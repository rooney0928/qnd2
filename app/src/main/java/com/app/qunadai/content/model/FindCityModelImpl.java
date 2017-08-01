package com.app.qunadai.content.model;

import com.app.qunadai.bean.FindCity;
import com.app.qunadai.content.contract.FindCityContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/8/1.
 */

public class FindCityModelImpl implements FindCityContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public FindCityModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void findCity(FindCity bean);

        void findCityFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void findCity(String city) {
        Observable<FindCity> request = RxHttp.getInstance().findCity(city);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<FindCity>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.findCityFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(FindCity bean) {
                        onReturnDataListener.findCity(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
