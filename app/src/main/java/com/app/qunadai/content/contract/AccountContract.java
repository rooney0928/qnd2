package com.app.qunadai.content.contract;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.bean.MeBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface AccountContract {
    interface View extends BaseView {
        void uploadAvatar(AvatarBean bean);
        void uploadAvatarFail(String error);
    }

    interface Presenter extends BasePresenter {
        void uploadAvatar(String token,String base64);
    }

    interface Model extends BaseModel {
        void uploadAvatar(String token,String base64);
    }
}
