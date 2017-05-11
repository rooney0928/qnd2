package com.app.qunadai.content.model;

import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.ResetBean;
import com.app.qunadai.content.contract.ForgetContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/11.
 */

public class ForgetModelImpl implements ForgetContract.Model {


    private OnReturnDataListener onReturnDataListener;

    public ForgetModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void getForgetSms(Message msg);

        void getForgetSmsFail(String error);

        void resetDone(ResetBean str);

        void resetFail(String error);

        void requestStart();

        void requestEnd();
    }


    @Override
    public void getServerData() {

    }

    @Override
    public void requestForgetSms(String phone) {
        Observable<Message> request = RxHttp.getInstance().getForgetSms(phone);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Message>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getForgetSmsFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(Message bean) {
                        onReturnDataListener.getForgetSms(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);

    }

    @Override
    public void reset(String phone, String sms, String pwd) {
        Observable<ResetBean> request = RxHttp.getInstance().reset(phone, sms, pwd);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ResetBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.resetFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(ResetBean bean) {
                        onReturnDataListener.resetDone(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
