package com.app.qunadai.content.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/1/4.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TITLE_ON_BACK_OFF = 0;
    public static final int TITLE_ON_BACK_ON = 2;
    public static final int TITLE_OFF = 4;


    /**
     * activity 底部布局的容器 默认状态显示
     */
    FrameLayout fl_bottom_base;
    /**
     * activity 中间布局容器
     */
    FrameLayout fl_center_base;
    //标题栏部分
    @BindView(R.id.rl_top)
    RelativeLayout rl_top;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_title)
    TextView tv_title;


    View root;
    /**
     * 底部的view 注:使用时需强转
     */
    View bottomView;
    /**
     * 中间view 注:使用时需强转
     */
    View centerView;


    /**
     * 创建根布局
     *
     * @return
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = View.inflate(this, R.layout.activity_base, null);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(root);
        initContentView();
    }

    /**
     * 初始化根布局中view
     */
    protected void initContentView() {
        centerView = createCenterView();
        if (centerView != null) {
            fl_center_base = (FrameLayout) root.findViewById(R.id.fl_center_base);
            fl_center_base.addView(centerView);
        }
        bottomView = createBottomView();
        if (bottomView != null) {
            fl_bottom_base = (FrameLayout) root.findViewById(R.id.fl_bottom_base);
            fl_bottom_base.addView(bottomView);
        }
        ButterKnife.bind(this, root);
        updateTopViewHideAndShow();
        //设置参数 监听
//        initRootData();
        rl_back.setOnClickListener(this);
        initView();
        initViewData();

    }

//    /**
//     * 设置数据 监听
//     */
//    private void initRootData() {
//
//    }

    /**
     * 控制标题栏view
     */
    protected abstract void updateTopViewHideAndShow();


    /**
     * 创建中间view
     *
     * @return
     */
    protected abstract View createCenterView();


    /**
     * 创建activity 顶部的布局
     *
     * @return
     */
    protected abstract View createBottomView();

    /**
     * 初始化添加的view
     */
    protected abstract void initView();

    /**
     * 初始化添加的view的数据 如设置监听.数据等 注:需要activity 的 initData方法中主动调用 onCreate
     */
    public abstract void initViewData();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back://返回
                this.finish();
                break;
        }
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setTitleBarStatus(int status) {
        switch (status) {
            case TITLE_ON_BACK_ON:
                rl_top.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.VISIBLE);
                rl_back.setVisibility(View.VISIBLE);
                break;
            case TITLE_ON_BACK_OFF:
                rl_top.setVisibility(View.VISIBLE);
                tv_title.setVisibility(View.VISIBLE);
                rl_back.setVisibility(View.GONE);
                break;
            case TITLE_OFF:
                rl_top.setVisibility(View.GONE);
                break;
        }
    }



}
