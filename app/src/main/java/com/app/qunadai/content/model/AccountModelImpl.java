package com.app.qunadai.content.model;

import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.AccountContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/15.
 */

public class AccountModelImpl implements AccountContract.Model {
    private OnReturnDataListener onReturnDataListener;

    public AccountModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener{
        void uploadAvatar(AvatarBean bean);

        void uploadAvatarFail(String error);

        void requestStart();

        void requestEnd();

    }


    @Override
    public void getServerData() {

    }

    @Override
    public void uploadAvatar(String token, String base64) {

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("text/plain"), base64);
        Observable<AvatarBean> request= RxHttp.getInstance().uploadAvatar(token,body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<AvatarBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.uploadAvatarFail(ex.getDisplayMessage());

                        if(ex.isTokenFail()){
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(AvatarBean bean) {
                        onReturnDataListener.uploadAvatar(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
