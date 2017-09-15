package com.app.qunadai.content.ui.me.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.MeBean;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Me5Contract;
import com.app.qunadai.content.presenter.v5.Me5Presenter;
import com.app.qunadai.content.ui.me.AuthActivity;
import com.app.qunadai.content.ui.me.ExploreActivity;
import com.app.qunadai.content.ui.me.SettingActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ReqKey;

import butterknife.BindView;

import static com.app.qunadai.MyApp.context;

/**
 * Created by wayne on 2017/9/7.
 */

public class Me5Fragment extends BaseFragment implements Me5Contract.View, View.OnClickListener {

    private Me5Presenter me5Presenter;
    @BindView(R.id.iv_me_avatar)
    ImageView iv_me_avatar;
    @BindView(R.id.tv_me_limit)
    TextView tv_me_limit;
    @BindView(R.id.tv_me_name)
    TextView tv_me_name;

    @BindView(R.id.ll_me_explore)
    LinearLayout ll_me_explore;
    @BindView(R.id.ll_me_calendar)
    LinearLayout ll_me_calendar;
    @BindView(R.id.ll_me_share)
    LinearLayout ll_me_share;
    @BindView(R.id.ll_me_message)
    LinearLayout ll_me_message;
    @BindView(R.id.ll_me_setting)
    LinearLayout ll_me_setting;


    public static Me5Fragment getInstance() {
        Me5Fragment meFragment = new Me5Fragment();
        Bundle bundle = new Bundle();
        meFragment.setArguments(bundle);
        return meFragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }


    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_me5, null);
        return root;
    }

    @Override
    protected void initData() {
        me5Presenter = new Me5Presenter(this);

        tv_me_limit.setOnClickListener(this);
        ll_me_explore.setOnClickListener(this);
        ll_me_calendar.setOnClickListener(this);
        ll_me_share.setOnClickListener(this);
        ll_me_message.setOnClickListener(this);
        ll_me_setting.setOnClickListener(this);

    }

    public void requestData() {

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
//tv_me_limit.setOnClickListener(this);
//        ll_me_explore.setOnClickListener(this);
//        ll_me_calendar.setOnClickListener(this);
//        ll_me_share.setOnClickListener(this);
//        ll_me_message.setOnClickListener(this);
//        ll_me_setting.setOnClickListener(this);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_me_explore:
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                Intent intentExplore = new Intent(getActivity(), ExploreActivity.class);
                startActivity(intentExplore);
                break;
            case R.id.ll_me_calendar:

                break;
            case R.id.ll_me_share:

                break;
            case R.id.ll_me_message:
            case R.id.tv_me_limit:
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                Intent intentAuth = new Intent(getActivity(), AuthActivity.class);
                startActivity(intentAuth);
                break;
            case R.id.ll_me_setting:
                Intent intentSet = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intentSet, ReqKey.REQ_QUIT_SYSTEM);
                break;
        }
    }
    public void requestUserData(){
        me5Presenter.requestCurrent(getToken());
    }

    @Override
    public void getCurrent(MeBean meBean) {
        String imgUrl = RxHttp.ROOT + "attachments/" + meBean.getContent().getUser().getAvatar();
        ImgUtil.loadRound(getActivity(), imgUrl, iv_me_avatar);

        tv_me_name.setText(meBean.getContent().getUser().getNick());
        if (CommUtil.isNull(tv_me_name)) {
            tv_me_name.setText(PrefUtil.getString(getActivity(), PrefKey.PHONE, ""));
        }
    }

    @Override
    public void getCurrentFail(String error) {

    }
}
