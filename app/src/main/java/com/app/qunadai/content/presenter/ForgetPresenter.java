package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.content.contract.ForgetContract;
import com.app.qunadai.content.model.ForgetModelImpl;

/**
 * Created by wayne on 2017/5/11.
 */

public class ForgetPresenter implements ForgetContract.Presenter {
    private ForgetContract.Model model;
    private ForgetContract.View view;

    public ForgetPresenter(ForgetContract.View iview) {
        this.view = iview;
        model = new ForgetModelImpl(new ForgetModelImpl.OnReturnDataListener() {
            @Override
            public void tokenFail() {
                view.tokenFail();
            }

            @Override
            public void getForgetSms(Message msg) {
                view.getForgetSms(msg);
            }

            @Override
            public void getForgetSmsFail(String error) {
                view.getForgetSmsFail(error);
            }

            @Override
            public void resetDone(ResetBean bean) {
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
    public void requestForgetSms(String phone) {
        model.requestForgetSms(phone);
    }

    @Override
    public void reset(String phone, String sms, String pwd) {
        model.reset(phone,sms,pwd);
    }
}
