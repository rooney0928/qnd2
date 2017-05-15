package com.app.qunadai.content.model;

import com.app.qunadai.bean.Token;
import com.app.qunadai.content.contract.SplashContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/15.
 */

public class SplashModelImpl implements SplashContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public SplashModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void loginDone(Token token);

        void loginFail(String error);


        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void loginByPwd(String phone, String pwd) {
        Observable<Token> request = RxHttp.getInstance().loginByPwd(phone, pwd);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Token>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.loginFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(Token token) {
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
