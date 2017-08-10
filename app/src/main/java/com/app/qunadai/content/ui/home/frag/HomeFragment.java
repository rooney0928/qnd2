package com.app.qunadai.content.ui.home.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.QNDFactory;
import com.app.qunadai.R;
import com.app.qunadai.bean.Banner;
import com.app.qunadai.bean.BannerBean;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.content.presenter.HomePresenter;
import com.app.qunadai.content.ui.home.CreditCardActivity;
import com.app.qunadai.content.ui.home.ProductsActivity;
import com.app.qunadai.content.ui.home.RecommendActivity;
import com.app.qunadai.content.ui.me.BankCardActivity;
import com.app.qunadai.content.ui.me.PersonInfoActivity;
import com.app.qunadai.content.ui.product.BrowserActivity;
import com.app.qunadai.content.ui.product.ProductDetailActivity;
import com.app.qunadai.content.view.FullViewPager;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import ezy.ui.view.BannerView;
import rx.Observable;
import rx.functions.Action1;

import static com.app.qunadai.MyApp.context;

/**
 * Created by wayne on 2017/5/8.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, View.OnClickListener {
    @BindView(R.id.tl_home_tab)
    TabLayout tl_home_tab;
    @BindView(R.id.swipe_home)
    SwipeRefreshLayout swipe_home;

    @BindView(R.id.iv_home_banner)
    ImageView iv_home_banner;

    @BindView(R.id.vp_recommend)
    FullViewPager vp_recommend;

    @BindView(R.id.ll_get_amount)
    LinearLayout ll_get_amount;
    @BindView(R.id.ll_home_allow_limit)
    LinearLayout ll_home_allow_limit;

    @BindView(R.id.tv_home_allow_limit)
    TextView tv_home_allow_limit;
    @BindView(R.id.rl_home_get_limit)
    RelativeLayout rl_home_get_limit;
    @BindView(R.id.bt_home_borrow)
    Button bt_home_borrow;

    @BindView(R.id.ll_home_speed_loan)
    LinearLayout ll_home_speed_loan;
    @BindView(R.id.ll_home_recommend_loan)
    LinearLayout ll_home_recommend_loan;
    @BindView(R.id.ll_home_credit)
    LinearLayout ll_home_credit;

    @BindView(R.id.rl_home_more)
    RelativeLayout rl_home_more;

    @BindView(R.id.banner)
    BannerView banner;

    private HomePresenter homePresenter;

    private List<Fragment> fragments = new ArrayList<>();
    private RecommendFragment recommendFragment1;
    private RecommendFragment recommendFragment2;
    private RecommendFragment recommendFragment3;

    private List<String> tabTitle;
    boolean isRefresh;


    public static HomeFragment getInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_home, null);
        return root;
    }

    @Override
    protected void initData() {
        CommUtil.tcEvent(getActivity(), "home_page", "首页着陆页");

        homePresenter = new HomePresenter(this);
        fragments = new ArrayList<>();
        recommendFragment1 = RecommendFragment.getInstance();
        recommendFragment2 = RecommendFragment.getInstance();
        recommendFragment3 = RecommendFragment.getInstance();

        fragments.add(recommendFragment1);
        fragments.add(recommendFragment2);
        fragments.add(recommendFragment3);

//        initBanner();

        initTabLayout();
        swipe_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                LogU.t("t-" + getToken());
                if (isRefresh || CommUtil.isNull(getToken())) {
                    swipe_home.setRefreshing(false);
                    return;
                }


                if (NetworkUtil.checkNetwork(getActivity())) {
                    homePresenter.requestPersonValue(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));
                    homePresenter.getHomeRecommend();

                } else {
                    swipe_home.setRefreshing(false);
                }
            }
        });
        ll_home_speed_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入贷款产品列表
                CommUtil.tcEvent(getActivity(), "Rapid_loan", "极速贷款");
                Intent intentProducts = new Intent(getActivity(), ProductsActivity.class);
                startActivity(intentProducts);
            }
        });
        rl_home_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入贷款产品列表
                Intent intentProducts = new Intent(getActivity(), ProductsActivity.class);
                startActivity(intentProducts);
            }
        });
        ll_home_recommend_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入推荐列表
                CommUtil.tcEvent(getActivity(), "Loans_to_recommend", "贷款推荐");
                Intent intentReco = new Intent(getActivity(), RecommendActivity.class);
                startActivity(intentReco);
            }
        });
        bt_home_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入贷款列表
                Intent intentProducts = new Intent(getActivity(), ProductsActivity.class);
                startActivity(intentProducts);
            }
        });
        ll_home_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //办信用卡
                Intent intentCredit = new Intent(getActivity(), CreditCardActivity.class);
                startActivity(intentCredit);

            }
        });
        iv_home_banner.setOnClickListener(this);


        if (NetworkUtil.checkNetwork(getActivity())) {
//            refreshMsg();
            homePresenter.getHomeRecommend();

            homePresenter.getBanner();
        }
    }

    private void initBanner() {
        int[] resIds = new int[]{R.mipmap.banner1};

        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            BannerItem item = new BannerItem();
            list.add(item);
        }

        banner.setViewFactory(new BannerViewFactory(getActivity()));
        banner.setDataList(list);
        banner.start();
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

            if (banners != null) {
                final Banner b = banners.get(position);

                if(b.getBannerMode().equals("EXTERNAL")){
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, BrowserActivity.class);
                            intent.putExtra("url", b.getBannerUrl());
                            intent.putExtra("title",b.getName());
                            context.startActivity(intent);
                        }
                    });
                }else{
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
            return iv;
        }
    }

    private void initTabLayout() {
        tabTitle = new ArrayList<>();
        tabTitle.add("2000以下");
        tabTitle.add("2000-10000");
        tabTitle.add("10000以上");

        tl_home_tab.setupWithViewPager(vp_recommend);
        tl_home_tab.setTabMode(TabLayout.MODE_FIXED);
        tl_home_tab.addTab(tl_home_tab.newTab().setText(tabTitle.get(0)));
        tl_home_tab.addTab(tl_home_tab.newTab().setText(tabTitle.get(1)));
        tl_home_tab.addTab(tl_home_tab.newTab().setText(tabTitle.get(2)));


        vp_recommend.setOffscreenPageLimit(3);
        vp_recommend.setAdapter(new MainFragmentPagerAdapter(getChildFragmentManager(), fragments, tabTitle));

    }


    @Override
    public void getHomeRecommend(HomeRecommend bean) {
//        LogU.t("" + new Gson().toJson(bean.getContent().getTab1().get(0), LoanDetail.class));
        recommendFragment1.setRecommendData(bean.getContent().getTab1());
        recommendFragment2.setRecommendData(bean.getContent().getTab2());
        recommendFragment3.setRecommendData(bean.getContent().getTab3());
    }


    @Override
    public void getHomeRecommendFail(String error) {
        LogU.te(error);
    }

    @Override
    public void getPersonValue(PersonBean bean) {
        LogU.t("limit-" + bean.getContent().getPersonalValue().getValuation());
        ll_get_amount.setVisibility(View.GONE);
        ll_home_allow_limit.setVisibility(View.GONE);
        if (0 != bean.getContent().getPersonalValue().getValuation()) {
            ll_home_allow_limit.setVisibility(View.VISIBLE);
            tv_home_allow_limit.setText("" + bean.getContent().getPersonalValue().getValuation());

        } else {
            ll_get_amount.setVisibility(View.VISIBLE);
            rl_home_get_limit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入身份认证
                    Intent intentInfo = new Intent(getActivity(), PersonInfoActivity.class);
                    startActivity(intentInfo);

                }
            });
        }
    }

    public void refreshMsg() {
        if (getActivity() != null && !CommUtil.isNull(getToken()) && homePresenter != null) {
            homePresenter.requestPersonValue(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));
        }
    }

    @Override
    public void getPersonValueFail(String error) {
        ToastUtil.showToast(getActivity(), error + "-h");
    }

    @Override
    public void getBanner(BannerBean bean) {
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
        ToastUtil.showToast(getActivity(), error + "-h");
        initBanner();
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        isRefresh = true;
    }

    @Override
    public void requestEnd() {
        if (swipe_home != null && swipe_home.isRefreshing()) {
            //延迟500毫秒关闭swipe
            Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(
                    new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    swipe_home.setRefreshing(false);
                                }
                            });
                        }
                    }
            );

        }
        isRefresh = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_banner:
                CommUtil.tcEvent(getActivity(), "banner", "焦点图");
                break;
        }
    }
}
