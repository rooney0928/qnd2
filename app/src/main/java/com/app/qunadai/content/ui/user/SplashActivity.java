package com.app.qunadai.content.ui.user;

import android.content.Intent;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/5/15.
 */

public class SplashActivity extends BaseActivity {
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
                                Intent intentLogin = new Intent(SplashActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                                finish();
                            }
                        });
                    }
                }
        );
    }
}
