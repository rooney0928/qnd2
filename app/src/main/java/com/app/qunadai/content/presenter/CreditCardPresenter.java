package com.app.qunadai.content.presenter;

import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.bean.CreditStrategy;
import com.app.qunadai.content.contract.CreditCardContract;
import com.app.qunadai.content.model.CreditCardModelImpl;

/**
 * Created by wayne on 2017/7/25.
 */

public class CreditCardPresenter implements CreditCardContract.Presenter {

    private CreditCardContract.View view;
    private CreditCardContract.Model model;

    public CreditCardPresenter(CreditCardContract.View iview) {
        this.view = iview;
        model = new CreditCardModelImpl(new CreditCardModelImpl.OnReturnDataListener() {
            @Override
            public void getStrategy(CreditStrategy bean) {
                view.getStrategy(bean);
            }

            @Override
            public void getStrategyFail(String error) {
                view.getStrategyFail(error);
            }

            @Override
            public void getCreditCardList(CreditCard bean) {
                view.getCreditCardList(bean);
            }

            @Override
            public void getCreditCardListFail(String error) {
                view.getCreditCardListFail(error);
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
    public void getStrategy(int page, int size) {
        model.getStrategy(page, size);
    }

    @Override
    public void getCreditCardList(String city, int page, int size) {
        model.getCreditCardList(city, page, size);
    }
}
