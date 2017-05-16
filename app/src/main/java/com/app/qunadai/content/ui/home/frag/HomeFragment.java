package com.app.qunadai.content.ui.home.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.content.presenter.HomePresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.content.ui.home.ProductsActivity;
import com.app.qunadai.content.ui.home.RecommendActivity;
import com.app.qunadai.content.ui.me.PersonInfoActivity;
import com.app.qunadai.content.view.FullViewPager;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.RxHolder;
import com.app.qunadai.utils.ToastUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/5/8.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.tl_home_tab)
    TabLayout tl_home_tab;
    @BindView(R.id.swipe_home)
    SwipeRefreshLayout swipe_home;

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

    @BindView(R.id.rl_home_more)
    RelativeLayout rl_home_more;

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
        homePresenter = new HomePresenter(this);
        fragments = new ArrayList<>();
        recommendFragment1 = RecommendFragment.getInstance();
        recommendFragment2 = RecommendFragment.getInstance();
        recommendFragment3 = RecommendFragment.getInstance();

        fragments.add(recommendFragment1);
        fragments.add(recommendFragment2);
        fragments.add(recommendFragment3);

        initTabLayout();
        swipe_home.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                if (isRefresh) {
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
                Intent intentReco = new Intent(getActivity(), RecommendActivity.class);
                startActivity(intentReco);
            }
        });

        homePresenter.getHomeRecommend();
        homePresenter.requestPersonValue(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));
    }

    private void initTabLayout() {
        tabTitle = new ArrayList<>();
        tabTitle.add("2000以下");
        tabTitle.add("2000-1万");
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
        LogU.t("limit-"+bean.getContent().getPersonalValue().getValuation());
        ll_get_amount.setVisibility(View.GONE);
        ll_home_allow_limit.setVisibility(View.GONE);
        if (0 != bean.getContent().getPersonalValue().getValuation()) {
            ll_home_allow_limit.setVisibility(View.VISIBLE);
            tv_home_allow_limit.setText(""+bean.getContent().getPersonalValue().getValuation());
            bt_home_borrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入贷款列表
                    Intent intentProducts = new Intent(getActivity(),ProductsActivity.class);
                    startActivity(intentProducts);
                }
            });
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

    @Override
    public void getPersonValueFail(String error) {
        ToastUtil.showToast(getActivity(), error+"-h");
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
}
