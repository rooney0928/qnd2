package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.HomeRecommend;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.HomeContract;
import com.app.qunadai.content.presenter.HomePresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/8.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.tl_home_tab)
    TabLayout tl_home_tab;
    @BindView(R.id.swipe_home)
    SwipeRefreshLayout swipe_home;

    @BindView(R.id.vp_recommend)
    ViewPager vp_recommend;

    private HomePresenter homePresenter;


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
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_home, null);
        return root;
    }

    @Override
    protected void initData() {
        homePresenter = new HomePresenter(this);
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


        tl_home_tab.setTabMode(TabLayout.MODE_FIXED);
        tl_home_tab.addTab(tl_home_tab.newTab().setText(tabTitle.get(0)));
        tl_home_tab.addTab(tl_home_tab.newTab().setText(tabTitle.get(1)));
        tl_home_tab.addTab(tl_home_tab.newTab().setText(tabTitle.get(2)));
    }


    @Override
    public void getHomeRecommend(HomeRecommend bean) {
        LogU.t(bean.getCode());
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
