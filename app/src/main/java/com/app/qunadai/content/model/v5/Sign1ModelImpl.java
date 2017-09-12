package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.Message;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.v5.Sign1Contract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/9/11.
 */

public class Sign1ModelImpl implements Sign1Contract.Model {

    private OnReturnDataListener onReturnDataListener;

    public Sign1ModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {

        void checkPhone(BaseBean<IsExist> bean);

        void checkPhoneFail(String error);

        void loginDone(BaseBean<Token> token);

        void loginFail(String error);

        void getRegisterSms(BaseBean<SmsBean> bean);

        void getRegisterSmsFail(String error);

        void getLoginSms(BaseBean<SmsBean> bean);

        void getLoginSmsFail(String error);

        void getForgetSms(BaseBean<SmsBean> bean);

        void getForgetSmsFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void checkPhone(String phone) {
        Observable<BaseBean<IsExist>> request = RxHttp.getInstance().checkMobile(phone);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<IsExist>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.checkPhoneFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<IsExist> bean) {
                        onReturnDataListener.checkPhone(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void loginByPwd(String phone, String pwd, String imei) {
        Observable<BaseBean<Token>> request = RxHttp.getInstance().loginByPwd(phone, pwd, imei);
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
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<Token> bean) {
                        onReturnDataListener.loginDone(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
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
    public void sendForgetSms(String phone) {
        Observable<BaseBean<SmsBean>> request = RxHttp.getInstance().getForgetSms5(phone);
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
                        onReturnDataListener.getForgetSmsFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BaseBean<SmsBean> bean) {
                        onReturnDataListener.getForgetSms(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
