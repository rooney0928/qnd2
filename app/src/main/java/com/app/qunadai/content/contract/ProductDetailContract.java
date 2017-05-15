package com.app.qunadai.content.contract;

import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.ProductDetailBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/14.
 */

public interface ProductDetailContract {
    interface View extends BaseView {
        void getProductDetail(ProductDetailBean bean);
        void getProductDetailFail(String error);
        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestProductDetail(String pid);
        void requestPersonValue(String token);
        void applyOrder(String token,String amount,String time,String timeType,String productId,String type);

    }

    interface Model extends BaseModel {
        void requestProductDetail(String pid);
        void requestPersonValue(String token);
        void applyOrder(String token,String amount,String time,String timeType,String productId,String type);

    }
}
