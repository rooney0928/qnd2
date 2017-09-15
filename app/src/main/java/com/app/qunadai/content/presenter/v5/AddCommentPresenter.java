package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.AddComment;
import com.app.qunadai.content.contract.v5.AddCommentContract;
import com.app.qunadai.content.model.v5.AddCommentModelImpl;

/**
 * Created by wayne on 2017/9/14.
 */

public class AddCommentPresenter implements AddCommentContract.Presenter {

    private AddCommentContract.View view;
    private AddCommentContract.Model model;

    public AddCommentPresenter(AddCommentContract.View iview) {
        this.view = iview;
        model = new AddCommentModelImpl(new AddCommentModelImpl.OnReturnDataListener() {
            @Override
            public void addComment(BaseBean<AddComment> bean) {
                view.addComment(bean);
            }

            @Override
            public void addCommentFail(String error) {
                view.addCommentFail(error);
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
    public void addComment(String pid, String token, int star,String content) {
        model.addComment(pid, token, star,content);
    }
}
