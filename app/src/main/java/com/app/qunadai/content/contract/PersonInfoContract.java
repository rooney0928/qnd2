package com.app.qunadai.content.contract;

import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface PersonInfoContract {
    interface View extends BaseView {
        void getPersonInfo(PersonInfo bean);
        void getPersonInfoFail(String error);
        void setPersonInfo(PersonInfo bean);
        void setPersonInfoFail(String error);

        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);
    }

    interface Presenter extends BasePresenter {
        void requestPersonInfo(String token);
        void setPersonInfo(String token,String amount,String period,String job,
                            String income,String edu,String marry,String address);
        void requestPersonValue(String token);

    }

    interface Model extends BaseModel {
        void requestPersonInfo(String token);
        void setPersonInfo(String token,String amount,String period,String job,
                           String income,String edu,String marry,String address);
        void requestPersonValue(String token);

    }
}
