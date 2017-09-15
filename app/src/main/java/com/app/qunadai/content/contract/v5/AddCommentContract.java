package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.AddComment;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface AddCommentContract {
    interface View extends BaseView {
        void addComment(BaseBean<AddComment> bean);
        void addCommentFail(String error);

    }

    interface Presenter extends BasePresenter {
        void addComment(String pid, String token, int star,String content);

    }

    interface Model extends BaseModel {
        void addComment(String pid, String token, int star,String content);

    }
}
