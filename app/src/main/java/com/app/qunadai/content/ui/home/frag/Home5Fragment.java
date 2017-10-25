package com.app.qunadai.content.ui.home.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Banner;
import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.PersonBean;
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
import com.app.qunadai.content.ui.home.CreditCardActivity;
import com.app.qunadai.content.ui.home.FilterProductsActivity;
import com.app.qunadai.content.ui.me.AuthActivity;
import com.app.qunadai.content.view.FullRecyclerView;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;


import java.util.List;

import butterknife.BindView;
import me.bakumon.numberanimtextview.NumberAnimTextView;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by wayne on 2017/9/7.
 */

public class Home5Fragment extends BaseFragment implements Home5Contract.View, View.OnClickListener {

    private static final int BANNER_SPEED = 3000;
    private Home5Presenter home5Presenter;

    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.iv_home_banner)
    ImageView iv_home_banner;
    @BindView(R.id.rv_floors)
    FullRecyclerView rv_floors;
    @BindView(R.id.rv_pros)
    RecyclerView rv_pros;
    @BindView(R.id.swipe_home)
    SwipeRefreshLayout swipe_home;

//    @BindView(R.id.ll_home_fast_loan)
//    LinearLayout ll_home_fast_loan;
//    @BindView(R.id.ll_home_low_rate)
//    LinearLayout ll_home_low_rate;
//    @BindView(R.id.ll_home_high_limit)
//    LinearLayout ll_home_high_limit;
//    @BindView(R.id.ll_home_long_term)
//    LinearLayout ll_home_long_term;

    @BindView(R.id.ll_home_loan)
    LinearLayout ll_home_loan;
    @BindView(R.id.ll_home_credit)
    LinearLayout ll_home_credit;
    @BindView(R.id.ll_home_new)
    LinearLayout ll_home_new;

    @BindView(R.id.iv_home_new_icon)
    ImageView iv_home_new_icon;

    @BindView(R.id.rl_home_get_limit)
    RelativeLayout rl_home_get_limit;
    @BindView(R.id.natv_limit_money)
    NumberAnimTextView natv_limit_money;



    List<Floors.FloorsBean> floors;
    List<Product> products;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager2;
    HomeFloorsAdapter adapter;
    ProductAdapter productAdapter;

    QBadgeView qBadgeView;


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
        qBadgeView = new QBadgeView(getActivity());
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

//        ll_home_fast_loan.setOnClickListener(this);
//        ll_home_low_rate.setOnClickListener(this);
//        ll_home_high_limit.setOnClickListener(this);
//        ll_home_long_term.setOnClickListener(this);

        ll_home_loan.setOnClickListener(this);
        ll_home_credit.setOnClickListener(this);
        ll_home_new.setOnClickListener(this);
        rl_home_get_limit.setOnClickListener(this);


        if (NetworkUtil.checkNetwork(getActivity())) {
            home5Presenter.getHomeFloors();
            home5Presenter.getHomeProducts();
            home5Presenter.getBanner();
            if(!CommUtil.isNull(getToken())){
                home5Presenter.requestPersonValue(getToken());
            }
        }

        swipe_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                home5Presenter.getHomeFloors();
                home5Presenter.getHomeProducts();
                if(!CommUtil.isNull(getToken())){
                    home5Presenter.requestPersonValue(getToken());
                }
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
//                        banner.setIsAuto(true);
                    }
                } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
//                    LogU.t("screen off");
                    if (banner != null) {
//                        banner.setIsAuto(false);
                    }
                }
            }
        };
//        LogU.t("registerReceiver");
//        getActivity().registerReceiver(mBatInfoReceiver, filter);


        LogU.t("token:" + getToken());

