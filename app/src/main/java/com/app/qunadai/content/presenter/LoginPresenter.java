package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.content.contract.LoginContract;
import com.app.qunadai.content.model.LoginModelImpl;

/**
 * Created by wayne on 2017/5/11.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginContract.Model model;

    public LoginPresenter(LoginContract.View iview) {
        this.view = iview;

        model = new LoginModelImpl(new LoginModelImpl.OnReturnDataListener() {
            @Override
            public void getLoginSms(String msg) {
                view.getLoginSms(msg);
            }

            @Override
            public void getLoginSmsFail(String error) {
                view.getLoginSmsFail(error);
            }

            @Override
            public void loginDone(BaseBean<Token> token) {
                view.loginDone(token);
            }

            @Override
            public void loginFail(String error) {
                view.loginFail(error);
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
    public void requestLoginSms(String phone) {
        model.requestLoginSms(phone);
    }

    @Override
    public void loginByPwd(String phone, String pwd) {
        model.loginByPwd(phone, pwd);
    }

    @Override
    public void loginBySms(String phone, String sms) {
        model.loginBySms(phone, sms);
    }
}
