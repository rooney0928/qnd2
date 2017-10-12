package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.NewReply;
import com.app.qunadai.bean.v5.Replies;
import com.app.qunadai.content.contract.v5.RepliesContract;
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
 * Created by wayne on 2017/10/11.
 */

public class RepliesModelImpl implements RepliesContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public RepliesModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {

        void getReplies(BaseBean<Replies> bean);

        void getRepliesMore(BaseBean<Replies> bean);

        void getRepliesFail(String error);

        void sendNewReply(BaseBean<NewReply> bean);

        void sendNewReplyFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getReplies(String cid, final int page, int size) {
        Observable<BaseBean<Replies>> request = RxHttp.getInstance().getRepliesByComment(cid, page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<Replies>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getRepliesFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<Replies> bean) {
                        if (page > 0) {
                            onReturnDataListener.getRepliesMore(bean);
                        } else {
                            onReturnDataListener.getReplies(bean);
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
