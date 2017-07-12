package com.app.qunadai.content.ui.bbs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

/**
 * Created by wayne on 2017/7/10.
 */

public class HelpActivity extends BaseActivity {

    @Override
    protected void updateTopViewHideAndShow() {
setTitleText("帮助中心");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_help, null);
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
