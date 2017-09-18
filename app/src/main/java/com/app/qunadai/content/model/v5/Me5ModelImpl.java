package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.MeContract;
import com.app.qunadai.content.contract.v5.Me5Contract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/12.
 */

public class Me5ModelImpl implements Me5Contract.Model {

    private OnReturnDataListener onReturnDataListener;

    public Me5ModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getPersonValue(PersonBean bean);

        void getPersonValueFail(String error);

        void getCurrent(MeBean bean);

        void getCurrentFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestCurrent(String token) {
        Observable<MeBean> request = RxHttp.getInstance().getMeCurrent(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<MeBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getCurrentFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(MeBean bean) {
                        onReturnDataListener.getCurrent(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void requestPersonValue(String token) {
        Observable<PersonBean> request = RxHttp.getInstance().getPersonValue(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PersonBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPersonValueFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(PersonBean bean) {
                        onReturnDataListener.getPersonValue(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
