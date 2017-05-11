package com.app.qunadai.content.contract;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void getRegisterSms(String msg);
        void getRegisterSmsFail(String error);
        void registerDone(String str);
        void registerFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestRegisterSms(String phone);
        void register(String phone,String sms,String pwd);
    }

    interface Model extends BaseModel {
        void requestRegisterSms(String phone);
        void register(String phone,String sms,String pwd);
    }
}
