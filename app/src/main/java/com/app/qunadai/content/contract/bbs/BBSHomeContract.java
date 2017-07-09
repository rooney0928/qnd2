package com.app.qunadai.content.contract.bbs;

import com.app.qunadai.bean.bbs.StrategyBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/7/8.
 */

public interface BBSHomeContract {

    interface View extends BaseView {
        void postList(StrategyBean bean);

        void postListMore(StrategyBean bean);

        void postListFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getPostList(int page, int size);
    }

    interface Model extends BaseModel {
        void getPostList(int page, int size);
    }
}
