package com.app.qunadai.content.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/8.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 内容
     */
//    protected FrameLayout fl_content;
//    protected View contentView;


    /**
     * 注:使用时需强转
     */
    protected Activity mActivity;

    protected View mRootView;
    // Fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.e("text", "fragment  onCreate ");
        mActivity = getActivity();
    }

    // Fragment填充布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.e("text", "fragment  onCreateView ");
        mRootView = createRootView();
        ButterKnife.bind(this,mRootView);
        try {
            return mRootView;
        } catch (Exception e) {
            throw new IllegalStateException("the method createPager() cannot return null");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();


    /**
     * 创建每一碎片的pager类
     *
     * @return
     */
    protected abstract View createRootView();

    /**
     * 获取当前的fragment
     *
     * @return
     */
    public Fragment getFragment() {
        return this;
    }
}
