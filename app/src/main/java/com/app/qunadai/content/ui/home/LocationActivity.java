package com.app.qunadai.content.ui.home;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.AllCity;
import com.app.qunadai.bean.City;
import com.app.qunadai.bean.bbs.HotCity;
import com.app.qunadai.content.adapter.HotCityAdapter;
import com.app.qunadai.content.adapter.LocationAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.LocationContract;
import com.app.qunadai.content.presenter.LocationPresenter;
import com.app.qunadai.content.view.SideBar;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.widget.StickyNestedScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/7/27.
 */

public class LocationActivity extends BaseActivity implements LocationContract.View {
    private LocationPresenter locationPresenter;

    @BindView(R.id.rv_hot)
    RecyclerView rv_hot;
    @BindView(R.id.rv_location)
    RecyclerView rv_location;
    @BindView(R.id.ll_hot_part)
    LinearLayout ll_hot_part;
    @BindView(R.id.tv_loc_search)
    TextView tv_loc_search;

    @BindView(R.id.tv_loc_dialog)
    TextView tv_loc_dialog;
    @BindView(R.id.sb_loc_index)
    SideBar sb_loc_index;
    @BindView(R.id.snsv_location_layout)
    StickyNestedScrollView snsv_location_layout;

    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    HotCityAdapter hotCityAdapter;
    LocationAdapter locationAdapter;

    private List<List<City>> list;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        locationPresenter = new LocationPresenter(this);
        gridLayoutManager = new GridLayoutManager(this, 4);
        hotCityAdapter = new HotCityAdapter(this);
        locationAdapter = new LocationAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        list = new ArrayList<>();

        rv_hot.setLayoutManager(gridLayoutManager);
        rv_hot.setAdapter(hotCityAdapter);

        rv_location.setLayoutManager(linearLayoutManager);
        rv_location.setAdapter(locationAdapter);

        rv_location.setNestedScrollingEnabled(false);
//        rv_location.addOnScrollListener(new RecyclerViewListener());
        tv_loc_search.setOnClickListener(this);
    }

    @Override
    public void initViewData() {
        sb_loc_index.setTextView(tv_loc_dialog);


        if (NetworkUtil.checkNetwork(this)) {
            locationPresenter.getHotCity();
            locationPresenter.getAllCity();
        }
    }

    private void moveTo(int n) {

        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
//        int lastItem = linearLayoutManager.findLastVisibleItemPosition();

//        int hotH = ll_hot_part.getHeight();
        int hotH = rv_location.getTop();

        int top = rv_location.getChildAt(n - firstItem).getTop();
//            rv_location.smoothScrollBy(0, top);
        snsv_location_layout.scrollTo(0, top + hotH);
/**
 if (n <= firstItem) {
 rv_location.smoothScrollToPosition(n);
 } else if (n <= lastItem) {
 int top = rv_location.getChildAt(n - firstItem).getTop();
 //            rv_location.smoothScrollBy(0, top);
 snsv_location_layout.scrollTo(0,top);
 } else {
 rv_location.smoothScrollToPosition(n);
 move = true;
 }
 */

    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        showLoading();
    }

    @Override
    public void requestEnd() {
        hideLoading();
    }

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("地址选择");

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

    @Override
    public void getAllCity(AllCity bean) {

        list.clear();
        AllCity.ContentBean.DistrictMapBean locMap = bean.getContent().getDistrictMap();
        list.add(locMap.getA());
        list.add(locMap.getB());
        list.add(locMap.getC());
        list.add(locMap.getD());
        list.add(locMap.getE());
        list.add(locMap.getF());
        list.add(locMap.getG());
        list.add(locMap.getH());
        list.add(locMap.getI());
        list.add(locMap.getJ());
        list.add(locMap.getK());
        list.add(locMap.getL());
        list.add(locMap.getM());
        list.add(locMap.getN());
        list.add(locMap.getO());
        list.add(locMap.getP());
        list.add(locMap.getQ());
        list.add(locMap.getR());
        list.add(locMap.getS());
        list.add(locMap.getT());
        list.add(locMap.getU());
        list.add(locMap.getV());
        list.add(locMap.getW());
        list.add(locMap.getX());
        list.add(locMap.getY());
        list.add(locMap.getZ());

        locationAdapter.setList(list);

        sb_loc_index.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s, int index) {
                LogU.t(s + "--" + index);
                if (index > 0) {
                    moveTo(index - 1);
                } else {
                    snsv_location_layout.scrollTo(0, 0);
                }
            }
        });
    }

    @Override
    public void getAllCityFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_loc_search:
                Intent intentSearch = new Intent(this, FindCityActivity.class);
                startActivity(intentSearch);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventClose event) {
        if ("loc".equalsIgnoreCase(event.getPage())) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
