package com.app.qunadai.content.ui.me;

import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

/**
 * Created by wayne on 2017/5/15.
 */

public class AboutActivity extends BaseActivity {
    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("关于我们");
    }

    @Override
    protected View createCenterView() {
        return View.inflate(this, R.layout.activity_about,null);
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
}
