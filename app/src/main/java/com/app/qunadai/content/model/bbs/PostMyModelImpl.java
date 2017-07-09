package com.app.qunadai.content.model.bbs;

import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.bbs.PostMyContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/7/4.
 */

public class PostMyModelImpl implements PostMyContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public PostMyModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getPostList(PostListBean bean);

        void getPostListMore(PostListBean bean);

        void getPostListFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getPostList(String token, final int page, int size) {

        Observable<PostListBean> request = RxHttp.getInstance().getMyPostList(token, page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PostListBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPostListFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(PostListBean bean) {
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

    private void getPostListData(final int type, String token, int page, int size) {

    }


}
