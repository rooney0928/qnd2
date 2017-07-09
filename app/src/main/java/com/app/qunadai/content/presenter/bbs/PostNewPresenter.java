package com.app.qunadai.content.presenter.bbs;

import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.content.contract.bbs.PostNewContract;
import com.app.qunadai.content.model.bbs.PostNewModelImpl;

/**
 * Created by wayne on 2017/7/3.
 */

public class PostNewPresenter implements PostNewContract.Presenter {

    private PostNewContract.View view;
    private PostNewContract.Model model;

    public PostNewPresenter(PostNewContract.View iview) {
        this.view = iview;
        model = new PostNewModelImpl(new PostNewModelImpl.OnReturnDataListener() {
            @Override
            public void postNew(PostNewBean bean) {
                view.postNewOk(bean);
            }

            @Override
            public void postNewFail(String error) {
                view.postNewFail(error);
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
    public void postNew(String token, String title, String content, String[] pics) {
        model.postNew(token, title, content, pics);
    }
}
