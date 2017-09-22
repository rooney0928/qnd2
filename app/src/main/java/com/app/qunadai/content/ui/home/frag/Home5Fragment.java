package com.app.qunadai.content.ui.home.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.qunadai.R;
import com.app.qunadai.bean.Banner;
import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.Floors;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.bean.v5.Products;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.HomeFloorsAdapter;
import com.app.qunadai.content.adapter.v5.ProductAdapter;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Home5Contract;
import com.app.qunadai.content.presenter.v5.Home5Presenter;
import com.app.qunadai.content.ui.home.FilterProductsActivity;
import com.app.qunadai.content.ui.home.ProductsActivity;
import com.app.qunadai.content.ui.product.BrowserActivity;
import com.app.qunadai.content.ui.product.ProductDetailActivity;
import com.app.qunadai.content.view.FullRecyclerView;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ezy.ui.view.BannerView;

/**
 * Created by wayne on 2017/9/7.
 */

public class Home5Fragment extends BaseFragment implements Home5Contract.View, View.OnClickListener {

    private Home5Presenter home5Presenter;

    @BindView(R.id.banner)
    BannerView banner;
    @BindView(R.id.iv_home_banner)
    ImageView iv_home_banner;
    @BindView(R.id.rv_floors)
    FullRecyclerView rv_floors;
    @BindView(R.id.rv_pros)
    RecyclerView rv_pros;
    @BindView(R.id.swipe_home)
    SwipeRefreshLayout swipe_home;

    @BindView(R.id.ll_home_fast_loan)
    LinearLayout ll_home_fast_loan;
    @BindView(R.id.ll_home_low_rate)
    LinearLayout ll_home_low_rate;
    @BindView(R.id.ll_home_high_limit)
    LinearLayout ll_home_high_limit;
    @BindView(R.id.ll_home_long_term)
    LinearLayout ll_home_long_term;

    List<Floors.FloorsBean> floors;
    List<Product> products;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager2;
    HomeFloorsAdapter adapter;
    ProductAdapter productAdapter;


    public static Home5Fragment getInstance() {
        Home5Fragment homeFragment = new Home5Fragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_home5, null);
        return root;
    }

    @Override
    protected void initData() {
        home5Presenter = new Home5Presenter(this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        productAdapter = new ProductAdapter(getActivity());
        adapter = new HomeFloorsAdapter(getActivity());
        productAdapter = new ProductAdapter(getActivity());
        int spacingInPixels8 = getResources().getDimensionPixelSize(R.dimen.dp8);
        int spacingInPixels1 = getResources().getDimensionPixelSize(R.dimen.dp1);

        rv_pros.setAdapter(productAdapter);
        rv_pros.setLayoutManager(linearLayoutManager2);
        rv_pros.addItemDecoration(new SpaceItemDecoration(spacingInPixels8));
        rv_pros.setNestedScrollingEnabled(false);


//        rv_floors.addItemDecoration(new SpaceItemDecoration(spacingInPixels8));
        rv_floors.setNestedScrollingEnabled(false);

        rv_floors.setLayoutManager(linearLayoutManager);
        rv_floors.setAdapter(adapter);

        ll_home_fast_loan.setOnClickListener(this);
        ll_home_low_rate.setOnClickListener(this);
        ll_home_high_limit.setOnClickListener(this);
        ll_home_long_term.setOnClickListener(this);


        if (NetworkUtil.checkNetwork(getActivity())) {
            home5Presenter.getHomeFloors();
            home5Presenter.getHomeProducts();
            home5Presenter.getBanner();
        }

        swipe_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                home5Presenter.getHomeFloors();
                home5Presenter.getHomeProducts();
            }
        });


        final IntentFilter filter = new IntentFilter();
        // 屏幕灭屏广播
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        // 屏幕亮屏广播
        filter.addAction(Intent.ACTION_SCREEN_ON);
        // 屏幕解锁广播
//        filter.addAction(Intent.ACTION_USER_PRESENT);
        // 当长按电源键弹出“关机”对话或者锁屏时系统会发出这个广播
        // example：有时候会用到系统对话框，权限可能很高，会覆盖在锁屏界面或者“关机”对话框之上，
        // 所以监听这个广播，当收到时就隐藏自己的对话，如点击pad右下角部分弹出的对话框
//        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);


        BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(final Context context, final Intent intent) {
//                LogU.t("onReceive");
                String action = intent.getAction();

                if (Intent.ACTION_SCREEN_ON.equals(action)) {
//                    LogU.t("screen on");
                    if (banner != null) {
                        banner.setIsAuto(true);
                    }
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
//                    LogU.t("screen off");
                    if (banner != null) {
                        banner.setIsAuto(false);
                    }
                }
            }
        };
