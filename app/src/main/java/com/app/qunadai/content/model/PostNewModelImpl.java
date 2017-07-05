package com.app.qunadai.content.model;

import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.bbs.PostNewContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/6/30.
 */

public class PostNewModelImpl implements PostNewContract.Model {
    private OnReturnDataListener onReturnDataListener;

    public PostNewModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void postNew(PostNewBean bean);

        void postNewFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void postNew(String token, String title, String content, String[] pics) {

        JSONObject obj = new JSONObject();

        JSONArray arr = new JSONArray();
        for (String pic : pics) {
            arr.put(pic);
        }

        try {
            obj.put("title",title);
            obj.put("content",content);
            obj.put("pictures",arr);

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());
            Observable<PostNewBean> request = RxHttp.getInstance().postNew(token, body);
            Subscription sub = request.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RxSubscriber<PostNewBean>() {
                        @Override
                        public void onStart() {
                            onReturnDataListener.requestStart();
                            super.onStart();
                        }

                        @Override
                        protected void onError(ApiException ex) {
                            onReturnDataListener.postNewFail(ex.getDisplayMessage());
                            if (ex.isTokenFail()) {
                                onReturnDataListener.tokenFail();
                            }
                        }

                        @Override
                        protected void onOk(PostNewBean bean) {
                            onReturnDataListener.postNew(bean);
                        }

                        @Override
                        protected void requestEnd() {
                            onReturnDataListener.requestEnd();
                        }
                    });
            RxHolder.addSubscription(sub);
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
