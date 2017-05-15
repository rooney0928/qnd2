package com.app.qunadai.content.contract;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.Token;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface SplashContract {
    interface View extends BaseView {
        void loginDone(Token token);
        void loginFail(String error);
    }

    interface Presenter extends BasePresenter {
        void loginByPwd(String phone,String pwd);
    }

    interface Model extends BaseModel {
        void loginByPwd(String phone,String pwd);
    }
}
