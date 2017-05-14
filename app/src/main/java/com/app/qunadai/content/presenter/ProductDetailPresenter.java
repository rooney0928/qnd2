package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.ProductDetailBean;
import com.app.qunadai.content.contract.ProductDetailContract;
import com.app.qunadai.content.model.ProductDetailModelImpl;

/**
 * Created by wayne on 2017/5/14.
 */

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailContract.View view;
    private ProductDetailContract.Model model;

    public ProductDetailPresenter(ProductDetailContract.View iview) {
        this.view = iview;

        model = new ProductDetailModelImpl(new ProductDetailModelImpl.OnReturnDataListener() {
            @Override
            public void getProductDetail(ProductDetailBean bean) {
                view.getProductDetail(bean);
            }

            @Override
            public void getProductDetailFail(String error) {
                view.getProductDetailFail(error);
            }

            @Override
            public void getPersonValue(PersonBean bean) {
                view.getPersonValue(bean);
            }

            @Override
            public void getPersonValueFail(String error) {
                view.getPersonValueFail(error);
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
    public void requestProductDetail(String pid) {
        model.requestProductDetail(pid);
    }

    @Override
    public void requestPersonValue(String token) {
        model.requestPersonValue(token);
    }
}
