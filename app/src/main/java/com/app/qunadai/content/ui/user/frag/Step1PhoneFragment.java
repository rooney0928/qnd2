package com.app.qunadai.content.ui.user.frag;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.IsExist;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Sign1Contract;
import com.app.qunadai.content.presenter.v5.Sign1Presenter;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

import static com.app.qunadai.MyApp.context;

/**
 * Created by wayne on 2017/9/11.
 */

public class Step1PhoneFragment extends BaseFragment implements Sign1Contract.View, View.OnClickListener {

    private Sign1Presenter sign1Presenter;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.cb_phone_right)
    CheckBox cb_phone_right;

    @BindView(R.id.tv_code_signin)
    TextView tv_code_signin;
    @BindView(R.id.tv_forget_password)
    TextView tv_forget_password;

    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.ll_pwd)
    LinearLayout ll_pwd;

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

        cb_phone_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    cb_phone_right.setChecked(true);
                    tv_code_signin.setEnabled(true);
                } else {
                    cb_phone_right.setChecked(false);
                    tv_code_signin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
            ll_pwd.setVisibility(View.VISIBLE);

        } else {
            //账号不存在
            LogU.t("not exist");
            EventBus.getDefault().post(new EventTurn(1, "sign"));

        }
    }

    @Override
    public void checkPhoneFail(String error) {
        ToastUtil.showToast(getActivity(), error);
    }

    @Override
    public void loginDone(Token token) {
        getActivity().finish();
    }

    @Override
    public void loginFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
//                ll_pwd.setVisibility(ll_pwd.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                if (ll_pwd.getVisibility() == View.GONE) {
                    //无密码模式
                    if (CommUtil.getText(et_phone).length() < 11) {
                        ToastUtil.showToast(getActivity(), "手机号码格式不正确");
                        return;
                    }
                    sign1Presenter.checkPhone(CommUtil.getText(et_phone));
                } else {
                    //有密码模式,去登录
                    if (CommUtil.isNull(et_phone) || CommUtil.isNull(et_pwd)) {
                        ToastUtil.showToast(getActivity(), "用户名或密码为空");
                        return;
                    }
                    String phone = et_phone.getText().toString().trim();
                    String pwd = et_pwd.getText().toString().trim();
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    String imei = tm.getDeviceId();
                    sign1Presenter.loginByPwd(phone, CommUtil.shaEncrypt(pwd), imei);

                }


                break;
        }
    }
}
