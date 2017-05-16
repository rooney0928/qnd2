package com.app.qunadai.content.model;

import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.RegBean;
import com.app.qunadai.content.contract.RegisterContract;
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

public class RegisterModelImpl implements RegisterContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public RegisterModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void getRegisterSms(String msg);

        void getRegisterSmsFail(String error);

        void registerDone(RegBean str);

        void registerFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestRegisterSms(String phone) {
        Observable<Message> request = RxHttp.getInstance().getRegisterSms(phone);
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
                        onReturnDataListener.getRegisterSmsFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(Message bean) {
                        onReturnDataListener.getRegisterSms(bean.getDetail());
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);

    }

    @Override
    public void register(String phone, String sms, String pwd) {
        Observable<RegBean> request = RxHttp.getInstance().register(phone, sms, pwd);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<RegBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
//                        onReturnDataListener.getRegisterSmsFail(ex.getMessage());
                        onReturnDataListener.registerFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(RegBean bean) {
//                        onReturnDataListener.getRegisterSms(bean.getDetail());
                        onReturnDataListener.registerDone(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);

    }

}
