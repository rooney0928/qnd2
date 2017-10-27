package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProductsNew;
import com.app.qunadai.content.base.BaseReturnListener;
import com.app.qunadai.content.contract.v5.ProductsNewContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/10/26.
 */

public class ProductsNewModelImpl implements ProductsNewContract.Model {


    private OnReturnDataListener onReturnDataListener;

    public ProductsNewModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener extends BaseReturnListener {

        void getProductsNew(BaseBean<ProductsNew> bean);

        void getProductsNewFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getProductsNew() {
        Observable<BaseBean<ProductsNew>> request = RxHttp.getInstance().getProductsNew();
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<ProductsNew>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getProductsNewFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<ProductsNew> bean) {
                        onReturnDataListener.getProductsNew(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }

    @Override
    public void lookProductsNew(String token) {
        Observable<BaseBean> request = RxHttp.getInstance().lookProductsNew(token);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
//                        onReturnDataListener.getProductsNewFail(ex.getDisplayMessage());
//                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
//                        }
                    }

                    @Override
                    protected void onOk(BaseBean bean) {
//                        onReturnDataListener.getProductsNew(bean);
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
