package com.app.qunadai.content.ui.me.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.MeBean;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.MeContract;
import com.app.qunadai.content.presenter.MePresenter;
import com.app.qunadai.content.ui.me.AccountActivity;
import com.app.qunadai.content.ui.me.PersonInfoActivity;
import com.app.qunadai.content.ui.me.SettingActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ReqKey;
import com.app.qunadai.utils.ToastUtil;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/10.
 */

public class MeFragment extends BaseFragment implements MeContract.View {
    private MePresenter mePresenter;
    @BindView(R.id.iv_me_avatar)
    ImageView iv_me_avatar;
    @BindView(R.id.tv_me_name)
    TextView tv_me_name;

    @BindView(R.id.rl_me_account)
    RelativeLayout rl_me_account;
    @BindView(R.id.rl_loan)
    RelativeLayout rl_loan;
    @BindView(R.id.rl_info)
    RelativeLayout rl_info;
    @BindView(R.id.rl_setting)
    RelativeLayout rl_setting;

    MeBean localMeBean;

    public static MeFragment getInstance() {
        MeFragment meFragment = new MeFragment();
        Bundle bundle = new Bundle();
        meFragment.setArguments(bundle);
        return meFragment;
    }


    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_me, null);
        return root;
    }


    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mePresenter = new MePresenter(this);
        ImgUtil.loadRound(getActivity(), R.mipmap.default_avatar, iv_me_avatar);
        mePresenter.requestCurrent(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));

        rl_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInfo = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intentInfo);
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, ReqKey.REQ_QUIT_SYSTEM);
            }
        });
        rl_me_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (localMeBean != null) {
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    intent.putExtra("nickname", localMeBean.getContent().getUser().getNick());
                    intent.putExtra("phone", localMeBean.getContent().getUser().getAccount().getMobileNumber());
                    intent.putExtra("avatar", localMeBean.getContent().getUser().getAvatar());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void getCurrent(MeBean meBean) {
        localMeBean = meBean;
        String imgUrl = RxHttp.ROOT + "/attachments/" + meBean.getContent().getUser().getAvatar();
        ImgUtil.loadRound(getActivity(), imgUrl, iv_me_avatar);
        tv_me_name.setText(meBean.getContent().getUser().getNick());
    }

    @Override
    public void getCurrentFail(String error) {
        ToastUtil.showToast(getActivity(), error + "-m");
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

    public void setNickname(String nickname) {
        tv_me_name.setText(nickname);
    }
}
