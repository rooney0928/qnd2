package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ReplyMessages;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.v5.MessagesContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/11/7.
 */

public class MessagesModelImpl implements MessagesContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public MessagesModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getMessages(BaseBean<ReplyMessages> bean);

        void getMessagesFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getMessages(String token) {
        Observable<BaseBean<ReplyMessages>> request = RxHttp.getInstance().getReplyMessages(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<ReplyMessages>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getMessagesFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<ReplyMessages> bean) {
                        onReturnDataListener.getMessages(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
