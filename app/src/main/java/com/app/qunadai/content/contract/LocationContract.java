package com.app.qunadai.content.contract;

import com.app.qunadai.bean.NickBean;
import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface LocationContract {
    interface View extends BaseView {
        void getHotCity(HotCity bean);
        void getHotCityFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getHotCity();

    }

    interface Model extends BaseModel {
        void getHotCity();

    }
}
