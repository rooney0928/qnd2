package com.app.qunadai.content.contract;

import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.bean.CreditStrategy;
import com.app.qunadai.content.base.BaseModel;
import com.app.qunadai.content.base.BasePresenter;
import com.app.qunadai.content.base.BaseView;

/**
 * Created by wayne on 2017/7/25.
 */

public interface CreditCardContract {
    interface View extends BaseView {
        void getStrategy(CreditStrategy bean);

        void getStrategyFail(String error);

        void getCreditCardList(CreditCard bean);

        void getCreditCardListFail(String error);
    }

    interface Presenter extends BasePresenter {
        void getStrategy(int page, int size);

        void getCreditCardList(String city, int page, int size);

    }

    interface Model extends BaseModel {
        void getStrategy(int page, int size);

        void getCreditCardList(String city, int page, int size);

    }
}
