package com.app.qunadai.content.ui.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.bean.v5.ProductsNew;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ProductAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.ProductsNewContract;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.ProductsNewPresenter;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/10/26.
 */

public class ProductsNewActivity extends BaseActivity implements ProductsNewContract.View {

    private ProductsNewPresenter productsNewPresenter;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    ProductAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    public List<Product> list;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("最新口子");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_recommend, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        productsNewPresenter = new ProductsNewPresenter(this);
        adapter = new ProductAdapter(this);

        list = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp8);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setNestedScrollingEnabled(false);

        rv_list.setAdapter(adapter);

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestProducts();
            }
        });

        setOnReLinkListener(new OnReLinkListener() {
            @Override
            public void doNewRequest() {
                requestProducts();
            }
        });
        if(NetworkUtil.checkNetwork(this)){
            productsNewPresenter.getProductsNew();
            if (!CommUtil.isNull(getToken())) {
                productsNewPresenter.lookProductsNew(getToken());
            }
        }else{
            setViewOffline();

        }


    }
    public void requestProducts() {
        if (NetworkUtil.checkNetwork(this)) {
            productsNewPresenter.getProductsNew();
        } else {
            setViewOffline();
        }

    }

    @Override
    public void initViewData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        CommUtil.tcStart(this,"Visit-Newest loan");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CommUtil.tcEnd(this,"Visit-Newest loan");
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {
        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            swipe_layout.setRefreshing(false);
        }
    }


    @Override
    public void getProductsNew(BaseBean<ProductsNew> bean) {
        List<Product> productList = bean.getContent().getProductList().getContent();
        list.clear();
        list = productList;
        adapter.setList(list);
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getProductsNewFail(String error) {
        ToastUtil.showToast(this, error);

    }
}
