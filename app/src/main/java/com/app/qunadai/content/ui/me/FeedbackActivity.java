package com.app.qunadai.content.ui.me;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.ui.me.frag.FeedStep1Fragment;
import com.app.qunadai.content.ui.me.frag.FeedStep2Fragment;
import com.app.qunadai.third.eventbus.EventTurn;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/6.
 */

public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.vp_feedback)
    ViewPager vp_feedback;

    private FeedStep1Fragment feedStep1Fragment;
    private FeedStep2Fragment feedStep2Fragment;
    private List<Fragment> fragments;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("意见反馈");
    }

    @Override
    protected View createCenterView() {
        View content = View.inflate(this, R.layout.activity_feedback, null);
        return content;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        fragments = new ArrayList<>();
        feedStep1Fragment = FeedStep1Fragment.getInstance();
        feedStep2Fragment = FeedStep2Fragment.getInstance();

        fragments.add(feedStep1Fragment);
        fragments.add(feedStep2Fragment);
    }

    @Override
    public void initViewData() {
        vp_feedback.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));
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
        if("feedback".equalsIgnoreCase(event.getType())){
            vp_feedback.setCurrentItem(1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}