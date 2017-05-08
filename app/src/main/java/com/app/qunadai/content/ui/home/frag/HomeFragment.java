package com.app.qunadai.content.ui.home.frag;

import android.view.View;
import android.widget.TextView;

import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.utils.ToastUtil;

/**
 * Created by wayne on 2017/5/8.
 */

public class HomeFragment extends BaseFragment {


    @Override
    protected View createRootView() {
        TextView tv= new TextView(getActivity());
        ToastUtil.showToast(getActivity(),"2"+this.getClass().toString());
        tv.setText("haha");
        return tv;
    }

    public static HomeFragment getInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }
}
