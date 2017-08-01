package com.app.qunadai.content.contract;

import com.app.qunadai.bean.FindCity;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/8/1.
 */

public interface FindCityContract {
    interface View extends BaseView {
        void findCity(FindCity bean);
        void findCityError(String error);
    }

    interface Presenter extends BasePresenter {
        void findCity(String city);
    }

    interface Model extends BaseModel {
        void findCity(String city);
    }
}
