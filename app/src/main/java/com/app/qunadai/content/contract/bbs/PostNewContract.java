package com.app.qunadai.content.contract.bbs;

import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

import org.json.JSONArray;

/**
 * Created by wayne on 2017/5/5.
 */

public interface PostNewContract {
    interface View extends BaseView {
        void postNewOk(PostNewBean bean);
        void postNewFail(String error);
    }

    interface Presenter extends BasePresenter {
        void postNew(String token, String title, String content, String[] pics);
    }

    interface Model extends BaseModel {
        void postNew(String token, String title, String content, String[] pics);
    }
}
