package com.app.qunadai.content.ui.bbs;

import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

/**
 * Created by wayne on 2017/6/5.
 */

public class PostDetailActivity extends BaseActivity{
    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("详情");
        setTitleRightImg(R.mipmap.ic_repost);
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_post_detail,null);
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
