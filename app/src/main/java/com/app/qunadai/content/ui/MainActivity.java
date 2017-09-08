package com.app.qunadai.content.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.bbs.PostMyActivity;
import com.app.qunadai.content.ui.bbs.frag.BBSFragment;
import com.app.qunadai.content.ui.bbs.frag.HelpFragment;
import com.app.qunadai.content.ui.home.frag.Home5Fragment;
import com.app.qunadai.content.ui.home.frag.HomeFragment;
import com.app.qunadai.content.ui.limit.frag.LimitFragment;
import com.app.qunadai.content.ui.me.frag.Me5Fragment;
import com.app.qunadai.content.ui.me.frag.MeFragment;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.third.eventbus.EventLogin;
import com.app.qunadai.third.eventbus.EventMe;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventToken;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ReqKey;
import com.app.qunadai.utils.ToastUtil;

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
    //    @BindView(R.id.rb_nav_limit)
//    RadioButton rb_nav_limit;
    @BindView(R.id.rb_nav_bbs)
    RadioButton rb_nav_bbs;
    @BindView(R.id.rb_nav_me)
    RadioButton rb_nav_me;
    private Handler handler = new Handler();

    //    private HomeFragment homeFragment;
    private Home5Fragment home5Fragment;
    //    private LimitFragment limitFragment;
    private BBSFragment bbsFragment;
    //    private HelpFragment helpFragment;
    private Me5Fragment me5Fragment;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_OFF);
        clearTitleBar();
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
        home5Fragment = Home5Fragment.getInstance();
//        homeFragment = HomeFragment.getInstance();
//        limitFragment = LimitFragment.getInstance();
//        helpFragment = HelpFragment.getInstance();
        bbsFragment = BBSFragment.getInstance();
        me5Fragment = Me5Fragment.getInstance();

        fragments.add(home5Fragment);
//        fragments.add(limitFragment);
//        fragments.add(helpFragment);
        fragments.add(bbsFragment);
        fragments.add(me5Fragment);

//        Point p = CommUtil.getSize(this);
//        LogU.t("width-" + p.x);
//        LogU.t("height-" + p.y);
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
//                setTitleBarStatus(TITLE_OFF);
                clearTitleBar();
                setTitleText("");
                switch (checkedId) {
                    case R.id.rb_nav_home:
                        vp_main.setCurrentItem(0);
                        if (NetworkUtil.checkNetwork(MainActivity.this)) {

                        }
                        break;
                    case R.id.rb_nav_bbs:
                        CommUtil.tcEvent(MainActivity.this, "strategy_list", "攻略列表");
                        vp_main.setCurrentItem(1);
                        setTitleBarVisible(true);
                        setTitleText("社区");
                        setTitleRightText("我的帖子");
                        setTitleRightEvent(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //进入我的帖子
                                if (CommUtil.isNull(getToken())) {
                                    exeLogin();
                                    return;
                                }
                                Intent intentMyPost = new Intent(MainActivity.this, PostMyActivity.class);
                                startActivity(intentMyPost);
                            }
                        });
                        break;
                    case R.id.rb_nav_me:
                        if(CommUtil.isNull(getToken())){
                            exeLogin();
                            return;
                        }
                        vp_main.setCurrentItem(2);
                        setTitleBarVisible(true);
                        setTitleText("我的");
                        if (NetworkUtil.checkNetwork(MainActivity.this)) {
//                            if (meFragment != null) {
//                                meFragment.refreshMsg();
//                            }
                        }
                        break;
                }
            }
        });
        vp_main.setOffscreenPageLimit(3);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMe event) {
//        if (meFragment != null) {
//            meFragment.refreshMsg();
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventToken event) {
        if (CommUtil.isNull(getToken())) {
            exeLogin();
            return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventLogin event) {
//        if (meFragment != null) {
//            meFragment.refreshMsg();
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventClose event) {
//        if (meFragment != null) {
//            meFragment.setMeMessage(event.getNickname());
//        }
        if ("main".equalsIgnoreCase(event.getPage())) {
            finish();
        }
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
    public void onMessageEvent(EventProgress event) {
//        if (meFragment != null) {
//            meFragment.setMeMessage(event.getNickname());
//        }
        if (event.isShow()) {
            showLoading();
        } else {
            hideLoading();
        }
    }

}
