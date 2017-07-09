package com.app.qunadai.content.model.bbs;

import com.app.qunadai.bean.bbs.StrategyBean;
import com.app.qunadai.bean.bbs.TalentBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.bbs.BBSHomeContract;
import com.app.qunadai.content.contract.bbs.TalentContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/7/9.
 */

public class TalentModelImpl implements TalentContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public TalentModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getPostList(TalentBean bean);

        void getPostListMore(TalentBean bean);

        void getPostListFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getPostList(final int page, int size) {
        Observable<TalentBean> request = RxHttp.getInstance().getTalent(page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<TalentBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPostListFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(TalentBean bean) {
                        if (page == 0) {
                            onReturnDataListener.getPostList(bean);
                        } else {
                            onReturnDataListener.getPostListMore(bean);
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
