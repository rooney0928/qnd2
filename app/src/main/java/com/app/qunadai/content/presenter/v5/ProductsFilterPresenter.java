package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.content.contract.v5.ProductsFilterContract;
import com.app.qunadai.content.model.v5.ProductsFilterModelImpl;

/**
 * Created by wayne on 2017/9/13.
 */

public class ProductsFilterPresenter implements ProductsFilterContract.Presenter {

    private ProductsFilterContract.View view;
    private ProductsFilterContract.Model model;

    public ProductsFilterPresenter(ProductsFilterContract.View iview) {
        this.view = iview;
        model = new ProductsFilterModelImpl(new ProductsFilterModelImpl.OnReturnDataListener() {
            @Override
            public void getProducts(BaseBean<ProductsFilter> bean) {
                view.getProducts(bean);
            }

            @Override
            public void getProductsMore(BaseBean<ProductsFilter> bean) {
                view.getProductsMore(bean);
            }

            @Override
            public void getProductsFail(String error) {
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
    public void getProducts(String prop, int page, int size) {
        model.getProducts(prop, page, size);
    }
}
