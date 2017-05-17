package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.content.contract.BankcardContract;
import com.app.qunadai.content.model.BankcardModelImpl;

/**
 * Created by wayne on 2017/5/14.
 */

public class BankcardPresenter implements BankcardContract.Presenter {
    private BankcardContract.View view;
    private BankcardContract.Model model;


    public BankcardPresenter(BankcardContract.View iview) {
        this.view = iview;
        model = new BankcardModelImpl(new BankcardModelImpl.OnReturnDataListener() {
            @Override
            public void tokenFail() {
                view.tokenFail();
            }

            @Override
            public void getBankcard(BankcardBean bean) {
                view.getBankcard(bean);
            }

            @Override
            public void getBankcardFail(String error) {
                view.getBankcardFail(error);
            }

            @Override
            public void setBankcard(BankcardBean bean) {
                view.setBankcard(bean);
            }

            @Override
            public void setBankcardFail(String error) {
                view.setBankcardFail(error);
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
    public void requestBankcard(String token) {
        model.requestBankcard(token);
    }

    @Override
    public void setBankcard(String token, String name, String bankcard, String idcard, String phone) {
        model.setBankcard(token, name, bankcard, idcard, phone);
    }
}
