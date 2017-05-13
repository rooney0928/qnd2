package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.contract.LimitContract;
import com.app.qunadai.content.model.HomeModelImpl;
import com.app.qunadai.content.model.LimitModelImpl;

/**
 * Created by wayne on 2017/5/10.
 */

public class LimitPresenter implements LimitContract.Presenter {
    private LimitContract.View view;
    private LimitContract.Model model;

    public LimitPresenter(LimitContract.View iview) {
        this.view = iview;
        model = new LimitModelImpl(new LimitModelImpl.OnReturnDataListener() {

            @Override
            public void getPersonValue(PersonBean bean) {
                view.getPersonValue(bean);
            }

            @Override
            public void getPersonValueFail(String error) {
                view.getPersonValueFail(error);
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
    public void requestPersonValue(String token) {
        model.requestPersonValue(token);
    }

    @Override
    public void updateStatus(String mobileNumber, String businessId,String token) {
        model.updateStatus(mobileNumber,businessId,token);
    }
}
