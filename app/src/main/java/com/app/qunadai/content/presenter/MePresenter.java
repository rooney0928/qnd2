package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.MeBean;
import com.app.qunadai.content.contract.MeContract;
import com.app.qunadai.content.model.MeModelImpl;

/**
 * Created by wayne on 2017/5/12.
 */

public class MePresenter implements MeContract.Presenter {

    private MeContract.Model model;
    private MeContract.View view;

    public MePresenter(MeContract.View iview) {
        this.view = iview;

        model = new MeModelImpl(new MeModelImpl.OnReturnDataListener() {
            @Override
            public void getCurrent(MeBean bean) {
                view.getCurrent(bean);
            }

            @Override
            public void getCurrentFail(String error) {
                view.getCurrentFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestCurrent(String token) {
        model.requestCurrent(token);
    }
}
