package com.app.qunadai.content.ui.bbs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.qunadai.MyApp;
import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Comment;
import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.Post;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.content.adapter.CommentAdapter;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.PostImgAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.bbs.PostDetailContract;
import com.app.qunadai.content.presenter.bbs.PostDetailPresenter;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventAlbum;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.RelativeDateFormat;
import com.app.qunadai.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/6/5.
 */

public class PostDetailActivity extends BaseActivity implements PostDetailContract.View {
    private PostDetailPresenter postDetailPresenter;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;


    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.sv_layout)
    ScrollView sv_layout;
    @BindView(R.id.rv_comment)
    RecyclerView rv_comment;
    @BindView(R.id.ll_comment_part)
    LinearLayout ll_comment_part;


    @BindView(R.id.tv_post_detail_title)
    TextView tv_post_detail_title;
    @BindView(R.id.iv_post_detail_avatar)
    ImageView iv_post_detail_avatar;
    @BindView(R.id.tv_post_detail_username)
    TextView tv_post_detail_username;
    @BindView(R.id.tv_post_detail_time)
    TextView tv_post_detail_time;
    @BindView(R.id.tv_post_detail_view)
    TextView tv_post_detail_view;
    @BindView(R.id.tv_post_detail_content)
    TextView tv_post_detail_content;

    @BindView(R.id.ll_post_detail_praise)
    LinearLayout ll_post_detail_praise;
    @BindView(R.id.cb_post_detail_praise)
    CheckBox cb_post_detail_praise;
    @BindView(R.id.tv_post_detail_praise)
    TextView tv_post_detail_praise;
    @BindView(R.id.ll_post_detail_wechat)
    LinearLayout ll_post_detail_wechat;
    @BindView(R.id.ll_post_detail_friend)
    LinearLayout ll_post_detail_friend;

    @BindView(R.id.et_comment)
    EditText et_comment;
    @BindView(R.id.tv_comment)
    TextView tv_comment;

    @BindView(R.id.view_comment)
    View view_comment;

    @BindView(R.id.rv_pics)
    RecyclerView rv_pics;

    CommentAdapter commentAdapter;
    LinearLayoutManager linearLayoutManager;
    private String postid;


    List<Comment> list;

    private String token;
    boolean isRefresh;
    private static final int PAGE_SIZE = 10;
    int page = 0;

    GridLayoutManager gridLayoutManager;
    PostImgAdapter adapter;
    private ArrayList<String> mImageList;


    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("详情");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_post_detail, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        postDetailPresenter = new PostDetailPresenter(this);

        list = new ArrayList<>();

        mImageList = new ArrayList<>();
        adapter = new PostImgAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                previewImage(position);
            }
        });
        gridLayoutManager = new GridLayoutManager(this, 4);

        rv_pics.setLayoutManager(gridLayoutManager);
        rv_pics.setAdapter(adapter);

        commentAdapter = new CommentAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                rv_comment.scrollTo(0,500);
                sv_layout.scrollTo(0, ll_comment_part.getTop() + view.getWidth() * position);
            }
        });

        commentAdapter.setLoadMoreListener(new CommentAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (isRefresh) {
                    return;
                }

                if (swipe_layout != null) {
                    swipe_layout.setRefreshing(true);
                }
                page++;
                postDetailPresenter.getCommentList(token, postid, page, PAGE_SIZE);


            }
        });

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_comment.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_comment.setLayoutManager(linearLayoutManager);
//        rv_comment.setHasFixedSize(true);
        rv_comment.setItemAnimator(new DefaultItemAnimator());
        rv_comment.setAdapter(commentAdapter);
        //保持滑动惯性
        rv_comment.setNestedScrollingEnabled(false);
        //滚动监听
