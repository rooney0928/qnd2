package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface Sign2Contract {
    interface View extends BaseView {

        void getRegisterSms(BaseBean<SmsBean> msg);

        void getRegisterSmsFail(String error);

        void getLoginSms(BaseBean<SmsBean> bean);

        void getLoginSmsFail(String error);

        void getForgetSms(BaseBean<SmsBean> bean);

        void getForgetSmsFail(String error);

        void loginDone(BaseBean<Token> token);

        void loginFail(String error);

    }

    interface Presenter extends BasePresenter {

        void sendRegSms(String phone);

        void sendLoginSms(String phone);

        void sendForgetSms(String phone);

        void loginBySms(String phone, String sms);

    }

    interface Model extends BaseModel {

        void sendRegSms(String phone);

        void sendLoginSms(String phone);

        void sendForgetSms(String phone);

        void loginBySms(String phone, String sms);

    }
}
