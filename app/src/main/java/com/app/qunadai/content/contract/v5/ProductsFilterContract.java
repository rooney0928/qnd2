package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.Products;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface ProductsFilterContract {
    interface View extends BaseView {
        void getProducts(BaseBean<ProductsFilter> bean);
        void getProductsMore(BaseBean<ProductsFilter> bean);
        void getProductsFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getProducts(String prop,int page,int size);

    }

    interface Model extends BaseModel {
        void getProducts(String prop,int page,int size);

    }
}
