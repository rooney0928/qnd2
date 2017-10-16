package com.app.qunadai.content.ui.user.frag;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.bean.v5.SmsBean;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Sign1Contract;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.content.presenter.v5.Sign1Presenter;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;


import butterknife.BindView;

import static com.app.qunadai.MyApp.context;

/**
 * Created by wayne on 2017/9/11.
 */

public class Step1PhoneFragment extends BaseFragment implements Sign1Contract.View, View.OnClickListener, FragmentBackPressed {
    private static final int TYPE_LOGIN_SMS = 10;
    private static final int TYPE_LOGIN_PWD = 20;
    private static final int TYPE_FORGET = 30;

    private static final int CHECK_REG = 12;
    private static final int CHECK_SMS = 14;
    private static final int CHECK_FORGET = 16;

    private Sign1Presenter sign1Presenter;

    @BindView(R.id.iv_login_back)
    ImageView iv_login_back;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.cb_phone_right)
    CheckBox cb_phone_right;
    @BindView(R.id.cb_pwd_hide)
    CheckBox cb_pwd_hide;

    @BindView(R.id.tv_code_signin)
    TextView tv_code_signin;
    @BindView(R.id.tv_forget_pwd)
    TextView tv_forget_pwd;

    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.ll_pwd)
    LinearLayout ll_pwd;

    int checkType;

    public static Step1PhoneFragment getInstance() {
        Step1PhoneFragment step1PhoneFragment = new Step1PhoneFragment();
        Bundle bundle = new Bundle();
        step1PhoneFragment.setArguments(bundle);
        return step1PhoneFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_sign_step1, null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        sign1Presenter = new Sign1Presenter(this);
        tv_submit.setOnClickListener(this);
        tv_forget_pwd.setOnClickListener(this);
        tv_code_signin.setOnClickListener(this);
        iv_login_back.setOnClickListener(this);
        cb_pwd_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int show = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                int hide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                et_pwd.setInputType(isChecked ? show : hide);
            }
        });


        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (CommUtil.getText(et_phone).length() < 11) {
                    ll_pwd.setVisibility(View.GONE);
                }
                cb_phone_right.setChecked(CheckUtil.isMobile(CommUtil.getText(et_phone)));
                tv_submit.setEnabled(CheckUtil.isMobile(CommUtil.getText(et_phone)));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tv_submit.setEnabled(CheckUtil.isMobile(CommUtil.getText(et_phone)));

    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {

        EventBus.getDefault().post(new EventProgress(true, "sign"));
    }

    @Override
    public void requestEnd() {
        EventBus.getDefault().post(new EventProgress(false, "sign"));

    }


    @Override
    public void checkPhone(BaseBean<IsExist> bean) {
        if (bean.getContent().isResult()) {
            //账号存在
            LogU.t("exist");

            switch (checkType) {
                case CHECK_REG:
                    ll_pwd.setVisibility(View.VISIBLE);
                    break;
                case CHECK_SMS:
                    sign1Presenter.sendLoginSms(CommUtil.getText(et_phone));

                    break;
                case CHECK_FORGET:
                    sign1Presenter.sendForgetSms(CommUtil.getText(et_phone));

                    break;
            }
        } else {
            //账号不存在，发送短信
            LogU.t("not exist");
            switch (checkType) {
                case CHECK_REG:
                    sign1Presenter.sendRegSms(CommUtil.getText(et_phone));
                    break;
                default:
                    ToastUtil.showToast(getActivity(), "该账号不存在");
                    break;
            }
        }
    }

    @Override
    public void checkPhoneFail(String error) {
        ToastUtil.showToast(getActivity(), error);
    }

    @Override
    public void getRegisterSms(BaseBean<SmsBean> msg) {
        PrefUtil.putString(getActivity(), PrefKey.SMS_TYPE, "reg");
        PrefUtil.putString(getActivity(), PrefKey.TEMP_PHONE, CommUtil.getText(et_phone));
        PrefUtil.putString(getActivity(), PrefKey.TEMP_SHA_CODE, msg.getContent().getVc());

        ToastUtil.showToast(getActivity(), "短信已发送");
        EventBus.getDefault().post(new EventTurn(1, "sign"));

    }

    @Override
    public void getRegisterSmsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void getForgetSms(BaseBean<SmsBean> bean) {
        PrefUtil.putString(getActivity(), PrefKey.SMS_TYPE, "forget");
        PrefUtil.putString(getActivity(), PrefKey.TEMP_PHONE, CommUtil.getText(et_phone));
        PrefUtil.putString(getActivity(), PrefKey.TEMP_SHA_CODE, bean.getContent().getVc());

        ToastUtil.showToast(getActivity(), "短信已发送");

        EventBus.getDefault().post(new EventTurn(1, "sign"));
    }

    @Override
    public void getForgetSmsFail(String error) {

    }

    @Override
    public void getLoginSms(BaseBean<SmsBean> bean) {
        PrefUtil.putString(getActivity(), PrefKey.SMS_TYPE, "login");
        PrefUtil.putString(getActivity(), PrefKey.TEMP_PHONE, CommUtil.getText(et_phone));
        PrefUtil.putString(getActivity(), PrefKey.TEMP_SHA_CODE, bean.getContent().getVc());


        ToastUtil.showToast(getActivity(), "短信已发送");

        EventBus.getDefault().post(new EventTurn(1, "sign"));
    }

    @Override
    public void getLoginSmsFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void loginDone(BaseBean<Token> token) {
        CommUtil.tcEvent(getActivity(),"password login","完成密码登录");
        PrefUtil.putString(getActivity(), PrefKey.TOKEN, token.getContent().getAccess_token());
        PrefUtil.putString(getActivity(), PrefKey.PHONE, CommUtil.getText(et_phone));

        ToastUtil.showToast(getActivity(), "恭喜您！登录成功！");
        getActivity().finish();
    }

    @Override
    public void loginFail(String error) {

        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_back:
                getActivity().finish();
                break;
            case R.id.tv_submit:
//                ll_pwd.setVisibility(ll_pwd.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (!NetworkUtil.checkNetwork(getActivity())) {
                    return;
                }

                if (ll_pwd.getVisibility() == View.GONE) {
                    //无密码模式
                    if (CommUtil.getText(et_phone).length() < 11 || !CheckUtil.isMobile(CommUtil.getText(et_phone))) {
                        ToastUtil.showToast(getActivity(), "手机号格式不正确");
                        return;
                    }

                    checkType = CHECK_REG;
                    sign1Presenter.checkPhone(CommUtil.getText(et_phone));
                } else {
                    //有密码模式,去登录
                    if (CommUtil.isNull(et_phone) || CommUtil.isNull(et_pwd)) {
                        ToastUtil.showToast(getActivity(), "用户名或密码为空");
                        return;
                    }
                    if (CommUtil.getText(et_phone).length() < 11 || !CheckUtil.isMobile(CommUtil.getText(et_phone))) {
                        ToastUtil.showToast(getActivity(), "手机号格式不正确");
                        return;
                    }

                    String phone = et_phone.getText().toString().trim();
                    String pwd = et_pwd.getText().toString().trim();
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    String imei = tm.getDeviceId();
                    sign1Presenter.loginByPwd(phone, CommUtil.shaEncrypt(pwd), imei);

                }


                break;
            case R.id.tv_code_signin:
                if (!NetworkUtil.checkNetwork(getActivity())) {
                    return;
                }

                if (CommUtil.isNull(et_phone) || CommUtil.getText(et_phone).length() != 11) {
                    ToastUtil.showToast(getActivity(), "手机号格式不正确");
                    return;
                }

//                sign1Presenter.sendLoginSms(CommUtil.getText(et_phone));
                checkType = CHECK_SMS;
                sign1Presenter.checkPhone(CommUtil.getText(et_phone));

                break;
            case R.id.tv_forget_pwd:
                if (!NetworkUtil.checkNetwork(getActivity())) {
                    return;
                }

                if (CommUtil.isNull(et_phone) || CommUtil.getText(et_phone).length() != 11) {
                    ToastUtil.showToast(getActivity(), "手机号格式不正确");
                    return;
                }
//                sign1Presenter.sendForgetSms(CommUtil.getText(et_phone));

                checkType = CHECK_FORGET;
                sign1Presenter.checkPhone(CommUtil.getText(et_phone));

                break;
        }
    }

    @Override
    public void onBackPressed() {
        getActivity().finish();
    }
}
