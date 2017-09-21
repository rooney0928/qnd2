package com.app.qunadai.content.presenter.v5;

import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.content.contract.v5.Product5DetailContract;
import com.app.qunadai.content.model.v5.Product5DetailModelImpl;
import com.app.qunadai.utils.CommUtil;

/**
 * Created by wayne on 2017/9/14.
 */

public class Product5DetailPresenter implements Product5DetailContract.Presenter {
    private Product5DetailContract.View view;
    private Product5DetailContract.Model model;

    public Product5DetailPresenter(Product5DetailContract.View iview) {
        this.view = iview;
        model = new Product5DetailModelImpl(new Product5DetailModelImpl.OnReturnDataListener() {
            @Override
            public void tokenFail() {
                view.tokenFail();
            }

            @Override
            public void getProduct5Detail(BaseBean<Product5DetailBean> bean) {
                view.getProduct5Detail(bean);
            }

            @Override
            public void getProduct5DetailFail(String error) {
                view.getProduct5DetailFail(error);
            }

            @Override
            public void getProduct5Comments(BaseBean<ProComments> bean) {
                view.getProduct5Comments(bean);
            }

            @Override
            public void getProduct5CommentsMore(BaseBean<ProComments> bean) {
                view.getProduct5CommentsMore(bean);
            }

            @Override
            public void getProduct5CommentsFail(String error) {
                view.getProduct5CommentsFail(error);
            }

            @Override
            public void requestStart() {
                view.requestStart();
            }

            @Override
            public void requestEnd() {
                view.requestEnd();
            }
        });
    }

    @Override
    public void getServerData() {

    }

    @Override
    public void getProduct5Detail(String pid, String token) {
        model.getProduct5Detail(pid, CommUtil.isNull(token) ? null : token);
    }

    @Override
    public void getProduct5Comments(String pid, int page, int size) {
        model.getProduct5Comments(pid, page, size);
    }

    @Override
    public void applyOrder(String token, String productId) {
        model.applyOrder(token, productId);
    }
}
