package com.app.qunadai.content.ui.bbs.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.QuestionAdapter;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.utils.CommUtil;

import org.json.JSONArray;
import org.json.JSONException;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/10.
 */

public class BBSFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    QuestionAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    public static BBSFragment getInstance() {
        BBSFragment bbsFragment = new BBSFragment();
        Bundle bundle = new Bundle();
        bbsFragment.setArguments(bundle);
        return bbsFragment;
    }
    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_help,null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        adapter = new QuestionAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);
        String json = CommUtil.readAssert(getActivity(),"question.json");
        try {
            JSONArray arr = new JSONArray(json);
            adapter.setQuestions(arr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
