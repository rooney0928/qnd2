package com.app.qunadai.content.ui.home.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.content.adapter.LoanAdapter;
import com.app.qunadai.content.base.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/12.
 */

public class RecommendFragment extends BaseFragment {
    @BindView(R.id.rv_recommend)
    RecyclerView rv_recommend;

    LinearLayoutManager linearLayoutManager;
    LoanAdapter adapter;

    public static RecommendFragment getInstance() {
        RecommendFragment recommendFragment= new RecommendFragment();
        Bundle bundle = new Bundle();
        recommendFragment.setArguments(bundle);
        return recommendFragment;
    }

    @Override
    protected View createRootView() {
        View view = View.inflate(getActivity(), R.layout.fragment_recommend,null);
        return view;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        adapter = new LoanAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_recommend.setLayoutManager(linearLayoutManager);
        rv_recommend.setAdapter(adapter);
    }

    public void setRecommendData(List<LoanDetail> list){
        adapter.setList(list);
    }
}
