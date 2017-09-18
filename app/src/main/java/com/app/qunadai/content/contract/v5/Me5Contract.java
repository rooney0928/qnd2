package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface Me5Contract {
    interface View extends BaseView {
        void getCurrent(MeBean meBean);
        void getCurrentFail(String error);
        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestCurrent(String token);
        void requestPersonValue(String token);

    }

    interface Model extends BaseModel {
        void requestCurrent(String token);
        void requestPersonValue(String token);

    }
}
