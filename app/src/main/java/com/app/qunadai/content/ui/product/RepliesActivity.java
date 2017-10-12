package com.app.qunadai.content.ui.product;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.NewReply;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.Replies;
import com.app.qunadai.bean.v5.Reply;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ReplyAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.RepliesContract;
import com.app.qunadai.content.inter.OnKeyBoardChangeListener;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.RepliesPresenter;
import com.app.qunadai.third.eventbus.EventOffline;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/10/10.
 */

public class RepliesActivity extends BaseActivity implements RepliesContract.View {
    RepliesPresenter repliesPresenter;
    @BindView(R.id.rl_reply_layout)
    RelativeLayout rl_reply_layout;
    @BindView(R.id.view_input)
    View view_input;
    @BindView(R.id.et_reply_content)
    EditText et_reply_content;
    @BindView(R.id.ll_input_layout)
    LinearLayout ll_input_layout;
    @BindView(R.id.iv_reply_send)
    ImageView iv_reply_send;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    ReplyAdapter replyAdapter;

    ProComment proComment;
    LinearLayoutManager linearLayoutManager;

    private static final int PAGE_SIZE = 5;
    int page = 0;


    private List<Reply> list;

    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("评论");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_replies, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        repliesPresenter = new RepliesPresenter(this);
        proComment = (ProComment) getIntent().getSerializableExtra("proComment");
        replyAdapter = new ReplyAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        list = new ArrayList<>();


        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp1);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setNestedScrollingEnabled(false);
        rv_list.setAdapter(replyAdapter);

        replyAdapter.setProComment(proComment);
        replyAdapter.setOnClickReplyListener(new ReplyAdapter.OnClickReplyListener() {
            @Override
            public void replyComment(int position) {

                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }

                view_input.setVisibility(View.VISIBLE);
//                bt_submit.setVisibility(View.GONE);
//                ProComment pc = list.get(position);
                if (CheckUtil.isMobile(proComment.getUsernick())) {
                    StringBuilder sb = new StringBuilder(proComment.getUsernick());
                    String username = sb.replace(3, proComment.getUsernick().length() - 4, "****").toString();
//                    tv_comment_username.setText(username);
                    et_reply_content.setHint("回复 " + username);

                } else {
                    et_reply_content.setHint("回复 " + proComment.getUsernick());
                }
//                cid = proComment.getId();

                et_reply_content.setText("");
                et_reply_content.setFocusable(true);
//                inputMethodManager.showSoftInput(et_reply_content,InputMethodManager.SHOW_FORCED);
                openKeyboard(et_reply_content);
            }
        });


        //获取屏幕高度
        screenHeight = CommUtil.getSize(this).y;
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 3;

    }

    int lastVisibleItem;

    @Override
    public void initViewData() {
        iv_reply_send.setOnClickListener(this);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                repliesPresenter.getReplies(proComment.getId(), page, PAGE_SIZE);

            }
        });
        controlKeyboardLayout(rl_reply_layout, ll_input_layout, new OnKeyBoardChangeListener() {
            @Override
            public void keyBoardChange(boolean isShow) {
                if (isShow) {
                    view_input.setVisibility(View.VISIBLE);
                } else {
                    view_input.setVisibility(View.GONE);
                }
            }
        });

        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == replyAdapter.getItemCount()) {
                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    repliesPresenter.getReplies(proComment.getId(), page, PAGE_SIZE);


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });

        setOnReLinkListener(new OnReLinkListener() {
            @Override
            public void doNewRequest() {
                page = 0;
                repliesPresenter.getReplies(proComment.getId(), page, PAGE_SIZE);

            }
        });

        if (NetworkUtil.checkNetwork(this)) {
            if (proComment != null) {
                repliesPresenter.getReplies(proComment.getId(), page, PAGE_SIZE);
            }
        } else {
            setViewOffline();
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_reply_send:

                //发帖
                if (CommUtil.getText(et_reply_content).length() < 6) {
                    ToastUtil.showToast(this, "评论内容不能少于6个字符");
                    return;
                }

                repliesPresenter.sendNewReply(proComment.getId(), getToken(), CommUtil.getText(et_reply_content));
                break;
        }
    }

    /**
     * 解决在页面底部置输入框，输入法弹出遮挡部分输入框的问题
     *
     * @param root       页面根元素
     * @param editLayout 被软键盘遮挡的输入框
     */
    public static void controlKeyboardLayout(final View root,
                                             final View editLayout, final OnKeyBoardChangeListener onKeyBoardChangeListener) {
        // TODO Auto-generated method stub
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInVisibleHeigh = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInVisibleHeigh > 100) {
//                    Log.v("hjb", "不可视区域高度大于100，则键盘显示");
                    int[] location = new int[2];
                    //获取editLayout在窗体的坐标
                    editLayout.getLocationInWindow(location);
                    //计算root滚动高度，使editLayout在可见区域
                    int srollHeight = (location[1] + editLayout.getHeight()) - rect.bottom;
                    onKeyBoardChangeListener.keyBoardChange(true);
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
//                    Log.v("hjb", "不可视区域高度小于100，则键盘隐藏");
                    onKeyBoardChangeListener.keyBoardChange(false);
                    root.scrollTo(0, 0);

                }


            }
        });
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {
        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            swipe_layout.setRefreshing(false);
        }
        hideKeyboard(et_reply_content);
    }


    @Override
    public void getReplies(BaseBean<Replies> bean) {
//刷新
//        List<LoanDetail> productList = bean.getContent().getPages().getContent();
        List<Reply> tempList = bean.getContent().getReplies().getContent();
        list.clear();
        list = tempList;
        replyAdapter.setList(list);
//        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getRepliesMore(BaseBean<Replies> bean) {
        //加载更多
        List<Reply> tempList = bean.getContent().getReplies().getContent();

        if (tempList.size() > 0) {
            list.addAll(tempList);
            replyAdapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void getRepliesFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void sendNewReply(BaseBean<NewReply> bean) {
        ToastUtil.showToast(this,"恭喜您!回复成功!");

        page = 0;
        repliesPresenter.getReplies(proComment.getId(), page, PAGE_SIZE);

    }

    @Override
    public void sendNewReplyFail(String error) {
        ToastUtil.showToast(this, error);

    }
}
