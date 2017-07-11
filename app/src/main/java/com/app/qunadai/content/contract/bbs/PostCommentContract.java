package com.app.qunadai.content.contract.bbs;

import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/7/10.
 */

public interface PostCommentContract {
    interface View extends BaseView {

        void praiseComment(PraiseBean bean);

        void praiseCommentFail(String error);

        void cancelPraiseComment(PraiseBean bean);

        void cancelPraiseCommentFail(String error);
    }

    interface Presenter extends BasePresenter {

        void praiseComment(String cid, String token);

        void cancelPraiseComment(String cid, String token);
    }

    interface Model extends BaseModel {

        void praiseComment(String cid, String token);

        void cancelPraiseComment(String cid, String token);
    }
}
