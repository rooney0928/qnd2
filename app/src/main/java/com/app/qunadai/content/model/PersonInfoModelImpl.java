package com.app.qunadai.content.model;

import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.PersonInfo;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.PersonInfoContract;
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
 * Created by wayne on 2017/5/13.
 */

public class PersonInfoModelImpl implements PersonInfoContract.Model {
    private OnReturnDataListener onReturnDataListener;

    public PersonInfoModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {
        void getPersonInfo(PersonInfo bean);

        void getPersonInfoFail(String error);

        void setPersonInfo(PersonInfo bean);

        void setPersonInfoFail(String error);

        void getPersonValue(PersonBean bean);

        void getPersonValueFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestPersonInfo(String token) {
        Observable<PersonInfo> request = RxHttp.getInstance().getPersonInfo(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PersonInfo>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPersonInfoFail(ex.getDisplayMessage());
                        if(ex.isTokenFail()){
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(PersonInfo bean) {
                        onReturnDataListener.getPersonInfo(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void setPersonInfo(String token, String amount, String period, String job, String income, String edu, String marry, String address) {
        JSONObject obj = new JSONObject();

        try {
            obj.put("loanAmount", amount);
            obj.put("loanDeadLine", period);
            obj.put("employmentStatus", job);
            obj.put("householdIncome", income);
            obj.put("educationLevel", edu);
            obj.put("maritalStatus", marry);
            obj.put("habitualResidence", address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        Observable<PersonInfo> request = RxHttp.getInstance().setPersonInfo(token, body);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PersonInfo>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.setPersonInfoFail(ex.getDisplayMessage());
                        if(ex.isTokenFail()){
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(PersonInfo bean) {
                        onReturnDataListener.setPersonInfo(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void requestPersonValue(String token) {
        Observable<PersonBean> request = RxHttp.getInstance().getPersonValue(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<PersonBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getPersonValueFail(ex.getDisplayMessage());
                        if(ex.isTokenFail()){
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(PersonBean bean) {
                        onReturnDataListener.getPersonValue(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
