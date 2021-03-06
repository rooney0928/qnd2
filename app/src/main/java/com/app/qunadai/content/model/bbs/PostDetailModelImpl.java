package com.app.qunadai.content.model.bbs;

import com.app.qunadai.bean.bbs.CommentList;
import com.app.qunadai.bean.bbs.PostBean;
import com.app.qunadai.bean.bbs.PraiseBean;
import com.app.qunadai.bean.bbs.SendCommentBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.bbs.PostDetailContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.RxHolder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/7/7.
 */

public class PostDetailModelImpl implements PostDetailContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public PostDetailModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {

        void getPostDetail(PostBean bean);

        void getPostDetailFail(String error);

        void commentList(CommentList bean);

        void commentListMore(CommentList bean);

        void commentListFail(String error);

        void sendComment(SendCommentBean bean);

        void sendCommentFail(String error);

        void praisePost(PraiseBean bean);

        void praisePostFail(String error);

        void cancelPraisePost(PraiseBean bean);

        void cancelPraisePostFail(String error);

        void requestStart();

        void requestEnd();
    }


    @Override
    public void getServerData() {

    }

    @Override
    public void getPostDetail(String aid, String token) {
        Observable<PostBean> request = RxHttp.getInstance().getPostDetail(aid, token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PostBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPostDetailFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PostBean bean) {
                        onReturnDataListener.getPostDetail(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void getPostDetailNoUser(String aid) {
        Observable<PostBean> request = RxHttp.getInstance().getPostDetailNoUser(aid);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PostBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPostDetailFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PostBean bean) {
                        onReturnDataListener.getPostDetail(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void getCommentList(String token, String aid, final int page, int size) {
        if (CommUtil.isNull(token)) {
            onReturnDataListener.tokenFail();
        } else {
            Observable<CommentList> request = RxHttp.getInstance().getCommentList(token, aid, page, size);
            Subscription sub = request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RxSubscriber<CommentList>() {
                        @Override
                        public void onStart() {
                            onReturnDataListener.requestStart();
                            super.onStart();
                        }

                        @Override
                        protected void onError(ApiException ex) {
                            onReturnDataListener.commentListFail(ex.getDisplayMessage());
                        }

                        @Override
                        protected void onOk(CommentList bean) {
                            if (page == 0) {
                                onReturnDataListener.commentList(bean);
                            } else {
                                onReturnDataListener.commentListMore(bean);
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

    @Override
    public void getCommentListNoUser(String aid, final int page, int size) {
        Observable<CommentList> request = RxHttp.getInstance().getCommentListNoUser(aid, page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CommentList>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.commentListFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(CommentList bean) {
                        if (page == 0) {
                            onReturnDataListener.commentList(bean);
                        } else {
                            onReturnDataListener.commentListMore(bean);
                        }
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void sendComment(String token, String aid, String content) {


        JSONObject obj = new JSONObject();
        try {
            obj.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());


        Observable<SendCommentBean> request = RxHttp.getInstance().sendComment(token, aid, body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<SendCommentBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.sendCommentFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(SendCommentBean bean) {
                        onReturnDataListener.sendComment(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void praisePost(String aid, String token) {
        Observable<PraiseBean> request = RxHttp.getInstance().praisePost(aid, token);
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
                        onReturnDataListener.praisePostFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PraiseBean bean) {
                        onReturnDataListener.praisePost(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void cancelPraisePost(String aid, String token) {
        Observable<PraiseBean> request = RxHttp.getInstance().cancelPraisePost(aid, token);
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
                        onReturnDataListener.cancelPraisePostFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(PraiseBean bean) {
                        onReturnDataListener.cancelPraisePost(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
