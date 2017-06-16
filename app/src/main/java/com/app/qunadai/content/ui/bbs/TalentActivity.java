package com.app.qunadai.content.ui.bbs;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.PostAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;

import butterknife.BindView;

/**
 * 贷款达人页面
 * Created by wayne on 2017/6/5.
 */

public class TalentActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    LinearLayoutManager linearLayoutManager;
    PostAdapter adapter;


    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_talent,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        adapter = new PostAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);
    }

    @Override
    public void initViewData() {

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
    protected void updateTopViewHideAndShow() {
        setTitleText("网贷达人");
        setTitleRightImg(R.mipmap.ic_post);
        setTitleRightEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
