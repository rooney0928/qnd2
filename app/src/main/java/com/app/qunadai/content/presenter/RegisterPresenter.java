package com.app.qunadai.content.presenter;

import com.app.qunadai.content.contract.RegisterContract;
import com.app.qunadai.content.model.RegisterModelImpl;

/**
 * Created by wayne on 2017/5/11.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View view;
    private RegisterContract.Model model;

    public RegisterPresenter(RegisterContract.View iview) {
        this.view = iview;
        model = new RegisterModelImpl(new RegisterModelImpl.OnReturnDataListener() {
            @Override
            public void getRegisterSms(String msg) {
                view.getRegisterSms(msg);
            }

            @Override
            public void getRegisterSmsFail(String error) {
                view.getRegisterSmsFail(error);
            }

            @Override
            public void registerDone(String str) {
                view.registerDone(str);
            }

            @Override
            public void registerFail(String error) {
                view.registerFail(error);
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
    public void requestRegisterSms(String phone) {
        model.requestRegisterSms(phone);
    }

    @Override
    public void register(String phone, String sms, String pwd) {
        model.register(phone, sms, pwd);
    }
}
