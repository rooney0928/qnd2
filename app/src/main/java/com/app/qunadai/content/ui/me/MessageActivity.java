package com.app.qunadai.content.ui.me;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.MyMessageAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.MessagesContract;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.MessagesPresenter;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/7.
 */

public class MessageActivity extends BaseActivity implements MessagesContract.View {
    private MessagesPresenter messagesPresenter;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    private static final int PAGE_SIZE = 10;
    int page = 0;

    List<ReplyMessages.RepliedCommentsBean.ContentBean> list;

    MyMessageAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    int lastVisibleItem;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("我的消息");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_messages, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        messagesPresenter = new MessagesPresenter(this);
        adapter = new MyMessageAdapter(this);
        list = new ArrayList<>();

//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp8);
//        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);

        rv_list.setNestedScrollingEnabled(false);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMessages();

            }
        });
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    messagesPresenter.getMessages(getToken(), page, PAGE_SIZE);

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
                messagesPresenter.getMessages(getToken(), page, PAGE_SIZE);
            }
        });
    }

    @Override
    public void initViewData() {
        requestMessages();

    }

    public void requestMessages() {
        if (NetworkUtil.checkNetwork(this)) {
            page = 0;
            messagesPresenter.getMessages(getToken(), page, PAGE_SIZE);
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
    public void getMessages(BaseBean<ReplyMessages> bean) {
        List<ReplyMessages.RepliedCommentsBean.ContentBean> tempList = bean.getContent().getReplied_comments().getContent();
        list.clear();
        list = tempList;
        adapter.setList(list);
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getMessagesMore(BaseBean<ReplyMessages> bean) {
        //加载更多
        List<ReplyMessages.RepliedCommentsBean.ContentBean> tempList = bean.getContent().getReplied_comments().getContent();

        if (tempList.size() > 0) {
            list.addAll(tempList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void getMessagesFail(String error) {

    }
}
