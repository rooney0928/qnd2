package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.Products;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface Sign1Contract {
    interface View extends BaseView {
        void checkPhone(BaseBean<IsExist> bean);

        void checkPhoneFail(String error);

        void getRegisterSms(BaseBean<SmsBean> bean);

        void getRegisterSmsFail(String error);

        void getForgetSms(BaseBean<SmsBean> bean);

        void getForgetSmsFail(String error);

        void getLoginSms(BaseBean<SmsBean> bean);

        void getLoginSmsFail(String error);

        void loginDone(BaseBean<Token> bean);

        void loginFail(String error);

    }

    interface Presenter extends BasePresenter {
        void checkPhone(String phone);

        void loginByPwd(String phone, String pwd, String imei);

        void sendRegSms(String phone);

        void sendLoginSms(String phone);

        void sendForgetSms(String phone);

    }

    interface Model extends BaseModel {
        void checkPhone(String phone);

        void loginByPwd(String phone, String pwd, String imei);

        void sendRegSms(String phone);

        void sendLoginSms(String phone);

        void sendForgetSms(String phone);

    }
}
