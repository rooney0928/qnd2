package com.app.qunadai.content.contract;

import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface ForgetContract {
    interface View extends BaseView {
        void getForgetSms(Message msg);
        void getForgetSmsFail(String error);
        void resetDone(ResetBean bean);
        void resetFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestForgetSms(String phone);
        void reset(String phone, String sms, String pwd);
    }

    interface Model extends BaseModel {
        void requestForgetSms(String phone);
        void reset(String phone, String sms, String pwd);
    }
}
