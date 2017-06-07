package com.app.qunadai.content.ui.bbs;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.ReplyAdapter;
import com.app.qunadai.content.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wayne on 2017/6/7.
 */

public class ReplyActivity extends BaseActivity {

    @BindView(R.id.rv_reply)
    RecyclerView rv_reply;

    ReplyAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("更多评论");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_reply,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter = new ReplyAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                
            }
        });
        rv_reply.setLayoutManager(linearLayoutManager);
        rv_reply.setAdapter(adapter);


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


}
