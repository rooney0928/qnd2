package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface ExploreContract {
    interface View extends BaseView {
        void getExplore(BaseBean<ExploreBean> bean);
        void getExploreFail(String error);

        void clearExplore(BaseBean bean);
        void clearExploreFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getExplore(String token);
        void clearExplore(String token);

    }

    interface Model extends BaseModel {
        void getExplore(String token);
        void clearExplore(String token);
    }
}
