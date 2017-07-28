package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.content.contract.LocationContract;
import com.app.qunadai.content.model.LocationModelImpl;

/**
 * Created by wayne on 2017/7/28.
 */

public class LocationPresenter implements LocationContract.Presenter {

    private LocationContract.View view;
    private LocationContract.Model model;

    public LocationPresenter(LocationContract.View iview) {
        this.view = iview;
        model = new LocationModelImpl(new LocationModelImpl.OnReturnDataListener() {
            @Override
            public void getHotCity(HotCity bean) {
                view.getHotCity(bean);
            }

            @Override
            public void getHotCityFail(String error) {
                view.getHotCityFail(error);
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
    public void getHotCity() {
        model.getHotCity();
    }
}
