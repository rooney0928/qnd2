package com.app.qunadai.content.presenter.bbs;

import com.app.qunadai.bean.bbs.StrategyBean;
import com.app.qunadai.content.contract.bbs.BBSHomeContract;
import com.app.qunadai.content.contract.bbs.BBSHomeContract.Presenter;
import com.app.qunadai.content.model.bbs.BBSHomeModelImpl;

/**
 * Created by wayne on 2017/7/9.
 */

public class BBSHomePresenter implements Presenter {

    private BBSHomeContract.View view;
    private BBSHomeContract.Model model;

    public BBSHomePresenter(BBSHomeContract.View iview) {
        this.view = iview;

        model = new BBSHomeModelImpl(new BBSHomeModelImpl.OnReturnDataListener() {
            @Override
            public void getPostList(StrategyBean bean) {
                view.postList(bean);
            }

            @Override
            public void getPostListMore(StrategyBean bean) {
                view.postListMore(bean);
            }

            @Override
            public void getPostListFail(String error) {
                view.postListFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }

            @Override
            public void tokenFail() {
                view.tokenFail();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getPostList(int page, int size) {
        model.getPostList(page, size);
    }
}
