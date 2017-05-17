package com.app.qunadai.content.ui;

import android.content.Intent;
import android.graphics.Point;
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
import com.app.qunadai.content.ui.bbs.frag.BBSFragment;
import com.app.qunadai.content.ui.home.frag.HomeFragment;
import com.app.qunadai.content.ui.home.frag.TestFragment;
import com.app.qunadai.content.ui.limit.frag.LimitFragment;
import com.app.qunadai.content.ui.me.frag.MeFragment;
import com.app.qunadai.content.view.ForbidScrollViewpager;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.third.eventbus.EventNick;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ReqKey;
import com.app.qunadai.utils.ToastUtil;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    private HomeFragment homeFragment;
    private LimitFragment limitFragment;
    private BBSFragment bbsFragment;
    private MeFragment meFragment;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_OFF);
    }

    @Override
    protected View createCenterView() {
        View content = View.inflate(this, R.layout.activity_main, null);
        return content;
    }

    @Override
    protected View createBottomView() {
        View footer = View.inflate(this, R.layout.layout_nav, null);
        return footer;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        homeFragment = HomeFragment.getInstance();
        limitFragment = LimitFragment.getInstance();
        bbsFragment = BBSFragment.getInstance();
        meFragment = MeFragment.getInstance();
//        testFragment = new TestFragment();
        fragments.add(homeFragment);
        fragments.add(limitFragment);
        fragments.add(bbsFragment);
        fragments.add(meFragment);

        Point p = CommUtil.getSize(this);
        LogU.t("width-"+p.x);
        LogU.t("height-"+p.y);
    }


    @Override
    public void initViewData() {
//        LogU.t("???"+rb_nav_home);
        /**/
//        CrashReport.testANRCrash();
//        CrashReport.testJavaCrash();
        vp_main.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));


        rg_nav_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //先清空标题栏
                setTitleBarStatus(TITLE_OFF);
                setTitle("");
                switch (checkedId) {
                    case R.id.rb_nav_home:
                        vp_main.setCurrentItem(0);
                        setTitleBarStatus(BaseActivity.TITLE_OFF);
                        break;
                    case R.id.rb_nav_limit:
                        vp_main.setCurrentItem(1);
                        setTitleBarStatus(BaseActivity.TITLE_OFF);
                        break;
                    case R.id.rb_nav_bbs:
                        vp_main.setCurrentItem(2);
                        setTitleBarStatus(BaseActivity.TITLE_ON_BACK_OFF);
                        setTitle("社区");
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

    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            ToastUtil.showToast(this, "再按一次退出程序");
            mPressedTime = mNowTime;
        } else {
            //退出程序
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ReqKey.REQ_MOXIE:
                if (limitFragment != null) {
                    limitFragment.onActivityResult(requestCode, resultCode, data);
                }
                break;
//            case ReqKey.REQ_QUIT_SYSTEM:
//                if(resultCode==RESULT_OK){
//                    finish();
//                    System.exit(0);
//                }
//                break;

        }

//        ReqKey.REQ_QUIT_SYSTEM
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventNick event) {
        if (meFragment != null) {
            meFragment.setNickname(event.getNickname());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventClose event) {
//        if (meFragment != null) {
//            meFragment.setNickname(event.getNickname());
//        }
        if("main".equalsIgnoreCase(event.getPage())){
            finish();
        }
    }

}
