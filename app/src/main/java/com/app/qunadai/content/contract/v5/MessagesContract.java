package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/11/7.
 */

public interface MessagesContract {
    interface View extends BaseView {
        void getMessages(BaseBean<ReplyMessages> bean);
        void getMessagesMore(BaseBean<ReplyMessages> bean);
        void getMessagesFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getMessages(String token, int page, int size);
    }

    interface Model extends BaseModel {
        void getMessages(String token, int page, int size);
    }
}
