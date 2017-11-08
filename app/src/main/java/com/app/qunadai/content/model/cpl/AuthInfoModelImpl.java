package com.app.qunadai.content.model.cpl;

import com.app.qunadai.MyApp;
import com.app.qunadai.bean.base.CplBase;
import com.app.qunadai.bean.cpl.CToken;
import com.app.qunadai.bean.cpl.UserInfo;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.cpl.AuthInfoContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.CplHttp;
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
 * Created by wayne on 2017/11/8.
 */

public class AuthInfoModelImpl implements AuthInfoContract.Model {


    private OnReturnDataListener onReturnDataListener;

    public AuthInfoModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getCplToken(CplBase<CToken> bean);

        void getCplTokenFail(String error);

        void setCplUserInfo(CplBase bean);

        void setCplUserInfoFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void reqCplToken(String phone) {
        JSONObject obj = new JSONObject();

        try {
            obj.put("app_id", MyApp.CPL_ID);
            obj.put("app_psw", MyApp.CPL_PSW);
            obj.put("mobile_number", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<CplBase<CToken>> request = CplHttp.getInstance().reqToken(body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CplBase<CToken>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getCplTokenFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(CplBase<CToken> bean) {
                        onReturnDataListener.getCplToken(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void setCplUserInfo(UserInfo info,String token) {
        JSONObject obj = new JSONObject();

        try {
            obj.put("app_id", MyApp.CPL_ID);
            obj.put("token",token);
            obj.put("name", info.getName());
            obj.put("idcard_number", info.getIdcard());
            obj.put("wechat_number", info.getWechat());
            obj.put("qq_number", info.getQq());
            obj.put("education_type", info.getEdu());
            obj.put("shebao_type", info.getShebao());
            obj.put("province", info.getProvince());
            obj.put("city", info.getCity());
            obj.put("district", info.getDistrict());
            obj.put("living_address", info.getLiving());
            obj.put("contact1_name", info.getContactName());
            obj.put("contact1_type", info.getContactType());
            obj.put("contact1_cell", info.getContactCell());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<CplBase> request = CplHttp.getInstance().setUserInfo(body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<CplBase>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.setCplUserInfoFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(CplBase bean) {
                        onReturnDataListener.setCplUserInfo(bean);
                    }


                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
