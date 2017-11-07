package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.third.eventbus.EventTurn;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/7.
 */

public class CplInfo1Fragment extends BaseFragment implements FragmentBackPressed {
    @BindView(R.id.tv_cpl_submit)
    TextView tv_cpl_submit;

    public static CplInfo1Fragment getInstance() {
        CplInfo1Fragment cplInfo1Fragment = new CplInfo1Fragment();
        Bundle bundle = new Bundle();
        cplInfo1Fragment.setArguments(bundle);
        return cplInfo1Fragment;
    }
    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        tv_cpl_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventTurn(1,"cplInfo"));
            }
        });
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_auth1_cpl, null);
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
        getActivity().finish();
    }

}
