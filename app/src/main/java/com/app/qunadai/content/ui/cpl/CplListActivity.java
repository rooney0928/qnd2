package com.app.qunadai.content.ui.cpl;

import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.third.tablayout.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/9.
 */

public class CplListActivity extends BaseActivity {
    private ArrayList<CustomTabEntity> mTabEntities= new ArrayList<>();
    private String[] mTitles = new String[]{"可申请", "已申请"};

    @BindView(R.id.ctl_tab_cpl)
    CommonTabLayout ctl_tab_cpl;

    @Override
    protected void updateTopViewHideAndShow() {

    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_cpl_products, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        for (String title : mTitles) {
            mTabEntities.add(new TabEntity(title));
        }
        ctl_tab_cpl.setTabData(mTabEntities);
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
