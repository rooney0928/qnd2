package com.app.qunadai.content.contract.v5;

import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.StatusBean;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface AuthContract {
    interface View extends BaseView {
        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);
        void getStatus(StatusBean bean);

    }

    interface Presenter extends BasePresenter {
        void requestPersonValue(String token);
        void updateStatus(String mobileNumber,String businessId,String token);
    }

    interface Model extends BaseModel {
        void requestPersonValue(String token);
        void updateStatus(String mobileNumber,String businessId,String token);
    }
}
