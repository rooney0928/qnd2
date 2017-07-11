package com.app.qunadai.content.contract.bbs;

import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/7/6.
 */

public interface PostDetailContract {

    interface View extends BaseView {

        void getPostDetail(PostBean bean);

        void getPostDetailFail(String error);

        void commentList(CommentList bean);

        void commentListMore(CommentList bean);

        void commentListFail(String error);

        void sendComment(SendCommentBean bean);

        void sendCommentFail(String error);

        void praisePost(PraiseBean bean);

        void praisePostFail(String error);
        void cancelPraisePost(PraiseBean bean);

        void cancelPraisePostFail(String error);
    }

    interface Presenter extends BasePresenter {

        void getPostDetail(String aid, String token);

        void getPostDetailNoUser(String aid);

        void getCommentList(String token, String aid, int page, int size);

        void getCommentListNoUser(String aid, int page, int size);

        void sendComment(String token, String aid, String content);

        void praisePost(String aid, String token);

        void cancelPraisePost(String aid, String token);


    }

    interface Model extends BaseModel {

        void getPostDetail(String aid, String token);

        void getPostDetailNoUser(String aid);

        void getCommentList(String token, String aid, int page, int size);

        void getCommentListNoUser(String aid, int page, int size);

        void sendComment(String token, String aid, String content);

        void praisePost(String aid, String token);

        void cancelPraisePost(String aid, String token);
    }
}
