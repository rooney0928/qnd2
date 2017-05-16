package com.app.qunadai.content.model;

import com.app.qunadai.bean.MeBean;
import com.app.qunadai.content.contract.MeContract;
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

public class MeModelImpl implements MeContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public MeModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener{
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
                    }

                    @Override
                    protected void onOk(MeBean bean) {
//                        onReturnDataListener.loginDone(token);
                        onReturnDataListener.getCurrent(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
