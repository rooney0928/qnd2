package com.app.qunadai.content.contract.bbs;

import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.bean.bbs.TalentBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface TalentContract {

    interface View extends BaseView {
        void postList(TalentBean bean);

        void postListMore(TalentBean bean);

        void postListFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getPostList(int page, int size);
    }

    interface Model extends BaseModel {
        void getPostList(int page, int size);
    }
}
