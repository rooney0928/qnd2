package com.app.qunadai.content.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.home.frag.FilterProductsFragment;
import com.app.qunadai.third.tablayout.TabEntity;
import com.app.qunadai.utils.LogU;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

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
    private String[] mTitles = new String[]{"快速放款", "超低利率", "超高额度", "超长期限"};

    private List<Fragment> fragments = new ArrayList<>();
    FilterProductsFragment filterProductsFragment1;
    FilterProductsFragment filterProductsFragment2;
    FilterProductsFragment filterProductsFragment3;
    FilterProductsFragment filterProductsFragment4;

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
        int index = getIntent().getIntExtra("index",0);
        for (String title : mTitles) {
            mTabEntities.add(new TabEntity(title));
        }
        filterProductsFragment1 = FilterProductsFragment.getInstance("loanTime");
        filterProductsFragment2 = FilterProductsFragment.getInstance("minRate");
        filterProductsFragment3 = FilterProductsFragment.getInstance("maxAmount");
        filterProductsFragment4 = FilterProductsFragment.getInstance("maxTerm");

        fragments.add(filterProductsFragment1);
        fragments.add(filterProductsFragment2);
        fragments.add(filterProductsFragment3);
        fragments.add(filterProductsFragment4);


        vp_pros.setOffscreenPageLimit(4);
        vp_pros.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));

        setTabTitle(mTabEntities);
        setTabListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                LogU.t("sel---"+position);
                vp_pros.setCurrentItem(position);
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
    }

    @Override
    public void initViewData() {

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
