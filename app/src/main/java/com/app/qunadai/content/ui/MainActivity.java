package com.app.qunadai.content.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.dialog.UpdateDialog;
import com.app.qunadai.content.ui.bbs.PostMyActivity;
import com.app.qunadai.content.ui.bbs.frag.BBSFragment;
import com.app.qunadai.content.ui.home.frag.Home5Fragment;
import com.app.qunadai.content.ui.me.frag.Me5Fragment;
import com.app.qunadai.content.ui.user.SplashActivity;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.third.eventbus.EventLogin;
import com.app.qunadai.third.eventbus.EventMe;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventToken;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.AppManager;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

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

    private int index = 0;

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


        //检查更新
        update();

    }


    AlertDialog dialog;
    AlertDialog updateDialog;

    /*
     * 检测有否升级
     */
    public void update() {
        PgyUpdateManager.register(this, "qunadai", new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {
//                loginDelay();
//                update5();
            }

            @Override
            public void onUpdateAvailable(String s) {
                // 将新版本信息封装到AppBean中
//                final AppBean appBean = getAppBeanFromString(s);
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("更新");
//                builder.setMessage(appBean.getReleaseNote());
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startDownloadTask(MainActivity.this, appBean.getDownloadURL());
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        checkNeedUpdate(appBean.getVersionName());
//                    }
//                });
//                dialog = builder.show();
//                dialog.setCancelable(false);
//                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        checkNeedUpdate(appBean.getVersionName());
//                    }
//                });

                final AppBean appBean = getAppBeanFromString(s);


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = View.inflate(MainActivity.this, R.layout.update_dialog, null);
                final ImageView iv_update_title = (ImageView) view.findViewById(R.id.iv_update_title);
                TextView tv_update_cancel = (TextView) view.findViewById(R.id.tv_update_cancel);
                TextView tv_update_submit = (TextView) view.findViewById(R.id.tv_update_submit);
                TextView tv_update_desc = (TextView) view.findViewById(R.id.tv_update_desc);
                tv_update_desc.setText(appBean.getReleaseNote());

                tv_update_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkNeedUpdate(appBean.getVersionName());
                    }
                });
                tv_update_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startDownloadTask(MainActivity.this, appBean.getDownloadURL());

                    }
                });

                builder.setView(view);


                updateDialog = builder.show();
                updateDialog.setCancelable(false);
                updateDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        checkNeedUpdate(appBean.getVersionName());
                    }
                });

                Window dialogWindow = updateDialog.getWindow();
                Point point = CommUtil.getSize(MainActivity.this);
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//                p.height = (int) (point.y * 0.8); // 高度设置为屏幕的0.6，根据实际情况调整
                p.width = (int) (point.x * 0.7); // 宽度设置为屏幕的0.65，根据实际情况调整
                dialogWindow.setAttributes(p);
            }

        });
    }


    public void checkNeedUpdate(String version) {
        String last = String.valueOf(version.charAt(version.length() - 1));
        int i = Integer.parseInt(last);
        if (i % 2 == 0) {
            //余2为0则强制更新,此时关闭
            AppManager.finishProgram();
        } else {
            if (updateDialog != null && updateDialog.isShowing()) {
                updateDialog.dismiss();
            }
        }
    }


    @Override
    public void initViewData() {
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
                        CommUtil.tcEvent(MainActivity.this,"home","着陆页");
                        vp_main.setCurrentItem(0);
                        setTitleBarVisible(true);
                        setTitleText("主页");
                        index = 0;

                        break;
                    case R.id.rb_nav_bbs:

                        CommUtil.tcEvent(MainActivity.this, "find", "发现首页点击量");
                        vp_main.setCurrentItem(1);
                        setTitleBarVisible(true);
                        setTitleText("发现");
                        setTitleRightText("我的帖子");
                        setTitleRightEvent(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //进入我的帖子
                                if (CommUtil.isNull(getToken())) {
                                    exeLogin();
                                    vp_main.setCurrentItem(0);
                                    return;
                                }
                                Intent intentMyPost = new Intent(MainActivity.this, PostMyActivity.class);
                                startActivity(intentMyPost);
                            }
                        });
                        index = 1;

                        break;
                    case R.id.rb_nav_me:

                        if (CommUtil.isNull(getToken())) {
                            exeLogin();
                            vp_main.setCurrentItem(index);
                            setCheckBox(index);
                            return;
                        }
                        if (me5Fragment != null) {
                            me5Fragment.requestUserData();
                        }
                        vp_main.setCurrentItem(2);
                        setTitleBarVisible(true);
                        setTitleText("我的");

                        index = 2;

                        break;
                }
            }
        });
        vp_main.setOffscreenPageLimit(3);
        rb_nav_home.setChecked(true);

    }

    public void setCheckBox(int index) {
        switch (index) {
            case 0:
                rb_nav_home.setChecked(true);
                break;
            case 1:
                rb_nav_bbs.setChecked(true);
                break;
            case 2:
                rb_nav_me.setChecked(true);
                break;
        }
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
        if (me5Fragment != null) {
            me5Fragment.requestUserData();
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventTurn event) {
        if (event.getType().equalsIgnoreCase("main")) {

            vp_main.setCurrentItem(event.getPage());
            setCheckBox(event.getPage());
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
