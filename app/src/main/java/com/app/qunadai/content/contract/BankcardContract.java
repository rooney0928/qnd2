package com.app.qunadai.content.contract;

import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface BankcardContract {
    interface View extends BaseView {
        void getBankcard(BankcardBean bean);
        void getBankcardFail(String error);
        void setBankcard(BankcardBean bean);
        void setBankcardFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestBankcard(String token);
        void setBankcard(String token, String name, String bankcard, String idcard,String phone);
    }

    interface Model extends BaseModel {
        void requestBankcard(String token);
        void setBankcard(String token, String name, String bankcard, String idcard,String phone);
    }
}
