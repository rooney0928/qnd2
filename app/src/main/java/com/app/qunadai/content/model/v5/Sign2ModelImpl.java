package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.contract.v5.Sign1Contract;
import com.app.qunadai.content.contract.v5.Sign2Contract;
import com.app.qunadai.content.model.LoginModelImpl;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/9/11.
 */

public class Sign2ModelImpl implements Sign2Contract.Model {

    private OnReturnDataListener onReturnDataListener;

    public Sign2ModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void getRegisterSms(BaseBean<SmsBean> bean);

        void getRegisterSmsFail(String error);

        void getLoginSms(BaseBean<SmsBean> bean);

        void getLoginSmsFail(String error);

        void loginDone(BaseBean<Token> token);

        void loginFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }


    @Override
    public void sendRegSms(String phone) {
        Observable<BaseBean<SmsBean>> request = RxHttp.getInstance().getRegSms(phone);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<SmsBean>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getRegisterSmsFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<SmsBean> bean) {
                        onReturnDataListener.getRegisterSms(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void sendLoginSms(String phone) {
        Observable<BaseBean<SmsBean>> request = RxHttp.getInstance().getLogin5Sms(phone);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<SmsBean>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getLoginSmsFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BaseBean<SmsBean> bean) {
                        onReturnDataListener.getLoginSms(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void loginBySms(String phone, String sms) {
        Observable<BaseBean<Token>> request = RxHttp.getInstance().loginBySms(phone, sms);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<Token>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.loginFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BaseBean<Token> token) {
                        onReturnDataListener.loginDone(token);

                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
