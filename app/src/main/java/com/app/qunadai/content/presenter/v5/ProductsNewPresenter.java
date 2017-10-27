package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProductsNew;
import com.app.qunadai.content.contract.v5.ProductsNewContract;
import com.app.qunadai.content.model.v5.ProductsNewModelImpl;

/**
 * Created by wayne on 2017/10/26.
 */

public class ProductsNewPresenter implements ProductsNewContract.Presenter {
    private ProductsNewContract.View view;
    private ProductsNewContract.Model model;

    public ProductsNewPresenter(ProductsNewContract.View iview) {
        this.view = iview;
        model = new ProductsNewModelImpl(new ProductsNewModelImpl.OnReturnDataListener() {
            @Override
            public void getProductsNew(BaseBean<ProductsNew> bean) {
                view.getProductsNew(bean);
            }

            @Override
            public void getProductsNewFail(String error) {
                view.getProductsNewFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }

            @Override
            public void tokenFail() {
                view.tokenFail();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getProductsNew() {
        model.getProductsNew();
    }

    @Override
    public void lookProductsNew(String token) {
        model.lookProductsNew(token);
    }
}