//        rv_comment.addOnScrollListener(mOnScrollListener);

        token = PrefUtil.getString(this, PrefKey.TOKEN, "");


        View root = getWindow().getDecorView();
        controlKeyboardLayout(root,et_comment);
    }

    /**
     * 点击进入预览图片
     *
     * @param position
     */
    private void previewImage(int position) {
        if (mImageList != null && mImageList.size() > 0) {
            Intent intentAlbum = new Intent(this, AlbumActivity.class);

            intentAlbum.putStringArrayListExtra("pics", mImageList);
            intentAlbum.putExtra("position", position);
            startActivity(intentAlbum);

//            EventAlbum event = new EventAlbum();
//            event.setPics(mImageList);
//            event.setPosition(position);
//            EventBus.getDefault().postSticky(event);
        }

/*

        Album.gallery(this)
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
//                .navigationBarColor(navigationBarColor) // NavigationBar color.

                .checkedList(mImageList) // List of pictures to preview.
                .currentPosition(position) // First display position image of the list.
                .checkFunction(false) // Anti-election function.
                .start(ACTIVITY_REQUEST_PREVIEW_PHOTO);
*/


    }

    boolean isAdmin;

    @Override
    public void initViewData() {
        postid = getIntent().getStringExtra("postid");
        isAdmin = getIntent().getBooleanExtra("admin", false);
        if (isAdmin) {
            tv_post_detail_username.setText("去哪贷");
        }

        ll_post_detail_praise.setOnClickListener(this);
        ll_post_detail_wechat.setOnClickListener(this);
        ll_post_detail_friend.setOnClickListener(this);

        if (CommUtil.isNull(token)) {
            postDetailPresenter.getPostDetailNoUser(postid);
            postDetailPresenter.getCommentListNoUser(postid, page, PAGE_SIZE);
        } else {
            postDetailPresenter.getPostDetail(postid, token);
            postDetailPresenter.getCommentList(token, postid, page, PAGE_SIZE);
        }


        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                if (CommUtil.isNull(token)) {
                    postDetailPresenter.getCommentListNoUser(postid, page, PAGE_SIZE);
                } else {
                    postDetailPresenter.getCommentList(token, postid, page, PAGE_SIZE);
                }
            }
        });


        tv_comment.setOnClickListener(this);
    }

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，
                // TODO 但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
                LogU.t("load");
                if (list != null && list.size() > 0) {
                    page++;
//                    postMyPresenter.getPostList(token, page, PAGE_SIZE);
                    if (CommUtil.isNull(token)) {
                        postDetailPresenter.getCommentListNoUser(postid, page, PAGE_SIZE);
                    } else {
                        postDetailPresenter.getCommentList(token, postid, page, PAGE_SIZE);
                    }
                }
            }
        }
    };


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_post_detail_praise:
                //点赞
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                boolean isPraise = cb_post_detail_praise.isChecked();


                if (isPraise) {
                    //当前是点赞，给他取消
                    postDetailPresenter.cancelPraisePost(postid, token);
                } else {
                    //当前没赞，反手给他一记赞
                    postDetailPresenter.praisePost(postid, token);
                }
                break;
            case R.id.ll_post_detail_wechat:
                shareToWX(SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.ll_post_detail_friend:
                shareToWX(SendMessageToWX.Req.WXSceneTimeline);

                break;
            case R.id.tv_comment:
                sendComment();
                break;
        }
    }

    public void sendComment() {
        if (CommUtil.isNull(token)) {
            exeLogin();
        } else {
            String content = CommUtil.getText(et_comment);
            if (CommUtil.isNull(content)) {
                ToastUtil.showToast(this, "内容不能为空");
                return;
            }
            postDetailPresenter.sendComment(token, postid, content);
        }
    }

    private void shareToWX(int WXChannel) {
        if (!MyApp.api.isWXAppInstalled()) {
            ToastUtil.showToast(this, "微信未安装");
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
//                webpage.webpageUrl = "http://www.bthai.net";
        webpage.webpageUrl = "https://m.qunadai.com/html/raiders/post_details.html?articleId=" + postid;

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = CommUtil.getText(tv_post_detail_title);
        if (CommUtil.getText(tv_post_detail_content).length() >= 80) {
            msg.description = CommUtil.getText(tv_post_detail_content).substring(0, 79);
        } else {
            msg.description = CommUtil.getText(tv_post_detail_content);
        }
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = bmpToByteArray(bmp, true);

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;

//        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.scene = WXChannel;

        boolean result = MyApp.api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        showLoading();
        isRefresh = true;

    }

    @Override
    public void requestEnd() {
        //延迟200毫秒关闭swipe
        Observable.timer(200, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideLoading();
                                if (swipe_layout != null && swipe_layout.isRefreshing()) {
                                    swipe_layout.setRefreshing(false);
                                }
                                isRefresh = false;
                            }
                        });
                    }
                }
        );

    }


    ArrayList<String> pics;

    @Override
    public void getPostDetail(PostBean bean) {


        Post tpost = bean.getContent().getArticle();
        tv_post_detail_title.setText(tpost.getTitle());
        if (!isAdmin) {
            tv_post_detail_username.setText(tpost.getUserNick());
        }
        tv_post_detail_time.setText(RelativeDateFormat.format(new Date(tpost.getCreatedTime())));
        tv_post_detail_view.setText("" + tpost.getBrowseAmount());
        tv_post_detail_content.setText(tpost.getContent());
        cb_post_detail_praise.setChecked(tpost.isPraisedByCurrentUser());
        tv_post_detail_praise.setText("" + tpost.getThumbUpAmount());
        tv_post_detail_praise.setSelected(tpost.isPraisedByCurrentUser());

        String imgUrl = RxHttp.ROOT + "attachments/" + tpost.getUserAvatar();
        ImgUtil.loadImgAvatar(this, imgUrl, iv_post_detail_avatar);

        pics = new ArrayList<>();

        //rxjava式循环修改url,并填入
        Observable.from(tpost.getPictures())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        adapter.setImages(pics);
                        mImageList = pics;
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(PostDetailActivity.this, e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        String imgUrl = RxHttp.ROOT + "articlePics/" + s;
                        pics.add(imgUrl);
                    }
                });
    }

    @Override
    public void getPostDetailFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void commentList(CommentList bean) {
        //刷新
        List<Comment> commentsList = bean.getContent().getCommentList().getContent();
        list.clear();
        list = commentsList;
        commentAdapter.setList(list);
    }

    @Override
    public void commentListMore(CommentList bean) {
        //加载更多
        List<Comment> commentsList = bean.getContent().getCommentList().getContent();
        if (commentsList.size() > 0) {
            list.addAll(commentsList);
            commentAdapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void commentListFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void sendComment(SendCommentBean bean) {
        page = 0;
        if (CommUtil.isNull(token)) {
            postDetailPresenter.getCommentListNoUser(postid, page, PAGE_SIZE);
        } else {
            postDetailPresenter.getCommentList(token, postid, page, PAGE_SIZE);
        }
        et_comment.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                    0);
        }
    }

    @Override
    public void sendCommentFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void praisePost(PraiseBean bean) {
        optPraise(bean);
    }

    public void optPraise(PraiseBean bean) {
        boolean isPraise = "NORMAL".equalsIgnoreCase(bean.getContent().getThumbUp().getStatus());
        cb_post_detail_praise.setChecked(isPraise);
        tv_post_detail_praise.setSelected(isPraise);

//        int praiseCount = post.getThumbUpAmount();
        int praiseCount = Integer.valueOf(CommUtil.getText(tv_post_detail_praise));
        if (isPraise) {
            praiseCount += 1;
        } else {
            praiseCount -= 1;
        }

        tv_post_detail_praise.setText("" + praiseCount);
    }

    @Override
    public void praisePostFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void cancelPraisePost(PraiseBean bean) {
        optPraise(bean);
    }

    @Override
    public void cancelPraisePostFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventProgress event) {
        if (event.isShow()) {
            showLoading();
        } else {
            Observable.timer(200, TimeUnit.MILLISECONDS).subscribe(
                    new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideLoading();
                                }
                            });
                        }
                    }
            );
        }


    }

    /**
     * 解决在页面底部置输入框，输入法弹出遮挡部分输入框的问题
     * @param root 页面根元素
     * @param editLayout 被软键盘遮挡的输入框
     */
    public static void controlKeyboardLayout(final View root,
                                             final View editLayout) {
        // TODO Auto-generated method stub
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                Rect rect=new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInVisibleHeight=root.getRootView().getHeight()-rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInVisibleHeight > 100) {
                    LogU.t( "不可视区域高度大于100，则键盘显示");
                    int[] location = new int[2];
                    //获取editLayout在窗体的坐标
                    editLayout.getLocationInWindow(location);
                    //计算root滚动高度，使editLayout在可见区域
                    int srollHeight = (location[1] + editLayout.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    LogU.t("不可视区域高度小于100，则键盘隐藏");
                    root.scrollTo(0, 0);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
