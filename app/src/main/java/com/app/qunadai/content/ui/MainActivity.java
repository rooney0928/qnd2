package com.app.qunadai.content.ui;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.home.frag.HomeFragment;
import com.app.qunadai.content.ui.home.frag.TestFragment;
import com.app.qunadai.content.view.ForbidScrollViewpager;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_main)
    NoScrollViewPager vp_main;

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
    private Handler handler = new Handler();

    private HomeFragment homeFragment1;
    private TestFragment testFragment;
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
//        testFragment = new TestFragment();
        fragments.add(homeFragment1);
    }

    @Override
    public void initViewData() {
//        LogU.t("???"+rb_nav_home);
        /**/
        vp_main.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),fragments));



        rg_nav_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //先清空标题栏
                setTitleBarStatus(0);
                switch (checkedId){
                    case R.id.rb_nav_home:
                        vp_main.setCurrentItem(0);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LogU.t("w--"+vp_main.getWidth());
                                LogU.t("h--"+vp_main.getHeight());
                            }
                        },500);
                        setTitleBarStatus(BaseActivity.TITLE_OFF);
                        break;
                    case R.id.rb_nav_limit:
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_nav_bbs:
                        vp_main.setCurrentItem(2);
                        setTitleBarStatus(BaseActivity.TITLE_ON_BACK_ON);

                        break;
                    case R.id.rb_nav_me:
                        vp_main.setCurrentItem(3);
                        break;
                }
            }
        });
        vp_main.setOffscreenPageLimit(4);
        rb_nav_home.setChecked(true);

    }


}
