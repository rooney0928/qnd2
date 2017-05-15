package com.app.qunadai.content.contract;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface NicknameContract {
    interface View extends BaseView {
        void uploadNickname(AvatarBean bean);
        void uploadNicknameFail(String error);
    }

    interface Presenter extends BasePresenter {
        void uploadNickname(String token, String nickname);
    }

    interface Model extends BaseModel {
        void uploadNickname(String token, String nickname);
    }
}
