package com.app.qunadai.content.ui.bbs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
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
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.content.adapter.CommentAdapter;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.bbs.PostDetailContract;
import com.app.qunadai.content.presenter.bbs.PostDetailPresenter;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.RelativeDateFormat;
import com.app.qunadai.utils.ToastUtil;
import com.bumptech.glide.util.Util;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/6/5.
 */

public class PostDetailActivity extends BaseActivity implements PostDetailContract.View {
    private PostDetailPresenter postDetailPresenter;


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
    @BindView(R.id.iv_comment_send)
    ImageView iv_comment_send;

    CommentAdapter commentAdapter;
    LinearLayoutManager linearLayoutManager;
    private Post post;


    List<Comment> list;

    private String token;
    boolean isRefresh;
    private static final int PAGE_SIZE = 10;
    int page = 0;
    int lastVisibleItem;


    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("详情");
        setTitleRightImg(R.mipmap.ic_repost);
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
        postDetailPresenter = new PostDetailPresenter(this);

        list = new ArrayList<>();

        commentAdapter = new CommentAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                rv_comment.scrollTo(0,500);
                sv_layout.scrollTo(0, ll_comment_part.getTop() + view.getWidth() * position);
            }
        });
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_comment.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_comment.setLayoutManager(linearLayoutManager);
        rv_comment.setHasFixedSize(true);
        rv_comment.setItemAnimator(new DefaultItemAnimator());
        rv_comment.setAdapter(commentAdapter);
        //保持滑动惯性
        rv_comment.setNestedScrollingEnabled(false);
        //滚动监听
//        rv_comment.addOnScrollListener(mOnScrollListener);
        rv_comment.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == commentAdapter.getItemCount()) {
                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    LogU.t("load2");
                    page++;
//                    recommendPresenter.getRecommendMore(page, PAGE_SIZE);
                    if (CommUtil.isNull(token)) {
                        postDetailPresenter.getCommentListNoUser(post.getId(), page, PAGE_SIZE);
                    } else {
                        postDetailPresenter.getCommentList(token, post.getId(), page, PAGE_SIZE);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });

        token = PrefUtil.getString(this, PrefKey.TOKEN, "");

    }

    @Override
    public void initViewData() {
        post = (Post) getIntent().getSerializableExtra("post");
        tv_post_detail_title.setText(post.getTitle());
        tv_post_detail_username.setText(post.getUserNick());
        tv_post_detail_time.setText(RelativeDateFormat.format(new Date(post.getCreatedTime())));
        tv_post_detail_view.setText("" + post.getBrowseAmount());
        tv_post_detail_content.setText(post.getContent());
        cb_post_detail_praise.setChecked(post.isPraisedByCurrentUser());
        tv_post_detail_praise.setText("" + post.getThumbUpAmount());

        String imgUrl = RxHttp.ROOT + "attachments/" + post.getUserAvatar();
        ImgUtil.loadImgAvatar(this,imgUrl,iv_post_detail_avatar);


        ll_post_detail_praise.setOnClickListener(this);
        ll_post_detail_wechat.setOnClickListener(this);
        ll_post_detail_friend.setOnClickListener(this);

        if (CommUtil.isNull(token)) {
            postDetailPresenter.getCommentListNoUser(post.getId(), page, PAGE_SIZE);
        } else {
            postDetailPresenter.getCommentList(token, post.getId(), page, PAGE_SIZE);
        }

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                if (CommUtil.isNull(token)) {
                    postDetailPresenter.getCommentListNoUser(post.getId(), page, PAGE_SIZE);
                } else {
                    postDetailPresenter.getCommentList(token, post.getId(), page, PAGE_SIZE);
                }
            }
        });
        et_comment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                LogU.t(v.toString());
                return false;
            }
        });

        iv_comment_send.setOnClickListener(this);
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
                        postDetailPresenter.getCommentListNoUser(post.getId(), page, PAGE_SIZE);
                    } else {
                        postDetailPresenter.getCommentList(token, post.getId(), page, PAGE_SIZE);
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
                boolean isPraise = cb_post_detail_praise.isChecked();
                cb_post_detail_praise.setChecked(!isPraise);
                tv_post_detail_praise.setSelected(!isPraise);
                break;
            case R.id.ll_post_detail_wechat:
                shareToWX(SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.ll_post_detail_friend:
                shareToWX(SendMessageToWX.Req.WXSceneTimeline);

                break;
            case R.id.iv_comment_send:
                if (CommUtil.isNull(token)) {
                    exeLogin();
                } else {
                    String content = CommUtil.getText(et_comment);
                    if (CommUtil.isNull(content)) {
                        ToastUtil.showToast(this, "内容不能为空");
                        return;
                    }
                    postDetailPresenter.sendComment(token, post.getId(), content);
                }
                break;
        }
    }

    private void shareToWX(int WXChannel) {
        if (!MyApp.api.isWXAppInstalled()) {
            ToastUtil.showToast(this, "微信未安装");
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
//                webpage.webpageUrl = "http://www.bthai.net";
        webpage.webpageUrl = "https://m.qunadai.com";

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = CommUtil.getText(tv_post_detail_title);
        msg.description = CommUtil.getText(tv_post_detail_content);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = bmpToByteArray(bmp, true);

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;

//        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.scene = WXChannel;

        boolean result = MyApp.api.sendReq(req);
        ToastUtil.showToast(this, "发送结果--" + result);
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
        hideLoading();
        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            swipe_layout.setRefreshing(false);
        }
        isRefresh = false;
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
            postDetailPresenter.getCommentListNoUser(post.getId(), page, PAGE_SIZE);
        } else {
            postDetailPresenter.getCommentList(token, post.getId(), page, PAGE_SIZE);
        }
        et_comment.setText("");
    }

    @Override
    public void sendCommentFail(String error) {
        ToastUtil.showToast(this, error);

    }
}
