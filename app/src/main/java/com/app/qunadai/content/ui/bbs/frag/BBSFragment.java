package com.app.qunadai.content.ui.bbs.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.BBSHomeAdapter;
import com.app.qunadai.content.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/22.
 */

public class BBSFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

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
        View root = View.inflate(getActivity(), R.layout.fragment_bbs,null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        adapter = new BBSHomeAdapter();
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);
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
}

