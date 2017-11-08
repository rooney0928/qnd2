package com.app.qunadai.content.ui.cpl;

import android.support.v4.app.Fragment;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.content.ui.cpl.frag.CplInfo1Fragment;
import com.app.qunadai.content.ui.cpl.frag.CplInfo2Fragment;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.third.eventbus.EventTurn;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/7.
 */

public class CplInfoActivity extends BaseActivity {
    @BindView(R.id.vp_info)
    NoScrollViewPager vp_info;

    private CplInfo1Fragment cplInfo1Fragment;
    private CplInfo2Fragment cplInfo2Fragment;
    private List<Fragment> fragments;


    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("完善信息");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_cpl_info, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        fragments = new ArrayList<>();

        cplInfo1Fragment = CplInfo1Fragment.getInstance();
        cplInfo2Fragment = CplInfo2Fragment.getInstance();

        fragments.add(cplInfo1Fragment);
        fragments.add(cplInfo2Fragment);
    }

    @Override
    public void initViewData() {
        vp_info.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventTurn event) {
        if("cplInfo".equalsIgnoreCase(event.getType())){
            vp_info.setCurrentItem(event.getPage());
        }
    }

    @Override
    public void onBackPressed() {
        int currentItem = vp_info.getCurrentItem();
        FragmentBackPressed fragment = (FragmentBackPressed) fragments
                .get(currentItem);
        fragment.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
