package com.app.qunadai.content.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.ui.user.LoginActivity;
import com.app.qunadai.content.ui.user.SignInActivity;
import com.app.qunadai.utils.AppManager;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/1/4.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, BaseView {

    public static final int TITLE_ON_BACK_OFF = 0;
    public static final int TITLE_ON_BACK_ON = 2;
    public static final int TITLE_OFF = 4;
    public static final int TITLE_ON_RIGHT_ON = 6;


    /**
     * activity 底部布局的容器 默认状态显示
     */
    FrameLayout fl_bottom_base;
    /**
     * activity 中间布局容器
     */
    FrameLayout fl_center_base;
    //标题栏部分
    @BindView(R.id.ll_top)
    LinearLayout ll_top;

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_title_left)
    TextView tv_title_left;
    @BindView(R.id.tv_title_right)
    TextView tv_title_right;
    @BindView(R.id.iv_title_img_right)
    ImageView iv_title_img_right;

    @BindView(R.id.ll_root)
    LinearLayout ll_root;


    View root;
    /**
     * 底部的view 注:使用时需强转
     */
    View bottomView;
    /**
     * 中间view 注:使用时需强转
     */
    View centerView;

    public InputMethodManager manager;

    public ProgressDialog pdLoad;

    /**
     * 创建根布局
     *
     * @return
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = View.inflate(this, R.layout.activity_base, null);
//        StatusBarUtil.StatusBarLightMode(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(root);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        AppManager.add(this);
        initContentView();
    }

    public void setNotification() {
        ll_root.setClipToPadding(true);
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
        fl_bottom_base = (FrameLayout) root.findViewById(R.id.fl_bottom_base);
        if (bottomView != null) {
            fl_bottom_base.addView(bottomView);
        } else {
            fl_bottom_base.setVisibility(View.GONE);
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


    public void setTitleRight(String title) {
        tv_title_right.setText(title);
    }

    //标题栏相关
    public void clearTitleBar() {
        ll_top.setVisibility(View.GONE);
//        tv_title.setVisibility(View.GONE);
        rl_back.setVisibility(View.GONE);
        tv_title_right.setVisibility(View.GONE);
    }

    public void setTitleBarVisible(boolean isShow) {
        ll_top.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setTitleLeftEvent(View.OnClickListener listener) {
        tv_title_left.setOnClickListener(listener);
    }

    public void setTitleRightEvent(View.OnClickListener listener) {
        tv_title_right.setOnClickListener(listener);
        iv_title_img_right.setOnClickListener(listener);
    }

    public void setBackListener(View.OnClickListener listener) {
        rl_back.setOnClickListener(listener);
    }

    public void setTitleText(String title) {
        tv_title.setText(title);
    }

    public void setTitleLeftText(String title) {
        tv_title_left.setText(title);
        tv_title_left.setVisibility(View.VISIBLE);
    }

    public void setTitleRightText(String title) {
        tv_title_right.setText(title);
        tv_title_right.setVisibility(View.VISIBLE);
    }

    public void setTitleRightImg(int imgId) {
        iv_title_img_right.setImageResource(imgId);
        iv_title_img_right.setVisibility(View.VISIBLE);
    }

    public void setBackVisible(boolean isShow) {
        rl_back.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        hideKeyboard(event);
        return super.onTouchEvent(event);
    }

    /**
     * 点击其他地方隐藏键盘
     *
     * @param event
     */
    private void hideKeyboard(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void tokenFail() {
        exeLogin();
    }


    public void exeLogin() {
        PrefUtil.removeItem(this, PrefKey.TOKEN);
        Intent intentLogin = new Intent(this, SignInActivity.class);
        startActivity(intentLogin);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.remove(this);
    }


    //加载对话框
    public void showLoading() {
        if (pdLoad == null) {
            pdLoad = new ProgressDialog(this);
            pdLoad.setMessage("正在加载中");
            pdLoad.setIndeterminate(true);
            pdLoad.setCancelable(true);
        }
        pdLoad.show();
    }

    public void hideLoading() {
        if (pdLoad != null && pdLoad.isShowing()) {
            pdLoad.dismiss();
        }
    }

    public String getToken() {
        return PrefUtil.getString(this, PrefKey.TOKEN, "");
    }


}
