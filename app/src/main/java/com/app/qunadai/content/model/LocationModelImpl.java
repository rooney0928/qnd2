package com.app.qunadai.content.model;

import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.LocationContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/7/28.
 */

public class LocationModelImpl implements LocationContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public LocationModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {

        void getHotCity(HotCity bean);

        void getHotCityFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getHotCity() {
        Observable<HotCity> request = RxHttp.getInstance().hotCity();
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<HotCity>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getHotCityFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(HotCity bean) {
                        onReturnDataListener.getHotCity(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
