package com.app.qunadai.content.ui.user;

import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

/**
 * Created by wayne on 2017/5/11.
 */

public class RegisterActivity extends BaseActivity {
    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("注册");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_register,null);
        return view;
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
