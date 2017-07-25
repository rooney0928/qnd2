package com.app.qunadai.content.ui.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.SplashContract;
import com.app.qunadai.content.presenter.SplashPresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.utils.AppManager;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.pgyersdk.Pgy;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

import static android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS;

/**
 * Created by wayne on 2017/5/15.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {
    private SplashPresenter splashPresenter;

    String phone;
    String pwd;
    String pwdEncode;
    boolean autoLogin;

    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_OFF);
        clearTitleBar();
    }

    @Override
    protected View createCenterView() {
        return View.inflate(this, R.layout.activity_splash, null);
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    AlertDialog dialog;

    @Override
    protected void initView() {
        splashPresenter = new SplashPresenter(this);

        //首先判断权限
        AndPermission.with(this)
                .requestCode(300)

                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION)
                .callback(this)
                .start()
        ;
    }

    @PermissionYes(300)
    private void getPermissionYes(List<String> grantedPermissions) {
        // Successfully.
        update();
    }

    @PermissionNo(300)
    private void getPermissionNo(List<String> deniedPermissions) {
        // Failure.
        showSettingDialog();
    }

    private void showSettingDialog() {
        String title = "权限申请";
        String content = "需要一些权限,以保证程序正常功能的使用\n" +
                "    请在[设置-应用管理-权限]中开启所需权限..";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setCancelable(false);
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri packageURI = Uri.parse("package:" + getPkgName());
                Intent intent = new Intent(ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                startActivity(intent);
                dialog.dismiss();
                AppManager.finishProgram();
            }
        });
        builder.setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AppManager.finishProgram();
            }
        });
        builder.show();
    }

    private String getPkgName() {
        return this.getPackageName();
    }

    /**
     * 检测有否升级
     */
    public void update() {
        PgyUpdateManager.register(this, "qunadai", new UpdateManagerListener() {
            @Override
            public void onNoUpdateAvailable() {
                loginDelay();
            }

            @Override
            public void onUpdateAvailable(String s) {
                // 将新版本信息封装到AppBean中
                final AppBean appBean = getAppBeanFromString(s);
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("更新");
                builder.setMessage(appBean.getReleaseNote());
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startDownloadTask(SplashActivity.this, appBean.getDownloadURL());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkNeedUpdate(appBean.getVersionName());
                    }
                });
                dialog = builder.show();
                dialog.setCancelable(false);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        checkNeedUpdate(appBean.getVersionName());
                    }
                });
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
            loginDelay();
        }
    }

    @Override
    public void initViewData() {

    }

    public void loginDelay() {
        phone = PrefUtil.getString(this, PrefKey.PHONE, "");
//        pwdEncode = PrefUtil.getString(this, PrefKey.PWD_ENCODE, "");
        pwd = PrefUtil.getString(this, PrefKey.PWD, "");
        autoLogin = PrefUtil.getBoolean(this, PrefKey.AUTO_LOGIN, false);
        //延迟3000毫秒进入首页
        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                if (autoLogin && !CommUtil.isNull(phone) && !CommUtil.isNull(pwd)) {
//                                    splashPresenter.loginByPwd(phone, CommUtil.shaEncrypt(pwd));
//                                } else {
//                                Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
//                                startActivity(intentLogin);
//                                finish();
//                                }


                                Intent intentMain = new Intent(SplashActivity.this,MainActivity.class);
                                startActivity(intentMain);
                                finish();

                            }
                        });
                    }
                }
        );
    }

    @Override
    public void loginDone(Token token) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail(String error) {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
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
