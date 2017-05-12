package com.app.qunadai.content.contract;

import com.app.qunadai.bean.ProductsBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/12.
 */

public interface ProductsContract {
    interface View extends BaseView {
        void getProducts(ProductsBean bean);
        void getProductsMore(ProductsBean bean);
        void getProductsFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestProducts(int page, int pageSize, String tagName,
                             String amount, String term);
        void requestProductsMore(int page, int pageSize, String tagName,
                             String amount, String term);
    }

    interface Model extends BaseModel {
        void requestProducts(int page, int pageSize, String tagName,
                             String amount, String term);
        void requestProductsMore(int page, int pageSize, String tagName,
                                 String amount, String term);
    }
}
