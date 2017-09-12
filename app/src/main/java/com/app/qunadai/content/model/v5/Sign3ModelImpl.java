package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.RegBean;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.content.contract.v5.Sign3Contract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/9/12.
 */

public class Sign3ModelImpl implements Sign3Contract.Model {
    private OnReturnDataListener onReturnDataListener;

    public Sign3ModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener{

        void registerDone(BaseBean<Token> bean);

        void registerFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void register(String phone, String sms, String pwd) {
        Observable<BaseBean<Token>> request = RxHttp.getInstance().register5(phone, sms, pwd);
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
//                        onReturnDataListener.getRegisterSmsFail(ex.getMessage());
                        onReturnDataListener.registerFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BaseBean<Token> bean) {
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