/**
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
 */

        LogU.t("channel-" + CommUtil.getAppMetaData(getActivity(), "UMENG_CHANNEL"));

    }


    @Override
    public void onClick(View v) {
        /*
        Intent intent = new Intent(getActivity(), FilterProductsActivity.class);

        switch (v.getId()) {
            case R.id.ll_home_fast_loan:
                intent.putExtra("index", 0);
                break;
            case R.id.ll_home_low_rate:
                intent.putExtra("index", 1);

                break;
            case R.id.ll_home_high_limit:
                intent.putExtra("index", 2);

                break;
            case R.id.ll_home_long_term:
                intent.putExtra("index", 3);

                break;
        }

        startActivity(intent);
*/

        switch (v.getId()) {
            case R.id.ll_home_loan:
                Intent intent = new Intent(getActivity(), FilterProductsActivity.class);
                intent.putExtra("index", 0);
                startActivity(intent);
                break;
            case R.id.ll_home_credit:
                Intent intentCredit = new Intent(getActivity(), CreditCardActivity.class);
                startActivity(intentCredit);
                break;
            case R.id.ll_home_new:
                break;
            case R.id.rl_home_get_limit:
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                Intent intentAuth = new Intent(getActivity(), AuthActivity.class);
                startActivity(intentAuth);
                break;

        }
    }


    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            if (bannerList != null && bannerList.size() > 1) {
                banner.startTurning(BANNER_SPEED);
            } else {
                banner.stopTurning();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            banner.stopTurning();
        }
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

    List<Banner> bannerList;

    @Override
    public void getBanner(BannerBean bean) {
        iv_home_banner.setVisibility(View.GONE);
        banner.setVisibility(View.VISIBLE);
//        List<Banner> banners = bean.getContent().getAvailableBanners();
//        List<BannerItem> list = new ArrayList<>();
//        for (int i = 0; i < banners.size(); i++) {
//            BannerItem item = new BannerItem();
//            item.picUrl = banners.get(i).getBannerPic();
//            list.add(item);
//        }
//        BannerViewFactory factory = new BannerViewFactory(getActivity());
//        factory.setBanners(banners);
//        factory.setListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });
//        banner.setViewFactory(factory);
//        banner.setDataList(list);
//        banner.start();

        qBadgeView.bindTarget(iv_home_new_icon)
                .setBadgeBackgroundColor(ContextCompat.getColor(getActivity(), R.color.v5_price))
                .setBadgeTextColor(ContextCompat.getColor(getActivity(), R.color.white))
                .setBadgeGravity(Gravity.START | Gravity.TOP)
                .setShowShadow(false)
                .setBadgeText("新");
        qBadgeView.hide(true);


        bannerList = bean.getContent().getAvailableBanners();
        if (bannerList.size() > 1) {
            banner.setCanLoop(true);
            banner.startTurning(BANNER_SPEED);
        } else {
            banner.setCanLoop(false);
            banner.stopTurning();
        }
//        banner.setScrollDuration(500);

//        banner.setManualPageable();
        banner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, bannerList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响


    }

    public class LocalImageHolderView implements Holder<Banner> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Banner data) {
//            imageView.setImageResource(data);
            String imgUrl = RxHttp.ROOT + "attachments/" + data.getBannerPic();
            ImgUtil.loadBanner(getActivity(), imgUrl, imageView);
        }
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


    @Override
    public void getHomeProductsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void getPersonValue(PersonBean bean) {
        boolean isNew = bean.getContent().getPersonalValue().getUser().isBrowsedLatestProducts();
        if(isNew){
            qBadgeView.bindTarget(iv_home_new_icon)
                    .setBadgeBackgroundColor(ContextCompat.getColor(getActivity(), R.color.v5_price))
                    .setBadgeTextColor(ContextCompat.getColor(getActivity(), R.color.white))
                    .setBadgeGravity(Gravity.START | Gravity.TOP)
                    .setShowShadow(false)
                    .setBadgeText("新");
        }else{
            qBadgeView.hide(true);
        }

        natv_limit_money.setNumberString(bean.getContent().getPersonalValue().getValuation()+"");
    }

    @Override
    public void getPersonValueFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

}
