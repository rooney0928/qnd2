package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.Token;
import com.app.qunadai.content.contract.SplashContract;
import com.app.qunadai.content.model.SplashModelImpl;

/**
 * Created by wayne on 2017/5/15.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View view;
    private SplashContract.Model model;

    public SplashPresenter(SplashContract.View iview) {
        this.view = iview;
        model = new SplashModelImpl(new SplashModelImpl.OnReturnDataListener() {
            @Override
            public void loginDone(Token token) {
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
    public void loginByPwd(String phone, String pwd) {
        model.loginByPwd(phone, pwd);
    }
}
