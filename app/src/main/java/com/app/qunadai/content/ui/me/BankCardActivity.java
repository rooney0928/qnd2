package com.app.qunadai.content.ui.me;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.bean.BankcardBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.BankcardContract;
import com.app.qunadai.content.presenter.BankcardPresenter;
import com.app.qunadai.content.ui.user.LoginActivity;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ProgressBarUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/14.
 */

public class BankCardActivity extends BaseActivity implements BankcardContract.View {
    private BankcardPresenter bankcardPresenter;

    @BindView(R.id.et_bank_name)
    EditText et_bank_name;
    @BindView(R.id.et_bank_bankcard)
    EditText et_bank_bankcard;
    @BindView(R.id.et_bank_idcard)
    EditText et_bank_idcard;
    @BindView(R.id.et_bank_phone)
    EditText et_bank_phone;

    @BindView(R.id.iv_bank_name_clear)
    ImageView iv_bank_name_clear;
    @BindView(R.id.iv_bank_bankcard_clear)
    ImageView iv_bank_bankcard_clear;
    @BindView(R.id.iv_bank_idcard_clear)
    ImageView iv_bank_idcard_clear;
    @BindView(R.id.iv_bank_phone_clear)
    ImageView iv_bank_phone_clear;

    @BindView(R.id.bt_submit)
    Button bt_submit;
    @BindView(R.id.verify_title_bar)
    View verify_title_bar;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("身份验证");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_bankcard, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        boolean hideTitle = getIntent().getBooleanExtra("titleHide", false);
        if (hideTitle) {
            verify_title_bar.setVisibility(View.GONE);
        }

        bankcardPresenter = new BankcardPresenter(this);
    }

    View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
//            LogU.t(v.getParent().ge);
            iv_bank_name_clear.setVisibility(View.GONE);
            iv_bank_bankcard_clear.setVisibility(View.GONE);
            iv_bank_idcard_clear.setVisibility(View.GONE);
            iv_bank_phone_clear.setVisibility(View.GONE);
            EditText et = (EditText) v;
            if (et.getText().toString().trim().length() > 0) {
                switch (v.getId()) {
                    case R.id.et_bank_name:
                        iv_bank_name_clear.setVisibility(View.VISIBLE);
                        break;
                    case R.id.et_bank_bankcard:
                        iv_bank_bankcard_clear.setVisibility(View.VISIBLE);
                        break;
                    case R.id.et_bank_idcard:
                        iv_bank_idcard_clear.setVisibility(View.VISIBLE);
                        break;
                    case R.id.et_bank_phone:
                        iv_bank_phone_clear.setVisibility(View.VISIBLE);
                        break;
                }
            }

        }
    };

    @Override
    public void initViewData() {

        et_bank_name.setOnFocusChangeListener(onFocusChangeListener);
        et_bank_bankcard.setOnFocusChangeListener(onFocusChangeListener);
        et_bank_idcard.setOnFocusChangeListener(onFocusChangeListener);
        et_bank_phone.setOnFocusChangeListener(onFocusChangeListener);
        iv_bank_name_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_bank_name.setText("");
            }
        });
        iv_bank_bankcard_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_bank_bankcard.setText("");
            }
        });
        iv_bank_idcard_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_bank_idcard.setText("");
            }
        });
        iv_bank_phone_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_bank_phone.setText("");
            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommUtil.isNull(et_bank_name) &&
                        !CommUtil.isNull(et_bank_bankcard) &&
                        !CommUtil.isNull(et_bank_idcard) &&
                        !CommUtil.isNull(et_bank_phone)
                        ) {
                    LogU.t("可以提交");
                    String token = PrefUtil.getString(BankCardActivity.this, PrefKey.TOKEN, "");
                    bankcardPresenter.setBankcard(token, CommUtil.getText(et_bank_name), CommUtil.getText(et_bank_bankcard),
                            CommUtil.getText(et_bank_idcard), CommUtil.getText(et_bank_phone));
                } else {
                    ToastUtil.showToast(BankCardActivity.this, "信息不完整");
                }

//                EventBus.getDefault().postSticky(new EventClose());
            }
        });

        bankcardPresenter.requestBankcard(PrefUtil.getString(this, PrefKey.TOKEN, ""));

    }

    @Override
    public void getBankcard(BankcardBean bean) {
        BankcardBean.ContentBean.RequirementBean reqBean = bean.getContent().getRequirement();
        if (reqBean != null) {
            et_bank_name.setText(reqBean.getName());
            et_bank_bankcard.setText(reqBean.getBankCardNumber());
            et_bank_idcard.setText(reqBean.getIdNumber());
            et_bank_phone.setText(reqBean.getMobileNumber());
        }

    }

    @Override
    public void getBankcardFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void setBankcard(BankcardBean bean) {
        EventBus.getDefault().post(new EventClose("info"));
        ToastUtil.showToastLong(this, "验证成功");
        finish();
    }

    @Override
    public void setBankcardFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        ProgressBarUtil.showLoadDialog(this);
    }


    @Override
    public void requestEnd() {
        ProgressBarUtil.hideLoadDialogDelay(this);
    }
    @Override
    public void tokenFail() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
    }
}
