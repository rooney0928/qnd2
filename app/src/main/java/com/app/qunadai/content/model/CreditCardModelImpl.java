package com.app.qunadai.content.model;

import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.bean.CreditStrategy;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.CreditCardContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/7/25.
 */

public class CreditCardModelImpl implements CreditCardContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public CreditCardModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getStrategy(CreditStrategy bean);

        void getStrategyFail(String error);

        void getCreditCardList(CreditCard bean);

        void getCreditCardListFail(String error);

        void requestStart();

        void requestEnd();
    }


    @Override
    public void getServerData() {

    }

    @Override
    public void getStrategy(int page, int size) {
        Observable<CreditStrategy> request = RxHttp.getInstance().creditStrategy(page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CreditStrategy>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getStrategyFail(ex.getDisplayMessage());
//                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
//                        }
                    }

                    @Override
                    protected void onOk(CreditStrategy bean) {
                        onReturnDataListener.getStrategy(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void getCreditCardList(String city, int page, int size) {
        Observable<CreditCard> request = RxHttp.getInstance().creditCardList(city, page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CreditCard>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getCreditCardListFail(ex.getDisplayMessage());
//                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
//                        }
                    }

                    @Override
                    protected void onOk(CreditCard bean) {
                        onReturnDataListener.getCreditCardList(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
