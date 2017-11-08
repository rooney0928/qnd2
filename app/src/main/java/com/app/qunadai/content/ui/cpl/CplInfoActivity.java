package com.app.qunadai.content.ui.cpl;

import android.support.v4.app.Fragment;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.CplBase;
import com.app.qunadai.bean.cpl.CToken;
import com.app.qunadai.bean.cpl.UserInfo;
import com.app.qunadai.content.adapter.MainFragmentPagerAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.cpl.AuthInfoContract;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.content.presenter.cpl.AuthInfoPresenter;
import com.app.qunadai.content.ui.cpl.frag.CplInfo1Fragment;
import com.app.qunadai.content.ui.cpl.frag.CplInfo2Fragment;
import com.app.qunadai.content.view.NoScrollViewPager;
import com.app.qunadai.third.eventbus.EventCplInfo;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/7.
 */

public class CplInfoActivity extends BaseActivity implements AuthInfoContract.View{
    private AuthInfoPresenter authInfoPresenter;
    @BindView(R.id.vp_info)
    NoScrollViewPager vp_info;


    private CplInfo1Fragment cplInfo1Fragment;
    private CplInfo2Fragment cplInfo2Fragment;
    private List<Fragment> fragments;



    private UserInfo userInfo;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("完善信息");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_cpl_info, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        authInfoPresenter = new AuthInfoPresenter(this);

        fragments = new ArrayList<>();

        cplInfo1Fragment = CplInfo1Fragment.getInstance();
        cplInfo2Fragment = CplInfo2Fragment.getInstance();

        fragments.add(cplInfo1Fragment);
        fragments.add(cplInfo2Fragment);

        userInfo = new UserInfo();
        if(NetworkUtil.checkNetwork(this)){
            authInfoPresenter.reqCplToken("15000983435");
        }
    }

    @Override
    public void initViewData() {
        vp_info.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragments));

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventTurn event) {
        if("cplInfo".equalsIgnoreCase(event.getType())){
            vp_info.setCurrentItem(event.getPage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventCplInfo event) {
        switch (event.getPage()){
            case 1:
                UserInfo user1 = event.getUserInfo();
                userInfo.setName(user1.getName());
                userInfo.setIdcard(user1.getIdcard());
                userInfo.setWechat(user1.getWechat());
                userInfo.setQq(user1.getQq());
                userInfo.setEdu(user1.getEdu());
                break;
            case 2:
                UserInfo user2 = event.getUserInfo();
                userInfo.setProvince(user2.getProvince());
                userInfo.setCity(user2.getCity());
                userInfo.setDistrict(user2.getDistrict());
                userInfo.setLiving(user2.getLiving());
                userInfo.setShebao(user2.getShebao());
                userInfo.setContactName(user2.getContactName());
                userInfo.setContactType(user2.getContactType());
                userInfo.setContactCell(user2.getContactCell());

                //然后提交
                if(CommUtil.isNull(getCplToken())){
                    ToastUtil.showToast(this,"token 为空，重新获取");
                    authInfoPresenter.reqCplToken("15000983435");
                }else{
                    authInfoPresenter.setCplUserInfo(userInfo,getCplToken());
                }
                break;
        }

    }

    @Override
    public void onBackPressed() {
        int currentItem = vp_info.getCurrentItem();
        FragmentBackPressed fragment = (FragmentBackPressed) fragments
                .get(currentItem);
        fragment.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getCplToken(CplBase<CToken> bean) {
        PrefUtil.putString(this, PrefKey.CPL_TOKEN,bean.getData().getToken());
    }

    @Override
    public void getCplTokenFail(String error) {
        ToastUtil.showToast(this,error);
    }

    @Override
    public void setCplUserInfo(CplBase bean) {
        if(bean.getStatus_code()==200){
            ToastUtil.showToast(this,"设置成功");
        }else{
            ToastUtil.showToast(this,"未设置成功");
        }

    }

    @Override
    public void setCplUserInfoFail(String error) {
        ToastUtil.showToast(this,error);

    }


}
