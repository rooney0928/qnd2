package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.third.eventbus.EventTurn;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wayne on 2017/11/7.
 */

public class CplInfo2Fragment extends BaseFragment implements FragmentBackPressed {

    public static CplInfo2Fragment getInstance() {
        CplInfo2Fragment cplInfo2Fragment = new CplInfo2Fragment();
        Bundle bundle = new Bundle();
        cplInfo2Fragment.setArguments(bundle);
        return cplInfo2Fragment;
    }
    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_auth2_cpl, null);
        return root;
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
    public void onBackPressed() {
        EventBus.getDefault().post(new EventTurn(0, "cplInfo"));
    }
}
