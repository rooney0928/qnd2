package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.content.contract.v5.Sign3Contract;
import com.app.qunadai.content.model.v5.Sign3ModelImpl;

/**
 * Created by wayne on 2017/9/12.
 */

public class Sign3Presenter implements Sign3Contract.Presenter {

    private Sign3Contract.View view;
    private Sign3Contract.Model model;

    public Sign3Presenter(Sign3Contract.View iview) {
        this.view = iview;
        model = new Sign3ModelImpl(new Sign3ModelImpl.OnReturnDataListener() {
            @Override
            public void registerDone(BaseBean<Token> bean) {
                view.registerDone(bean);
            }

            @Override
            public void registerFail(String error) {
                view.registerFail(error);
            }

            @Override
            public void resetDone(BaseBean<Token> bean) {
                view.resetDone(bean);
            }

            @Override
            public void resetFail(String error) {
                view.resetFail(error);
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
    public void register(String phone, String sms, String pwd) {
        model.register(phone, sms, pwd);
    }

    @Override
    public void reset(String phone, String sms, String pwd) {
        model.reset(phone, sms, pwd);
    }
}
