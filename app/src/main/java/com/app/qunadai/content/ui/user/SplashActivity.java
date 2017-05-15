package com.app.qunadai.content.ui.user;

import android.content.Intent;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.SplashContract;
import com.app.qunadai.content.presenter.SplashPresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

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
        setTitleBarStatus(TITLE_OFF);
    }

    @Override
    protected View createCenterView() {
        return View.inflate(this, R.layout.activity_splash, null);
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        splashPresenter = new SplashPresenter(this);

        phone = PrefUtil.getString(this, PrefKey.PHONE, "");
//        pwdEncode = PrefUtil.getString(this, PrefKey.PWD_ENCODE, "");
        pwd = PrefUtil.getString(this, PrefKey.PWD, "");
        autoLogin = PrefUtil.getBoolean(this, PrefKey.AUTO_LOGIN, false);
    }

    @Override
    public void initViewData() {
        //延迟3000毫秒登录
        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (autoLogin && !CommUtil.isNull(phone) && !CommUtil.isNull(pwd)) {
                                    splashPresenter.loginByPwd(phone, CommUtil.shaEncrypt(pwd));
                                } else {
                                    Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
                                    startActivity(intentLogin);
                                    finish();
                                }

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
