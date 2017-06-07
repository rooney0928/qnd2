package com.app.qunadai.content.ui.bbs;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.CommentAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wayne on 2017/6/5.
 */

public class PostDetailActivity extends BaseActivity{

    @BindView(R.id.rv_comment)
    RecyclerView rv_comment;

    CommentAdapter commentAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("详情");
        setTitleRightImg(R.mipmap.ic_repost);
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_post_detail,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        commentAdapter = new CommentAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_comment.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_comment.setLayoutManager(linearLayoutManager);
        rv_comment.setAdapter(commentAdapter);
        //保持滑动惯性
        rv_comment.setNestedScrollingEnabled(false);
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
