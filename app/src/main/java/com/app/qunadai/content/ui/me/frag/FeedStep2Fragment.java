package com.app.qunadai.content.ui.me.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/6.
 */

public class FeedStep2Fragment extends BaseFragment {


    @BindView(R.id.tv_feedback_submit)
    TextView tv_feedback_submit;

    public static FeedStep2Fragment getInstance() {
        FeedStep2Fragment feedStep2Fragment = new FeedStep2Fragment();
        Bundle bundle = new Bundle();
        feedStep2Fragment.setArguments(bundle);
        return feedStep2Fragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        tv_feedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_feedback2, null);
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
