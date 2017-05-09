package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.utils.ToastUtil;

/**
 * Created by wayne on 2017/5/8.
 */

public class HomeFragment extends BaseFragment {


    public static HomeFragment getInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_home,null);
        return root;
    }
}
