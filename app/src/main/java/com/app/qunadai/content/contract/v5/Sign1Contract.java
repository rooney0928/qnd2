package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.Products;
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

        void loginDone(Token token);

        void loginFail(String error);

    }

    interface Presenter extends BasePresenter {
        void checkPhone(String phone);

        void loginByPwd(String phone, String pwd, String imei);

    }

    interface Model extends BaseModel {
        void checkPhone(String phone);

        void loginByPwd(String phone, String pwd, String imei);

    }
}
