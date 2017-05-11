package com.app.qunadai.content.model;

import com.app.qunadai.bean.Message;
import com.app.qunadai.content.contract.RegisterContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.RxHolder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.https.HttpsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/5/11.
 */

public class RegisterModelImpl implements RegisterContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public RegisterModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void getRegisterSms(String msg);

        void getRegisterSmsFail(String error);

        void registerDone(String str);

        void registerFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestRegisterSms(String phone) {
        Observable<Message> request = RxHttp.getInstance().getRegisterSms(phone);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<Message>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getRegisterSmsFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(Message bean) {
                        onReturnDataListener.getRegisterSms(bean.getDetail());
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);

    }

    @Override
    public void register(String phone, String sms, String pwd) {
//        try {
//            JSONObject obj = new JSONObject();
//            obj.put("filter", "mobile");
//            obj.put("mobileNumber", phone);
//            obj.put("sha1password", pwd);
//            obj.put("smsActivateCode", sms);
////            String url = RxHttp.ROOT + "users/activate?filter=mobile&mobile="+phone+"&sha1password="+pwd+"&smsActivateCode="+sms;
//            OkHttpUtils
//                    .put()//also can use delete() ,head() , patch()
//                    .url(RxHttp.ROOT + "users/activate")
//                    .requestBody(RequestBody.create(null, obj.toString()))//
//                    .build()//
//                    .execute(new Callback(){
//
//
//                        @Override
//                        public Object parseNetworkResponse(Response response, int id) throws Exception {
//                            return null;
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            LogU.t("?"+e.getMessage());
//                        }
//
//                        @Override
//                        public void onResponse(Object response, int id) {
//                            LogU.t("?"+response.toString());
//                        }
//                    });
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//        RequestBody body = new FormBody.Builder()
//                .add("filter", "mobile")
//                .add("mobileNumber", phone)
//                .add("sha1password", pwd)
//                .add("smsActivateCode", sms).build();


//        Observable<String> request = RxHttp.getInstance().register(phone, sms, pwd);
//        Subscription sub = request.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RxSubscriber<String>() {
//                    @Override
//                    public void onStart() {
//                        onReturnDataListener.requestStart();
//                        super.onStart();
//                    }
//
//                    @Override
//                    protected void onError(ApiException ex) {
////                        onReturnDataListener.getRegisterSmsFail(ex.getMessage());
//                        onReturnDataListener.registerFail(ex.getMessage());
//                    }
//
//                    @Override
//                    protected void onOk(String str) {
////                        onReturnDataListener.getRegisterSms(bean.getDetail());
//                        onReturnDataListener.registerDone(str);
//                    }
//
//                    @Override
//                    protected void requestEnd() {
//                        onReturnDataListener.requestEnd();
//                    }
//                });
//        RxHolder.addSubscription(sub);


        AsyncHttpClient myClient = new AsyncHttpClient();
        String url = RxHttp.ROOT + "users/activate?filter=mobile&mobileNumber=" + phone + "&sha1password=" + pwd + "&smsActivateCode=" + sms;
        myClient.put(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                onReturnDataListener.requestStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    String result = obj.optString("detail");
                    onReturnDataListener.registerDone(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    JSONObject obj = new JSONObject(new String(responseBody));
                    String result = obj.optString("detail");
                    onReturnDataListener.registerFail(result);
                } catch (JSONException e) {
//                    e.printStackTrace();
                    onReturnDataListener.registerFail("注册失败");
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                onReturnDataListener.requestEnd();
            }
        });

    }

}
