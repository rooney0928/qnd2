package com.app.qunadai.content.ui.me.frag;

import android.os.Bundle;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;

/**
 * Created by wayne on 2017/9/7.
 */

public class Me5Fragment extends BaseFragment {

    public static Me5Fragment getInstance() {
        Me5Fragment meFragment = new Me5Fragment();
        Bundle bundle = new Bundle();
        meFragment.setArguments(bundle);
        return meFragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_me5, null);
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


}
