package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.ApplyBean;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.NewReply;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.v5.Product5DetailContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/9/14.
 */

public class Product5DetailModelImpl implements Product5DetailContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public Product5DetailModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {

        void getProduct5Detail(BaseBean<Product5DetailBean> bean);

        void getProduct5DetailFail(String error);

        void getProduct5Comments(BaseBean<ProComments> bean);

        void getProduct5CommentsMore(BaseBean<ProComments> bean);

        void getProduct5CommentsFail(String error);

        void sendNewReply(BaseBean<NewReply> bean);

        void sendNewReplyFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getProduct5Detail(String pid, String token) {
        Observable<BaseBean<Product5DetailBean>> request = RxHttp.getInstance().getProducts5Detail(pid, token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<Product5DetailBean>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getProduct5DetailFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<Product5DetailBean> bean) {
                        onReturnDataListener.getProduct5Detail(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void getProduct5Comments(String pid, final int page, int size) {
        Observable<BaseBean<ProComments>> request = RxHttp.getInstance().getProductComments(pid, page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<ProComments>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getProduct5CommentsFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<ProComments> bean) {
                        if (page > 0) {
                            onReturnDataListener.getProduct5CommentsMore(bean);
                        } else {
                            onReturnDataListener.getProduct5Comments(bean);
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
    public void applyOrder(String token, String productId) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("productId", productId);
//            obj.put("type", "H5");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        LogU.t(obj.toString());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<ApplyBean> request = RxHttp.getInstance().apply(token, body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ApplyBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                    }

                    @Override
                    protected void onOk(ApplyBean bean) {
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void sendNewReply(String cid, String token, String content) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());


        Observable<BaseBean<NewReply>> request = RxHttp.getInstance().sendNewReply(cid, token, body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<NewReply>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.sendNewReplyFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BaseBean<NewReply> bean) {
                        onReturnDataListener.sendNewReply(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
