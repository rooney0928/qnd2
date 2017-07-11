package com.app.qunadai.content.presenter.bbs;

import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.content.contract.bbs.PostDetailContract;
import com.app.qunadai.content.model.bbs.PostDetailModelImpl;

/**
 * Created by wayne on 2017/7/7.
 */

public class PostDetailPresenter implements PostDetailContract.Presenter {

    private PostDetailContract.View view;
    private PostDetailContract.Model model;

    public PostDetailPresenter(PostDetailContract.View iview) {
        this.view = iview;
        model = new PostDetailModelImpl(new PostDetailModelImpl.OnReturnDataListener() {
            @Override
            public void getPostDetail(PostBean bean) {
                view.getPostDetail(bean);
            }

            @Override
            public void getPostDetailFail(String error) {
                view.getPostDetailFail(error);
            }

            @Override
            public void commentList(CommentList bean) {
                view.commentList(bean);
            }

            @Override
            public void commentListMore(CommentList bean) {
                view.commentListMore(bean);
            }

            @Override
            public void commentListFail(String error) {
                view.commentListFail(error);
            }

            @Override
            public void sendComment(SendCommentBean bean) {
                view.sendComment(bean);
            }

            @Override
            public void sendCommentFail(String error) {
                view.sendCommentFail(error);
            }

            @Override
            public void praisePost(PraiseBean bean) {
                view.praisePost(bean);
            }

            @Override
            public void praisePostFail(String error) {
                view.praisePostFail(error);
            }

            @Override
            public void cancelPraisePost(PraiseBean bean) {
                view.cancelPraisePost(bean);
            }

            @Override
            public void cancelPraisePostFail(String error) {
                view.cancelPraisePostFail(error);
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
    public void getPostDetail(String aid, String token) {
        model.getPostDetail(aid, token);
    }

    @Override
    public void getPostDetailNoUser(String aid) {
        model.getPostDetailNoUser(aid);
    }

    @Override
    public void getCommentList(String token, String aid, int page, int size) {
        model.getCommentList(token, aid, page, size);
    }

    @Override
    public void getCommentListNoUser(String aid, int page, int size) {
        model.getCommentListNoUser(aid, page, size);
    }

    @Override
    public void sendComment(String token, String aid, String content) {
        model.sendComment(token, aid, content);
    }

    @Override
    public void praisePost(String aid, String token) {
        model.praisePost(aid, token);
    }

    @Override
    public void cancelPraisePost(String aid, String token) {
        model.cancelPraisePost(aid, token);
    }
}
