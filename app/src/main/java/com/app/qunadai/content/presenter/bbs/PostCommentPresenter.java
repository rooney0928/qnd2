package com.app.qunadai.content.presenter.bbs;

import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.content.contract.bbs.PostCommentContract;
import com.app.qunadai.content.model.bbs.PostCommentModelImpl;

/**
 * Created by wayne on 2017/7/11.
 */

public class PostCommentPresenter implements PostCommentContract.Presenter {

    private PostCommentContract.View view;
    private PostCommentContract.Model model;

    public PostCommentPresenter(PostCommentContract.View iview) {
        this.view = iview;
        model = new PostCommentModelImpl(new PostCommentModelImpl.OnReturnDataListener() {
            @Override
            public void praiseComment(PraiseBean bean) {
                view.praiseComment(bean);
            }

            @Override
            public void praiseCommentFail(String error) {
                view.praiseCommentFail(error);
            }

            @Override
            public void cancelPraiseComment(PraiseBean bean) {
                view.cancelPraiseComment(bean);
            }

            @Override
            public void cancelPraiseCommentFail(String error) {
                view.cancelPraiseCommentFail(error);
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
    public void praiseComment(String cid, String token) {
        model.praiseComment(cid, token);
    }

    @Override
    public void cancelPraiseComment(String cid, String token) {
        model.cancelPraiseComment(cid, token);
    }
}
