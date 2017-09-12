package com.app.qunadai.content.contract;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface LoginContract {
    interface View extends BaseView {
        void getLoginSms(String msg);
        void getLoginSmsFail(String error);

        void loginDone(BaseBean<Token> token);
        void loginFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestLoginSms(String phone);
        void loginByPwd(String phone,String pwd);
        void loginBySms(String phone,String sms);
    }

    interface Model extends BaseModel {
        void requestLoginSms(String phone);
        void loginByPwd(String phone,String pwd);
        void loginBySms(String phone,String sms);
    }
}
