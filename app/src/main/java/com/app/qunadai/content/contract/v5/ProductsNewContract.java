package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.bean.v5.ProductsNew;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface ProductsNewContract {
    interface View extends BaseView {
        void getProductsNew(BaseBean<ProductsNew> bean);
        void getProductsNewFail(String error);

    }

    interface Presenter extends BasePresenter {
        void getProductsNew();
        void lookProductsNew(String token);
    }

    interface Model extends BaseModel {
        void getProductsNew();
        void lookProductsNew(String token);
    }
}
