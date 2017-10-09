package com.app.qunadai.content.ui.user;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.content.ui.user.frag.Step1PhoneFragment;
import com.app.qunadai.content.ui.user.frag.Step2CodeFragment;
import com.app.qunadai.content.ui.user.frag.Step3PwdFragment;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.LogU;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/11.
 */

public class SignActivity extends BaseActivity {

    @BindView(R.id.vp_sign)
    NoScrollViewPager vp_sign;

    private Step1PhoneFragment step1PhoneFragment;
    private Step2CodeFragment step2CodeFragment;
    private Step3PwdFragment step3PwdFragment;

    private List<Fragment> fragments = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Transition explode = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(R.transition.slide);
            //退出时使用
            getWindow().setEnterTransition(explode);
            //第一次进入时使用
            getWindow().setEnterTransition(explode);
            //再次进入时使用
            getWindow().setReenterTransition(explode);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void updateTopViewHideAndShow() {
        clearTitleBar();
    }

    @Override
    protected View createCenterView() {
        View content = View.inflate(this, R.layout.activity_sign, null);
        return content;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        step1PhoneFragment = Step1PhoneFragment.getInstance();
        step2CodeFragment = Step2CodeFragment.getInstance();
        step3PwdFragment = Step3PwdFragment.getInstance();

        fragments.add(step1PhoneFragment);
        fragments.add(step2CodeFragment);
        fragments.add(step3PwdFragment);

    }

    @Override
    public void initViewData() {
        vp_sign.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));

        vp_sign.setOffscreenPageLimit(3);

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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventProgress event) {
//        if (meFragment != null) {
//            meFragment.setMeMessage(event.getNickname());
//        }

        if (event.getType().equalsIgnoreCase("sign")) {
            if (event.isShow()) {
                showLoading();
            } else {
                hideLoading();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventTurn event) {
        if (event.getType().equalsIgnoreCase("sign")) {
            vp_sign.setCurrentItem(event.getPage());
            switch (event.getPage()) {
                case 0:
                    break;
                case 1:
                    if (step2CodeFragment != null) {
                        step2CodeFragment.startTimer();
                    }
                    
                    break;
                case 2:
                    if(step3PwdFragment!=null){
                        step3PwdFragment.setData();
                    }
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        int currentItem = vp_sign.getCurrentItem();
        FragmentBackPressed fragment = (FragmentBackPressed) fragments
                .get(currentItem);
        fragment.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slice_no, R.anim.slice_out_bottom);

    }
}
