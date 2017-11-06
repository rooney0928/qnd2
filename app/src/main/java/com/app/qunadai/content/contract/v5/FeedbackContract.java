package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.FeedBack;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface FeedbackContract {
    interface View extends BaseView {
        void addFeedback(BaseBean<FeedBack> bean);

        void addFeedbackFail(String error);

    }

    interface Presenter extends BasePresenter {
        void addFeedback(String token,String msg);

    }

    interface Model extends BaseModel {
        void addFeedback(String token,String msg);

    }
}
