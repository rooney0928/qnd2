package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.FeedBack;
import com.app.qunadai.content.contract.v5.FeedbackContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/11/6.
 */

public class FeedbackModelImpl implements FeedbackContract.Model {
    private OnReturnDataListener onReturnDataListener;

    public FeedbackModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {

        void addFeedback(BaseBean<FeedBack> bean);

        void addFeedbackFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void addFeedback(String token, String msg) {
        Observable<BaseBean<FeedBack>> request = RxHttp.getInstance().addFeedback(token,msg);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<FeedBack>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.addFeedbackFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<FeedBack> bean) {
                        onReturnDataListener.addFeedback(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
