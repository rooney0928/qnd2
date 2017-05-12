package com.app.qunadai.content.contract;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.Recommend;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/5/5.
 */

public interface RecommendContract {
    interface View extends BaseView {
        void getRecommend(Recommend bean);
        void getRecommendMore(Recommend bean);
        void getRecommendFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getRecommend(int page,int pageSize);
        void getRecommendMore(int page,int pageSize);
    }

    interface Model extends BaseModel {
        void getRecommend(int page,int pageSize);
        void getRecommendMore(int page,int pageSize);
    }
}
