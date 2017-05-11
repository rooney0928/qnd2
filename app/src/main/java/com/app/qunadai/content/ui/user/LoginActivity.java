package com.app.qunadai.content.ui.user;

import android.view.View;

import com.app.qunadai.content.base.BaseActivity;

/**
 * Created by wayne on 2017/5/11.
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_OFF);
        setTitle("登录");
    }

    @Override
    protected View createCenterView() {
        return null;
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

    }
}
