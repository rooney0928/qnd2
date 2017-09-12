package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.RegBean;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface Sign3Contract {
    interface View extends BaseView {
        void registerDone(BaseBean<Token> bean);

        void registerFail(String error);

        void resetDone(BaseBean<Token> bean);

        void resetFail(String error);

    }

    interface Presenter extends BasePresenter {
        void register(String phone, String sms, String pwd);

        void reset(String phone, String sms, String pwd);
    }

    interface Model extends BaseModel {
        void register(String phone, String sms, String pwd);

        void reset(String phone, String sms, String pwd);
    }
}
