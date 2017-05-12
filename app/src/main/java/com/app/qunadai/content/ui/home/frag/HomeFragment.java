package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.content.presenter.HomePresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.content.view.FullViewPager;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.RxHolder;
import com.app.qunadai.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscription;

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

    private HomePresenter homePresenter;

    private List<Fragment> fragments = new ArrayList<>();
    private RecommendFragment recommendFragment1;
    private RecommendFragment recommendFragment2;
    private RecommendFragment recommendFragment3;

    private List<String> tabTitle;

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
                swipe_home.setRefreshing(false);
            }
        });

        homePresenter.getHomeRecommend();
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
        vp_recommend.setAdapter(new MainFragmentPagerAdapter(getChildFragmentManager(),fragments,tabTitle));

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

    }
}
