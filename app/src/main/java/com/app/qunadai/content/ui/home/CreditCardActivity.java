package com.app.qunadai.content.ui.home;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.app.qunadai.R;
import com.app.qunadai.bean.CreditCard;
import com.app.qunadai.bean.CreditStrategy;
import com.app.qunadai.content.adapter.CreditCardAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.CreditCardContract;
import com.app.qunadai.content.presenter.CreditCardPresenter;
import com.app.qunadai.content.ui.product.BrowserActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventLoc;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/7/21.
 */

public class CreditCardActivity extends BaseActivity implements CreditCardContract.View {

    private CreditCardPresenter creditCardPresenter;

    @BindView(R.id.vf_hot)
    ViewFlipper vf_hot;
    @BindView(R.id.rv_cards)
    RecyclerView rv_cards;

    @BindView(R.id.tv_credit_location)
    TextView tv_credit_location;


    CreditCardAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    //定位
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationClientOption;

    boolean autoSearch = true;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("办信用卡");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_credit_card, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        creditCardPresenter = new CreditCardPresenter(this);

        EventBus.getDefault().register(this);
        adapter = new CreditCardAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_cards.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_cards.setLayoutManager(linearLayoutManager);
        rv_cards.setAdapter(adapter);


        //首先判断权限
        AndPermission.with(this)
                .requestCode(300)

                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .callback(this)
                .start()
        ;
        showLoading();
        tv_credit_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLocation = new Intent(CreditCardActivity.this, LocationActivity.class);
                startActivity(intentLocation);
            }
        });
    }


    @PermissionYes(300)
    private void getPermissionYes(List<String> grantedPermissions) {
        // Successfully.
//        update();
        //获取权限成功
        initLocation();
        startLocation();
    }

    private void initLocation() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationClientOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationClientOption);
        //设置定位监听
        locationClient.setLocationListener(locationListener);

    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationClientOption);
        // 启动定位
        locationClient.startLocation();
    }

    private int GPS_REQUEST_CODE = 10;

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location && !CommUtil.isNull(location.getCity())) {
                tv_credit_location.setText(location.getCity());
                creditCardPresenter.getCreditCardList(location.getCity(), 0, 500);

            } else {
                tv_credit_location.setText("定位失败");
                showLocSetting();
            }
        }


    };

    private void showLocSetting() {

    }


    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    // 根据控件的选择，重新设置定位参数
    private void resetOption() {
        // 设置是否需要显示地址信息
        locationClientOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationClientOption.setGpsFirst(true);
        // 设置是否开启缓存
        locationClientOption.setLocationCacheEnable(true);
        // 设置是否单次定位
        locationClientOption.setOnceLocation(true);
        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
        locationClientOption.setOnceLocationLatest(true);
        //设置是否使用传感器
        locationClientOption.setSensorEnable(true);
        //设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        /*
        String strInterval = etInterval.getText().toString();
        if (!TextUtils.isEmpty(strInterval)) {
            try{
                // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
                locationOption.setInterval(Long.valueOf(strInterval));
            }catch(Throwable e){
                e.printStackTrace();
            }
        }

        String strTimeout = etHttpTimeout.getText().toString();
        if(!TextUtils.isEmpty(strTimeout)){
            try{
                // 设置网络请求超时时间
                locationOption.setHttpTimeOut(Long.valueOf(strTimeout));
            }catch(Throwable e){
                e.printStackTrace();
            }
        }
         */
    }

    @PermissionNo(300)
    private void getPermissionNo(List<String> deniedPermissions) {
        // Failure.
//        showSettingDialog();
        //获取权限失败

    }

    @Override
    public void initViewData() {
        if (NetworkUtil.checkNetwork(this)) {
            creditCardPresenter.getStrategy(0, 500);
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
        showLoading();
    }

    @Override
    public void requestEnd() {
        hideLoading();
    }

    @Override
    public void getStrategy(CreditStrategy bean) {
        List<CreditStrategy.ContentBeanX.CreditCardStrategyBean.ContentBean> list =
                bean.getContent().getCreditCardStrategy().getContent();

        vf_hot.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(this, R.layout.item_hot, null);
            final CreditStrategy.ContentBeanX.CreditCardStrategyBean.ContentBean strategy = list.get(i);
            final TextView tv_hot = (TextView) view.findViewById(R.id.tv_hot);
            tv_hot.setText(strategy.getTitle());

            view.setTag(i);
//            int position = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtil.showToast(CreditCardActivity.this, CommUtil.getText(tv_hot));
//                    int pos = (int) v.getTag();
//                    ToastUtil.showToast(CreditCardActivity.this, pos + "");
                    if (CommUtil.isNull(getToken())) {
                        exeLogin();
                    } else {
                        Intent intent = new Intent(CreditCardActivity.this, BrowserActivity.class);
                        String root = "http://192.168.11.129:7777/";
                        String url = root + "html/creditCard/ccStrategy/ccStrategy.html?articleId=" + strategy.getId()
                                + "&token=" + getToken();
                        intent.putExtra("url", url);
                        intent.putExtra("title", strategy.getTitle());
                        startActivity(intent);
                    }

                }
            });

            vf_hot.addView(view);

        }
    }

    @Override
    public void getStrategyFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void getCreditCardList(CreditCard bean) {
        adapter.setList(bean.getContent().getBanks().getContent());
    }

    @Override
    public void getCreditCardListFail(String error) {
        ToastUtil.showToast(this, error);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventLoc event) {
        tv_credit_location.setText(event.getLocation());
        if (NetworkUtil.checkNetwork(this)) {
            creditCardPresenter.getCreditCardList(event.getLocation(), 0, 500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        if (locationClient != null) {
            locationClient.onDestroy();
            locationClient = null;
            locationClientOption = null;

        }
    }
}
