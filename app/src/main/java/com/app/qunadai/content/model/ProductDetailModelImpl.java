package com.app.qunadai.content.model;

import com.app.qunadai.bean.ApplyBean;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.ProductDetailBean;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.ProductDetailContract;
import com.app.qunadai.content.contract.ProductsContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.LogU;
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

public class ProductDetailModelImpl implements ProductDetailContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public ProductDetailModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener{
        void getProductDetail(ProductDetailBean bean);

        void getProductDetailFail(String error);

        void getPersonValue(PersonBean bean);

        void getPersonValueFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void requestProductDetail(String pid) {
        Observable<ProductDetailBean> request = RxHttp.getInstance().getLoanDetail(pid);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ProductDetailBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getProductDetailFail(ex.getDisplayMessage());
                    }

                    @Override
                    protected void onOk(ProductDetailBean bean) {
                        onReturnDataListener.getProductDetail(bean);
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

    @Override
    public void applyOrder(String token, String amount, String time, String timeType, String productId, String type) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("loanAmount", amount);
            obj.put("timeLimit", time);
            obj.put("timeLimitType", timeType);
            obj.put("id", productId);
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
    public void getServerData() {

    }

}
