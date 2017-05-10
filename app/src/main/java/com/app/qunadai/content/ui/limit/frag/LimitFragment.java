package com.app.qunadai.content.ui.limit.frag;

import android.os.Bundle;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;

/**
 * Created by wayne on 2017/5/10.
 */

public class LimitFragment extends BaseFragment {

    public static LimitFragment getInstance() {
        LimitFragment limitFragment = new LimitFragment();
        Bundle bundle = new Bundle();
        limitFragment.setArguments(bundle);
        return limitFragment;
    }
    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_limit,null);
        return root;
    }

    @Override
    protected void initData() {

    }
}
