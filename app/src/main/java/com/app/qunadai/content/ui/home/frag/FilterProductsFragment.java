package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.bean.v5.ProductsFilter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ProductAdapter;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.ProductsFilterContract;
import com.app.qunadai.content.presenter.v5.ProductsFilterPresenter;
import com.app.qunadai.third.eventbus.EventOffline;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/13.
 */

public class FilterProductsFragment extends BaseFragment implements ProductsFilterContract.View {
    private ProductsFilterPresenter productsFilterPresenter;
    private static final int PAGE_SIZE = 10;
    int page = 0;
    String type;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;

    ProductAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    public List<Product> list;
    int lastVisibleItem;



    public static FilterProductsFragment getInstance(String type, int banner) {
        FilterProductsFragment filterProductsFragment = new FilterProductsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putInt("banner", banner);
        filterProductsFragment.setArguments(bundle);
        return filterProductsFragment;
    }


    @Override
    protected void initBundle(Bundle savedInstanceState) {
//        type = savedInstanceState.getString("type");
        Bundle args = getArguments();
        type = args.getString("type");
//        banner = args.getInt("banner");
    }

    @Override
    protected void initData() {
//        ImgUtil.loadImg(getActivity(), banner, iv_banner_title);
        productsFilterPresenter = new ProductsFilterPresenter(this);
        adapter = new ProductAdapter(getActivity());
        list = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp8);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setNestedScrollingEnabled(false);

        rv_list.setAdapter(adapter);

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                productsFilterPresenter.getProducts(type, page, PAGE_SIZE);
            }
        });

        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    productsFilterPresenter.getProducts(type, page, PAGE_SIZE);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });

        if(NetworkUtil.checkNetwork(getActivity())){
            productsFilterPresenter.getProducts(type, page, PAGE_SIZE);
        }else{
            EventBus.getDefault().post(new EventOffline("filterPro"));
        }


    }

    public void refresh(){
        page = 0;
        productsFilterPresenter.getProducts(type, page, PAGE_SIZE);
    }

    @Override
    protected View createRootView() {
        View view = View.inflate(getActivity(), R.layout.fragment_filter_pros, null);
        return view;
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
    public void getProducts(BaseBean<ProductsFilter> bean) {
//刷新
//        List<LoanDetail> productList = bean.getContent().getPages().getContent();
        List<Product> tempList = bean.getContent().getProductList().getContent();
        list.clear();
        list = tempList;
        adapter.setList(list);
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getProductsMore(BaseBean<ProductsFilter> bean) {
//加载更多
        List<Product> tempList = bean.getContent().getProductList().getContent();

        if (tempList.size() > 0) {
            list.addAll(tempList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(getActivity(), "没有更多数据");
        }

    }

    @Override
    public void getProductsFail(String error) {
        ToastUtil.showToast(getActivity(), error);
    }
}
