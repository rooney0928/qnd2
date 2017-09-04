package com.app.qunadai.content.ui.user;

import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

/**
 * Created by wayne on 2017/9/4.
 */

public class SignInActivity extends BaseActivity {

    @Override
    protected View createCenterView() {
        return View.inflate(this, R.layout.activity_signin5, null);
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

    @Override
    protected void updateTopViewHideAndShow() {
        clearTitleBar();
    }


}
