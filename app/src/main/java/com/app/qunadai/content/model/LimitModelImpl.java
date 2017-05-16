package com.app.qunadai.content.model;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.StatusBean;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.content.contract.LimitContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/10.
 */

public class LimitModelImpl implements LimitContract.Model {
    private OnReturnDataListener mOnReturnDataListener;

    public LimitModelImpl(OnReturnDataListener onReturnDataListener) {
        this.mOnReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener{
        void getPersonValue(PersonBean bean);
        void getPersonValueFail(String error);

        void requestStart();
        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestPersonValue(String token) {
        Observable<PersonBean> request = RxHttp.getInstance().getPersonValue(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PersonBean>() {
                    @Override
                    public void onStart() {
                        mOnReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        mOnReturnDataListener.getPersonValueFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PersonBean bean) {
                        mOnReturnDataListener.getPersonValue(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        mOnReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void updateStatus(String mobileNumber, String businessId,String token) {
        Observable<StatusBean> request = RxHttp.getInstance().updateStatus(mobileNumber,businessId,token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<StatusBean>() {
                    @Override
                    public void onStart() {
                        mOnReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
//                        mOnReturnDataListener.getPersonValueFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(StatusBean bean) {

                    }

                    @Override
                    protected void requestEnd() {
                        mOnReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
