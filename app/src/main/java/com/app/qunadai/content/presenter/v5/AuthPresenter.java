package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.StatusBean;
import com.app.qunadai.content.contract.v5.AuthContract;
import com.app.qunadai.content.model.LimitModelImpl;
import com.app.qunadai.content.model.v5.AuthModelImpl;

/**
 * Created by wayne on 2017/5/10.
 */

public class AuthPresenter implements AuthContract.Presenter {
    private AuthContract.View view;
    private AuthContract.Model model;

    public AuthPresenter(AuthContract.View iview) {
        this.view = iview;
        model = new AuthModelImpl(new AuthModelImpl.OnReturnDataListener() {

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
            public void getStatus(StatusBean bean) {
                view.getStatus(bean);
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
    public void requestPersonValue(String token) {
        model.requestPersonValue(token);
    }

    @Override
    public void updateStatus(String mobileNumber, String businessId, String token) {
        model.updateStatus(mobileNumber, businessId, token);
    }
}
