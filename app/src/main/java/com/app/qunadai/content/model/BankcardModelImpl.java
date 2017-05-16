package com.app.qunadai.content.model;

import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.content.contract.BankcardContract;
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
 * Created by wayne on 2017/5/14.
 */

public class BankcardModelImpl implements BankcardContract.Model{

    private OnReturnDataListener onReturnDataListener;

    public BankcardModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void getBankcard(BankcardBean bean);

        void getBankcardFail(String error);

        void setBankcard(BankcardBean bean);

        void setBankcardFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {
        
    }

    @Override
    public void requestBankcard(String token) {
        Observable<BankcardBean> request = RxHttp.getInstance().getBankcard(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BankcardBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getBankcardFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BankcardBean bean) {
                        onReturnDataListener.getBankcard(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void setBankcard(String token, String name, String bankcard, String idcard, String phone) {
        JSONObject obj = new JSONObject();

        try {
            obj.put("name", name);
            obj.put("bankCardNumber", bankcard);
            obj.put("idNumber", idcard);
            obj.put("mobileNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<BankcardBean> request = RxHttp.getInstance().setBankcard(token,body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BankcardBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.setBankcardFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(BankcardBean bean) {
                        onReturnDataListener.setBankcard(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
