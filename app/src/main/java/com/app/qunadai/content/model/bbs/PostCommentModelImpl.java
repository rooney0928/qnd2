package com.app.qunadai.content.model.bbs;

import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.bbs.PostCommentContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/7/10.
 */

public class PostCommentModelImpl implements PostCommentContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public PostCommentModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {


        void praiseComment(PraiseBean bean);

        void praiseCommentFail(String error);

        void cancelPraiseComment(PraiseBean bean);

        void cancelPraiseCommentFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void praiseComment(String cid, String token) {
        Observable<PraiseBean> request = RxHttp.getInstance().praiseComment(cid, token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PraiseBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.praiseCommentFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PraiseBean bean) {
                        onReturnDataListener.praiseComment(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void cancelPraiseComment(String cid, String token) {
        Observable<PraiseBean> request = RxHttp.getInstance().cancelPraiseComment(cid, token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PraiseBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.cancelPraiseCommentFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PraiseBean bean) {
                        onReturnDataListener.cancelPraiseComment(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
