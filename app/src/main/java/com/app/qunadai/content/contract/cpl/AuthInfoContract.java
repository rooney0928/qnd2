package com.app.qunadai.content.contract.cpl;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.base.CplBase;
import com.app.qunadai.bean.cpl.CToken;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface AuthInfoContract {
    interface View extends BaseView {
        void getCplToken(CplBase<CToken> bean);
        void getCplTokenFail(String fail);
    }

    interface Presenter extends BasePresenter {
        void reqCplToken(String phone);
    }

    interface Model extends BaseModel {
        void reqCplToken(String phone);
    }
}
