package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.Products;
import com.app.qunadai.content.contract.v5.Home5Contract;
import com.app.qunadai.content.model.v5.Home5ModelImpl;

/**
 * Created by wayne on 2017/9/7.
 */

public class Home5Presenter implements Home5Contract.Presenter {
    private Home5Contract.View view;
    private Home5Contract.Model model;

    public Home5Presenter(Home5Contract.View iview) {
        this.view = iview;
        model = new Home5ModelImpl(new Home5ModelImpl.OnReturnDataListener() {
            @Override
            public void getPersonValue(PersonBean bean) {
                view.getPersonValue(bean);
            }

            @Override
            public void getPersonValueFail(String error) {
                view.getPersonValueFail(error);
            }

            @Override
            public void getBanner(BannerBean bean) {
                view.getBanner(bean);
            }

            @Override
            public void getBannerFail(String error) {
                view.getBannerFail(error);
            }

            @Override
            public void getHomeFloors(BaseBean<Floors> bean) {
                view.getHomeFloors(bean);
            }

            @Override
            public void getHomeFloorsFail(String error) {
                view.getHomeFloorsFail(error);
            }

            @Override
            public void getHomeProducts(BaseBean<Products> bean) {
                view.getHomeProducts(bean);
            }

            @Override
            public void getHomeProductsFail(String error) {
                view.getHomeProductsFail(error);
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

            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getBanner() {
        model.getBanner();
    }

    @Override
    public void getHomeFloors() {
        model.getHomeFloors();
    }

    @Override
    public void getHomeProducts() {
        model.getHomeProducts();
    }

    @Override
    public void requestPersonValue(String token) {
        model.requestPersonValue(token);
    }
}
