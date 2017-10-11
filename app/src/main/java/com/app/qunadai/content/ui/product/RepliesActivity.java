package com.app.qunadai.content.ui.product;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.Replies;
import com.app.qunadai.bean.v5.Reply;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ReplyAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.RepliesContract;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.RepliesPresenter;
import com.app.qunadai.third.eventbus.EventOffline;
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

    }
    int lastVisibleItem;

    @Override
    public void initViewData() {
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                repliesPresenter.getReplies(proComment.getId(), page, PAGE_SIZE);

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

    }
}
