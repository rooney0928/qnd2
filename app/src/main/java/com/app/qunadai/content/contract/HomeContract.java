package com.app.qunadai.content.contract;

import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
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
        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);
        void getBanner(BannerBean bean);
        void getBannerFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getHomeRecommend();
        void requestPersonValue(String token);
        void getBanner();
    }

    interface Model extends BaseModel {
        void getHomeRecommend();
        void requestPersonValue(String token);
        void getBanner();

    }
}
