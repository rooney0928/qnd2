package com.app.qunadai.content.model;

import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/10.
 */

public class HomeModelImpl implements HomeContract.Model {
    private OnReturnDataListener mOnReturnDataListener;

    public HomeModelImpl(OnReturnDataListener onReturnDataListener) {
        this.mOnReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener{
        void getHomeRecommend(HomeRecommend bean);
        void getHomeRecommendError(String error);

        void requestStart();
        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getHomeRecommend() {
        Observable<HomeRecommend> request = RxHttp.getInstance().getRecommend();
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<HomeRecommend>() {
                    @Override
                    public void onStart() {
                        mOnReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        mOnReturnDataListener.getHomeRecommendError(ex.getMessage());
                    }

                    @Override
                    protected void onOk(HomeRecommend bean) {
                        mOnReturnDataListener.getHomeRecommend(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        mOnReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
