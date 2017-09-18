package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.contract.v5.Me5Contract;
import com.app.qunadai.content.model.v5.Me5ModelImpl;

/**
 * Created by wayne on 2017/5/12.
 */

public class Me5Presenter implements Me5Contract.Presenter {

    private Me5Contract.Model model;
    private Me5Contract.View view;

    public Me5Presenter(Me5Contract.View iview) {
        this.view = iview;

        model = new Me5ModelImpl(new Me5ModelImpl.OnReturnDataListener() {
            @Override
            public void tokenFail() {
                view.tokenFail();
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
            public void getCurrent(MeBean bean) {
                view.getCurrent(bean);
            }

            @Override
            public void getCurrentFail(String error) {
                view.getCurrentFail(error);
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
    public void requestCurrent(String token) {
        model.requestCurrent(token);
    }

    @Override
    public void requestPersonValue(String token) {
        model.requestPersonValue(token);
    }
}
