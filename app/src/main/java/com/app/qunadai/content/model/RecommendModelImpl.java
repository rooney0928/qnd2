package com.app.qunadai.content.model;

import com.app.qunadai.bean.Recommend;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.RecommendContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/12.
 */

public class RecommendModelImpl implements RecommendContract.Model {
    private static int REFRESH = 0;
    private static int LOAD_MORE = 1;
    private OnReturnDataListener onReturnDataListener;

    public RecommendModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getRecommend(Recommend bean);

        void getRecommendMore(Recommend bean);

        void getRecommendFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getRecommend(int page, int pageSize) {
        requestRecommend(REFRESH, page, pageSize);
    }

    @Override
    public void getRecommendMore(int page, int pageSize) {
        requestRecommend(LOAD_MORE, page, pageSize);
    }

    public void requestRecommend(final int type, int page, int pageSize) {
        Observable<Recommend> request = RxHttp.getInstance().getRecommend(page, pageSize);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Recommend>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getRecommendFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(Recommend bean) {
                        if (type == REFRESH) {
                            onReturnDataListener.getRecommend(bean);
                        } else {
                            onReturnDataListener.getRecommendMore(bean);
                        }
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
