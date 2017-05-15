package com.app.qunadai.content.ui.me;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.user.LoginActivity;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.FileUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/15.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.rl_setting_good)
    RelativeLayout rl_setting_good;
    @BindView(R.id.rl_setting_cache)
    RelativeLayout rl_setting_cache;
    @BindView(R.id.rl_setting_about)
    RelativeLayout rl_setting_about;
    @BindView(R.id.rl_setting_version)
    RelativeLayout rl_setting_version;


    @BindView(R.id.bt_setting_logout)
    Button bt_setting_logout;
    @BindView(R.id.tv_setting_cache)
    TextView tv_setting_cache;
    @BindView(R.id.tv_settion_version)
    TextView tv_settion_version;
    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("设置");
    }


    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_setting,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
//        File cacheDir = getExternalCacheDir();
//        LogU.t(cacheDir.getAbsolutePath().toString());
//        LogU.t(FileUtil.getFolderSize(cacheDir)+"");
        bt_setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PrefUtil.removeItem(SettingActivity.this, PrefKey.TOKEN);
                PrefUtil.removeItem(SettingActivity.this, PrefKey.PHONE);
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                EventBus.getDefault().postSticky(new EventClose());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void initViewData() {
        rl_setting_good.setOnClickListener(this);
        rl_setting_cache.setOnClickListener(this);
        rl_setting_about.setOnClickListener(this);
        rl_setting_version.setOnClickListener(this);

        tv_settion_version.setText("v"+ CommUtil.getVersionName(this));
    }
}