//        LogU.t("registerReceiver");
        getActivity().registerReceiver(mBatInfoReceiver, filter);


        LogU.t("token:"+getToken());


        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, statusBarHeight1, 0, 0);
            banner.setLayoutParams(lp);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FilterProductsActivity.class);

        switch (v.getId()) {
            case R.id.ll_home_fast_loan:
                intent.putExtra("index",0);
                break;
            case R.id.ll_home_low_rate:
                intent.putExtra("index",1);

                break;
            case R.id.ll_home_high_limit:
                intent.putExtra("index",2);

                break;
            case R.id.ll_home_long_term:
                intent.putExtra("index",3);

                break;
        }

        startActivity(intent);

    }


    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
//        EventBus.getDefault().post(new EventProgress(true));
    }

    @Override
    public void requestEnd() {
        swipe_home.setRefreshing(false);
//        EventBus.getDefault().post(new EventProgress(false));

    }


    @Override
    public void getBanner(BannerBean bean) {
        iv_home_banner.setVisibility(View.GONE);
        banner.setVisibility(View.VISIBLE);
        List<Banner> banners = bean.getContent().getAvailableBanners();
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            BannerItem item = new BannerItem();
            item.picUrl = banners.get(i).getBannerPic();
            list.add(item);
        }
        BannerViewFactory factory = new BannerViewFactory(getActivity());
        factory.setBanners(banners);
        banner.setViewFactory(factory);
        banner.setDataList(list);
        banner.start();


    }

    @Override
    public void getBannerFail(String error) {
        iv_home_banner.setVisibility(View.VISIBLE);
        banner.setVisibility(View.GONE);
//
        ToastUtil.showToast(getActivity(), error + "-h");
    }

    @Override
    public void getHomeFloors(BaseBean<Floors> bean) {
        floors = bean.getContent().getFloors();
        adapter.setList(floors);
    }

    @Override
    public void getHomeFloorsFail(String error) {
        ToastUtil.showToast(getActivity(), error);
    }

    @Override
    public void getHomeProducts(BaseBean<Products> bean) {
        products = bean.getContent().getHomeProductList();
        productAdapter.setList(products);
    }


    public static class BannerItem {
        public String pid;
        public String picUrl;

        @Override
        public String toString() {
            return pid;
        }
    }


    public static class BannerViewFactory implements BannerView.ViewFactory<BannerItem> {
        private Context context;
        private List<Banner> banners;

        public BannerViewFactory(Context context) {
            this.context = context;
        }

        public void setBanners(List<Banner> banners) {
            this.banners = banners;
        }

        @Override
        public View create(final BannerItem item, final int position, final ViewGroup container) {
            ImageView iv = new ImageView(container.getContext());
//            RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA);
//            Glide.with(container.getContext().getApplicationContext()).load(item.image).apply(options).into(iv);
            String imgUrl = RxHttp.ROOT + "attachments/" + item.picUrl;

//            LogU.t("banner----"+imgUrl);
            if (CommUtil.isNull(item.picUrl)) {
                ImgUtil.loadImg(container.getContext(), R.mipmap.banner1, iv);
            } else {
                ImgUtil.loadImg(container.getContext(), imgUrl, iv);
            }
/*
            if (banners != null) {
                final Banner b = banners.get(position);

                if (b.getBannerMode().equals("EXTERNAL")) {
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, BrowserActivity.class);
                            intent.putExtra("url", b.getBannerUrl());
                            intent.putExtra("title", b.getName());
                            context.startActivity(intent);
                        }
                    });
                } else {
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (b.getTargetInfo() != null) {
                                Banner.TargetInfoBean target = b.getTargetInfo();
                                switch (target.getType()) {
                                    case "product":
                                    case "PRODUCT":
                                        //单个产品
                                        Intent intent = new Intent(context, ProductDetailActivity.class);
                                        intent.putExtra("pid", target.getId());
                                        context.startActivity(intent);
                                        break;
                                    case "products":
                                    case "PRODUCTS":
                                    case "productList":
                                        //多个产品
                                        Intent intentProducts = new Intent(context, ProductsActivity.class);
                                        context.startActivity(intentProducts);
                                        break;
                                }

                            }
                        }
                    });
                }

            }
            */
            return iv;
        }

    }

    @Override
    public void getHomeProductsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }
}
