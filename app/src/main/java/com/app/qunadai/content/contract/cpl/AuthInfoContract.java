package com.app.qunadai.content.contract.cpl;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.base.CplBase;
import com.app.qunadai.bean.cpl.CToken;
import com.app.qunadai.bean.cpl.UserInfo;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface AuthInfoContract {
    interface View extends BaseView {
        void getCplToken(CplBase<CToken> bean);
        void getCplTokenFail(String error);
        void setCplUserInfo(CplBase bean);
        void setCplUserInfoFail(String error);
    }

    interface Presenter extends BasePresenter {
        void reqCplToken(String phone);
        void setCplUserInfo(UserInfo info,String token);
    }

    interface Model extends BaseModel {
        void reqCplToken(String phone);
        void setCplUserInfo(UserInfo info,String token);
    }
}
