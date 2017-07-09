package com.app.qunadai.content.contract.bbs;

import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface PostMyContract {

    interface View extends BaseView {
        void postList(PostListBean bean);
        void postListMore(PostListBean bean);
        void postListFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getPostList(String token, int page, int size);
    }

    interface Model extends BaseModel {
        void getPostList(String token, int page, int size);
    }
}
