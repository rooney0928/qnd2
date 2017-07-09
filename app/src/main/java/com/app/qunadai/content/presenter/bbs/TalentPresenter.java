package com.app.qunadai.content.presenter.bbs;

import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.bean.bbs.TalentBean;
import com.app.qunadai.content.contract.bbs.PostMyContract;
import com.app.qunadai.content.contract.bbs.TalentContract;
import com.app.qunadai.content.model.bbs.PostMyModelImpl;
import com.app.qunadai.content.model.bbs.TalentModelImpl;

/**
 * Created by wayne on 2017/7/4.
 */

public class TalentPresenter implements TalentContract.Presenter {

    private TalentContract.View view;
    private TalentContract.Model model;

    public TalentPresenter(TalentContract.View iview) {
        this.view = iview;

        model = new TalentModelImpl(new TalentModelImpl.OnReturnDataListener() {
            @Override
            public void getPostList(TalentBean bean) {
                view.postList(bean);
            }

            @Override
            public void getPostListMore(TalentBean bean) {
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
