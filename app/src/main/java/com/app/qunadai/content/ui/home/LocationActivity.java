package com.app.qunadai.content.ui.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.content.adapter.HotCityAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.LocationContract;
import com.app.qunadai.content.presenter.LocationPresenter;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by wayne on 2017/7/27.
 */

public class LocationActivity extends BaseActivity implements LocationContract.View{
    private LocationPresenter locationPresenter;

    @BindView(R.id.rv_lock)
    RecyclerView rv_lock;

    GridLayoutManager gridLayoutManager;
    HotCityAdapter hotCityAdapter;



    @Override
    protected void initView() {
        locationPresenter = new LocationPresenter(this);
        gridLayoutManager = new GridLayoutManager(this, 4);
        hotCityAdapter = new HotCityAdapter(this);

        rv_lock.setLayoutManager(gridLayoutManager);
        rv_lock.setAdapter(hotCityAdapter);
    }

    @Override
    public void initViewData() {
        if (NetworkUtil.checkNetwork(this)) {
            locationPresenter.getHotCity();
        }
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

    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_location, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }


    @Override
    public void getHotCity(HotCity bean) {
        hotCityAdapter.setList(bean.getContent().getDistrictList());
    }

    @Override
    public void getHotCityFail(String error) {
        ToastUtil.showToast(this, error);

    }
}
