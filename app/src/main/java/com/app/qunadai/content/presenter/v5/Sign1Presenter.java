package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.content.contract.v5.Sign1Contract;
import com.app.qunadai.content.model.v5.Sign1ModelImpl;
import com.app.qunadai.utils.CommUtil;

/**
 * Created by wayne on 2017/9/11.
 */

public class Sign1Presenter implements Sign1Contract.Presenter {

    private Sign1Contract.View view;
    private Sign1Contract.Model model;

    public Sign1Presenter(Sign1Contract.View iview) {
        this.view = iview;
        model = new Sign1ModelImpl(new Sign1ModelImpl.OnReturnDataListener() {
            @Override
            public void checkPhone(BaseBean<IsExist> bean) {
                view.checkPhone(bean);
            }

            @Override
            public void checkPhoneFail(String error) {
                view.checkPhoneFail(error);
            }

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
    public void checkPhone(String phone) {
        model.checkPhone(phone);
    }

    @Override
    public void loginByPwd(String phone, String pwd, String imei) {
        model.loginByPwd(phone, pwd, imei);
    }
}
