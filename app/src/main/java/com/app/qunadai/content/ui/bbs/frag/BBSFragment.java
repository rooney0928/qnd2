package com.app.qunadai.content.ui.bbs.frag;

import android.os.Bundle;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;

/**
 * Created by wayne on 2017/5/10.
 */

public class BBSFragment extends BaseFragment {

    public static BBSFragment getInstance() {
        BBSFragment bbsFragment = new BBSFragment();
        Bundle bundle = new Bundle();
        bbsFragment.setArguments(bundle);
        return bbsFragment;
    }
    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_bbs,null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
