package com.app.qunadai.content.ui.bbs;

import android.view.View;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/14.
 */

public class AnswerActivity extends BaseActivity {
    @BindView(R.id.tv_answer_title)
    TextView tv_answer_title;
    @BindView(R.id.tv_answer_content)
    TextView tv_answer_content;


    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("帮助中心");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_answer,null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        tv_answer_title.setText(title);
        tv_answer_content.setText(content);
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
