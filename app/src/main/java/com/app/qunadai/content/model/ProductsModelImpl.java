package com.app.qunadai.content.model;

import com.app.qunadai.bean.ProductsBean;
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
 * Created by wayne on 2017/5/12.
 */

public class ProductsModelImpl implements ProductsContract.Model {
    private static int REFRESH = 0;
    private static int LOAD_MORE = 1;
    private OnReturnDataListener onReturnDataListener;

    public ProductsModelImpl(OnReturnDataListener onReturnDataListener) {
        this.onReturnDataListener = onReturnDataListener;
    }

    public interface OnReturnDataListener {
        void requestProducts(ProductsBean bean);

        void requestProductsMore(ProductsBean bean);

        void requestProductsFail(String error);

        void requestStart();

        void requestEnd();
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void requestProducts(int page, int pageSize, String tagName, String amount, String term) {
        getProducts(REFRESH, page, pageSize, tagName, amount, term);
    }

    @Override
    public void requestProductsMore(int page, int pageSize, String tagName, String amount, String term) {
        getProducts(LOAD_MORE, page, pageSize, tagName, amount, term);
    }

    public void getProducts(final int type, int page, int pageSize, String tagName, String amount, String term) {
        Observable<ProductsBean> request = RxHttp.getInstance().getFilterLoan(page, pageSize, tagName, amount, term);
        Subscription sub = request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<ProductsBean>() {
                    @Override
                    public void onStart() {
                        onReturnDataListener.requestStart();
                        super.onStart();
                    }

                    @Override
                    protected void onError(ApiException ex) {
//                        onReturnDataListener.loginFail(ex.getMessage());
                        onReturnDataListener.requestProductsFail(ex.getMessage());
                    }

                    @Override
                    protected void onOk(ProductsBean bean) {
//                        onReturnDataListener.loginDone(token);
                        if (type == REFRESH) {
                            onReturnDataListener.requestProducts(bean);
                        } else {
                            onReturnDataListener.requestProductsMore(bean);
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
