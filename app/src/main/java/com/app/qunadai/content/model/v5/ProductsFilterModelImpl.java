package com.app.qunadai.content.model.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.content.contract.v5.ProductsFilterContract;
import com.app.qunadai.http.ApiException;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.http.RxSubscriber;
import com.app.qunadai.utils.RxHolder;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wayne on 2017/9/13.
 */

public class ProductsFilterModelImpl implements ProductsFilterContract.Model {

    private OnReturnDataListener onReturnDataListener;

    public ProductsFilterModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {

        void getProducts(BaseBean<ProductsFilter> bean);

        void getProductsMore(BaseBean<ProductsFilter> bean);

        void getProductsFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getProducts(String prop, final int page, int size) {
        Observable<BaseBean<ProductsFilter>> request = RxHttp.getInstance().getProductsFilter(prop, page, size);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<BaseBean<ProductsFilter>>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        onReturnDataListener.getProductsFail(ex.getDisplayMessage());
                        if (ex.isTokenFail()) {
//                            onReturnDataListener.tokenFail();
                        }
                    }

                    @Override
                    protected void onOk(BaseBean<ProductsFilter> bean) {
                        if (page > 0) {
                            onReturnDataListener.getProductsMore(bean);
                        } else {
                            onReturnDataListener.getProducts(bean);
                        }
                    }

                    @Override
                    protected void requestEnd() {
                        onReturnDataListener.requestEnd();
                    }
                });
        RxHolder.addSubscription(sub);
    }
}
