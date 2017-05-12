package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.ProductsBean;
import com.app.qunadai.content.contract.ProductsContract;
import com.app.qunadai.content.model.ProductsModelImpl;

/**
 * Created by wayne on 2017/5/12.
 */

public class ProductsPresenter implements ProductsContract.Presenter {

    private ProductsContract.Model model;
    private ProductsContract.View view;

    public ProductsPresenter(ProductsContract.View iview) {
        this.view = iview;

        model = new ProductsModelImpl(new ProductsModelImpl.OnReturnDataListener() {
            @Override
            public void requestProducts(ProductsBean bean) {
                view.getProducts(bean);
            }

            @Override
            public void requestProductsMore(ProductsBean bean) {
                view.getProductsMore(bean);
            }

            @Override
            public void requestProductsFail(String error) {
                view.getProductsFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestProducts(int page, int pageSize, String tagName, String amount, String term) {
        model.requestProducts(page, pageSize, tagName, amount, term);
    }

    @Override
    public void requestProductsMore(int page, int pageSize, String tagName, String amount, String term) {
        model.requestProductsMore(page, pageSize, tagName, amount, term);
    }
}
