package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ExploreBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.v5.ExploreContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/9/15.
 */

public class ExploreModelImpl implements ExploreContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public ExploreModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener{

        void getExplore(BaseBean<ExploreBean> bean);

        void getExploreFail(String error);

        void clearExplore(BaseBean bean);

        void clearExploreFail(String error);

        void requestStart();

        void requestEnd();
    }
    @Override
    public void getServerData() {

    }

    @Override
    public void getExplore(String token) {
        Observable<BaseBean<ExploreBean>> request = RxHttp.getInstance().getExplore(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<ExploreBean>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getExploreFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<ExploreBean> bean) {
                        onReturnDataListener.getExplore(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void clearExplore(String token) {
        Observable<BaseBean> request = RxHttp.getInstance().clearExplore(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.clearExploreFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean bean) {
                        onReturnDataListener.clearExplore(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
