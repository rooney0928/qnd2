package com.app.qunadai.content.model;

import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.ProductDetailBean;
import com.app.qunadai.content.contract.ProductDetailContract;
import com.app.qunadai.content.contract.ProductsContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

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

    public interface OnReturnDataListener {
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
                        onReturnDataListener.getProductDetailFail(ex.getMessage());
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
                        onReturnDataListener.getPersonValueFail(ex.getMessage());
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
    public void getServerData() {

    }

}
