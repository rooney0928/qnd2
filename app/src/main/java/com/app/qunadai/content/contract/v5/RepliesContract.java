package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.bean.v5.Replies;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface RepliesContract {
    interface View extends BaseView {
        void getReplies(BaseBean<Replies> bean);
        void getRepliesMore(BaseBean<Replies> bean);
        void getRepliesFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getReplies(String cid, int page, int size);

    }

    interface Model extends BaseModel {
        void getReplies(String cid, int page, int size);

    }
}
