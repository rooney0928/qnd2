package com.app.qunadai.content.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.ui.home.frag.FilterProductsFragment;
import com.app.qunadai.third.eventbus.EventLogin;
import com.app.qunadai.third.eventbus.EventOffline;
import com.app.qunadai.third.tablayout.TabEntity;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kennyc.view.MultiStateView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/13.
 */

public class FilterProductsActivity extends BaseActivity {

    @BindView(R.id.vp_pros)
    ViewPager vp_pros;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = new String[]{"快速放款", "超低利率", "超高额度"};

    private List<Fragment> fragments = new ArrayList<>();
    FilterProductsFragment filterProductsFragment1;
    FilterProductsFragment filterProductsFragment2;
    FilterProductsFragment filterProductsFragment3;
//    FilterProductsFragment filterProductsFragment4;

    @Override
    protected void updateTopViewHideAndShow() {
        clearTitleBar();
        setTitleBarVisible(true);
        setBackVisible(true);
        setTitleTextVisible(false);
        setTabsVisible(true);
    }

    @Override
    protected View createCenterView() {
        View root = View.inflate(this, R.layout.activity_products_filter, null);
        return root;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        int index = getIntent().getIntExtra("index", 0);
        for (String title : mTitles) {
            mTabEntities.add(new TabEntity(title));
        }
        filterProductsFragment1 = FilterProductsFragment.getInstance("loanTime", R.mipmap.banner_fast);
        filterProductsFragment2 = FilterProductsFragment.getInstance("minRate", R.mipmap.banner_low);
        filterProductsFragment3 = FilterProductsFragment.getInstance("maxAmount", R.mipmap.banner_high);
//        filterProductsFragment4 = FilterProductsFragment.getInstance("maxTerm", R.mipmap.banner_period);

        fragments.add(filterProductsFragment1);
        fragments.add(filterProductsFragment2);
        fragments.add(filterProductsFragment3);
//        fragments.add(filterProductsFragment4);


        vp_pros.setOffscreenPageLimit(4);
        vp_pros.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));

        setTabTitle(mTabEntities);
        setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                LogU.t("sel---"+position);
                vp_pros.setCurrentItem(position);
                FilterProductsFragment productsFragment = (FilterProductsFragment) fragments.get(position);
                if (productsFragment.list != null && productsFragment.list.size() == 0) {
                    productsFragment.refresh();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        vp_pros.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp_pros.setCurrentItem(index);

        setOnReLinkListener(new OnReLinkListener() {
            @Override
            public void doNewRequest() {
                switch (vp_pros.getCurrentItem()) {
                    case 0:
                        filterProductsFragment1.refresh();
                        break;
                    case 1:
                        filterProductsFragment2.refresh();

                        break;
                    case 2:
                        filterProductsFragment3.refresh();

                        break;
                    case 3:
//                        filterProductsFragment4.refresh();

                        break;
                }
            }
        });
    }

    @Override
    public void initViewData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        CommUtil.tcStart(this, "Visit-Fast loan");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CommUtil.tcEnd(this, "Visit-Fast loan");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventOffline event) {
        if (event.getType().equalsIgnoreCase("filterPro")) {
            if (getPageStatus() != MultiStateView.VIEW_STATE_ERROR) {
                setViewOffline();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
