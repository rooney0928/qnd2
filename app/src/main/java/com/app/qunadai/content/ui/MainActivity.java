package com.app.qunadai.content.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.home.frag.HomeFragment;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    ViewPager vp_main;

    @BindView(R.id.rg_nav_group)
    RadioGroup rg_nav_group;
    @BindView(R.id.rb_nav_home)
    RadioButton rb_nav_home;
    @BindView(R.id.rb_nav_limit)
    RadioButton rb_nav_limit;
    @BindView(R.id.rb_nav_bbs)
    RadioButton rb_nav_bbs;
    @BindView(R.id.rb_nav_me)
    RadioButton rb_nav_me;

    private HomeFragment homeFragment1;
    private HomeFragment homeFragment2;
    private HomeFragment homeFragment3;
    private HomeFragment homeFragment4;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_OFF);
    }

    @Override
    protected View createCenterView() {
        View content = View.inflate(this,R.layout.activity_main,null);
        return content;
    }

    @Override
    protected View createBottomView() {
        View footer = View.inflate(this,R.layout.layout_nav,null);
        return footer;
    }

    @Override
    protected void initView() {
        homeFragment1 = HomeFragment.getInstance();
        homeFragment2 = HomeFragment.getInstance();
        homeFragment3 = HomeFragment.getInstance();
        homeFragment4 = HomeFragment.getInstance();
        fragments.add(homeFragment1);
        fragments.add(homeFragment2);
        fragments.add(homeFragment3);
        fragments.add(homeFragment4);
    }

    @Override
    public void initViewData() {
//        LogU.t("???"+rb_nav_home);
        vp_main.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        vp_main.setOffscreenPageLimit(4);

        rg_nav_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_nav_home:
//                        ToastUtil.showToast(MainActivity.this,"1");
                        vp_main.setCurrentItem(0);
                        break;
                    case R.id.rb_nav_limit:
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_nav_bbs:
                        vp_main.setCurrentItem(2);
                        break;
                    case R.id.rb_nav_me:
                        vp_main.setCurrentItem(3);
                        break;
                }
            }
        });

    }

}
