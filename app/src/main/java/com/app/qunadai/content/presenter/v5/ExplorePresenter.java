package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.content.contract.v5.ExploreContract;
import com.app.qunadai.content.model.v5.ExploreModelImpl;

/**
 * Created by wayne on 2017/9/15.
 */

public class ExplorePresenter implements ExploreContract.Presenter {

    private ExploreContract.View view;
    private ExploreContract.Model model;

    public ExplorePresenter(ExploreContract.View iview) {
        this.view = iview;
        model = new ExploreModelImpl(new ExploreModelImpl.OnReturnDataListener() {
            @Override
            public void getExplore(BaseBean<ExploreBean> bean) {
                view.getExplore(bean);
            }

            @Override
            public void getExploreFail(String error) {
                view.getExploreFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }

            @Override
            public void tokenFail() {
                view.tokenFail();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getExplore(String token) {
        model.getExplore(token);
    }
}
