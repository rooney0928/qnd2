package com.app.qunadai.content.ui.bbs.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.BBSHomeAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.ui.bbs.PostActivity;
import com.app.qunadai.content.ui.bbs.TalentActivity;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/22.
 */

public class BBSFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.ll_bbs_post)
    LinearLayout ll_bbs_post;

    @BindView(R.id.ll_bbs_talent)
    LinearLayout ll_bbs_talent;

    LinearLayoutManager linearLayoutManager;
    BBSHomeAdapter adapter;


    public static BBSFragment getInstance() {
        BBSFragment bbsFragment = new BBSFragment();
        Bundle bundle = new Bundle();
        bbsFragment.setArguments(bundle);
        return bbsFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_bbs, null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

        adapter = new BBSHomeAdapter();
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);

        ll_bbs_post.setOnClickListener(this);
        ll_bbs_talent.setOnClickListener(this);

    }

    @Override
    public void requestStart() {

    }

    @Override
    public void requestEnd() {

    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_bbs_post:
                //发帖
                Intent intentPost = new Intent(getActivity(), PostActivity.class);
                startActivity(intentPost);
                break;
            case R.id.ll_bbs_talent:
                //进入贷款达人
                Intent intentTalent = new Intent(getActivity(), TalentActivity.class);
                startActivity(intentTalent);
                break;
        }
    }
}

