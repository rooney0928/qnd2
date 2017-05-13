package com.app.qunadai.content.contract;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface LimitContract {
    interface View extends BaseView {
        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);
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
