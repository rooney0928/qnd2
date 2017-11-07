package com.app.qunadai.content.ui.me;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.MessagesContract;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.MessagesPresenter;
import com.app.qunadai.utils.NetworkUtil;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/7.
 */

public class MessageActivity extends BaseActivity implements MessagesContract.View{
    private MessagesPresenter messagesPresenter;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @Override
    protected void updateTopViewHideAndShow() {

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
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestMessages();

            }
        });
        requestMessages();


        setOnReLinkListener(new OnReLinkListener() {
            @Override
            public void doNewRequest() {
                messagesPresenter.getMessages(getToken());
            }
        });
    }

    @Override
    public void initViewData() {

    }

    public void requestMessages() {
        if (NetworkUtil.checkNetwork(this)) {
            messagesPresenter.getMessages(getToken());
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

    }


    @Override
    public void getMessages(BaseBean<ReplyMessages> bean) {

    }

    @Override
    public void getMessagesFail(String error) {

    }
}
