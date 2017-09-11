package com.app.qunadai.content.ui.user.frag;

import android.os.Bundle;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;

/**
 * Created by wayne on 2017/9/11.
 */

public class Step3PwdFragment extends BaseFragment {

    public static Step3PwdFragment getInstance() {
        Step3PwdFragment step3PwdFragment = new Step3PwdFragment();
        Bundle bundle = new Bundle();
        step3PwdFragment.setArguments(bundle);
        return step3PwdFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_sign_step3, null);
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
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }


}
