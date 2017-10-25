package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.Products;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface Home5Contract {
    interface View extends BaseView {
        void getBanner(BannerBean bean);

        void getBannerFail(String error);

        void getHomeFloors(BaseBean<Floors> bean);

        void getHomeFloorsFail(String error);

        void getHomeProducts(BaseBean<Products> bean);

        void getHomeProductsFail(String error);

        void getPersonValue(PersonBean bean);

        void getPersonValueFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getBanner();

        void getHomeFloors();

        void getHomeProducts();

        void requestPersonValue(String token);


    }

    interface Model extends BaseModel {
        void getBanner();

        void getHomeFloors();

        void getHomeProducts();

        void requestPersonValue(String token);

    }
}
