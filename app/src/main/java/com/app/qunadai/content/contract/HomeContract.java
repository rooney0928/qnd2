package com.app.qunadai.content.contract;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface HomeContract {
    interface View extends BaseView {
        void getHomeRecommend(HomeRecommend bean);
        void getHomeRecommendFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getHomeRecommend();
    }

    interface Model extends BaseModel {
        void getHomeRecommend();
    }
}
