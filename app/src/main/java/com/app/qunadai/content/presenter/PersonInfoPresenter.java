package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.content.contract.PersonInfoContract;
import com.app.qunadai.content.model.PersonInfoModelImpl;

/**
 * Created by wayne on 2017/5/13.
 */

public class PersonInfoPresenter implements PersonInfoContract.Presenter {
    private PersonInfoContract.View view;
    private PersonInfoContract.Model model;

    public PersonInfoPresenter(PersonInfoContract.View iview) {
        this.view = iview;
        model = new PersonInfoModelImpl(new PersonInfoModelImpl.OnReturnDataListener() {
            @Override
            public void getPersonInfo(PersonInfo bean) {
                view.getPersonInfo(bean);
            }

            @Override
            public void getPersonInfoFail(String error) {
                view.getPersonInfoFail(error);
            }

            @Override
            public void setPersonInfo(PersonInfo bean) {
                view.setPersonInfo(bean);
            }

            @Override
            public void setPersonInfoFail(String error) {
                view.setPersonInfoFail(error);
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
    public void requestPersonInfo(String token) {
        model.requestPersonInfo(token);
    }

    @Override
    public void setPersonInfo(String token, String amount, String period, String job, String income, String edu, String marry, String address) {
        model.setPersonInfo(token,amount,period,job,income,edu,marry,address);
    }
}
