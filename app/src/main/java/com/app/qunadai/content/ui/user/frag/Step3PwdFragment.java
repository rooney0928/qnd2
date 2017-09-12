package com.app.qunadai.content.ui.user.frag;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.Token;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Sign3Contract;
import com.app.qunadai.content.inter.FragmentBackPressed;
import com.app.qunadai.content.presenter.v5.Sign3Presenter;
import com.app.qunadai.third.eventbus.EventProgress;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/11.
 */

public class Step3PwdFragment extends BaseFragment implements Sign3Contract.View, FragmentBackPressed, View.OnClickListener {


    private Sign3Presenter sign3Presenter;
    @BindView(R.id.cb_pwd_hide)
    CheckBox cb_pwd_hide;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    String phone;
    String smsCode;
    String smsType;

    public static Step3PwdFragment getInstance() {
        Step3PwdFragment step3PwdFragment = new Step3PwdFragment();
        Bundle bundle = new Bundle();
        step3PwdFragment.setArguments(bundle);
        return step3PwdFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_sign_step3, null);
        return root;
    }


    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        sign3Presenter = new Sign3Presenter(this);
        cb_pwd_hide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int show = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
                int hide = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
                et_pwd.setInputType(isChecked ? show : hide);
            }
        });
        tv_submit.setOnClickListener(this);
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

    public void setData() {
        phone = PrefUtil.getString(getActivity(), PrefKey.TEMP_PHONE, "");
        smsCode = PrefUtil.getString(getActivity(), PrefKey.TEMP_CODE, "");
        smsType = PrefUtil.getString(getActivity(), PrefKey.SMS_TYPE, "");

    }


    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new EventTurn(1, "sign"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                int len = CommUtil.getText(et_pwd).length();
                if (len > 16 || len < 6) {
                    ToastUtil.showToast(getActivity(), "密码长度不合适");
                    break;
                }
                if (smsType.equalsIgnoreCase("reg")) {
                    sign3Presenter.register(phone, smsCode, CommUtil.shaEncrypt(CommUtil.getText(et_pwd)));
                } else if (smsType.equalsIgnoreCase("forget")) {
                    sign3Presenter.reset(phone, smsCode, CommUtil.shaEncrypt(CommUtil.getText(et_pwd)));
                }
                break;
        }
    }

    @Override
    public void registerDone(BaseBean<Token> bean) {
        PrefUtil.putString(getActivity(), PrefKey.TOKEN, bean.getContent().getAccess_token());

        ToastUtil.showToast(getActivity(), "恭喜您！注册成功！");
        getActivity().finish();
    }

    @Override
    public void registerFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }

    @Override
    public void resetDone(BaseBean<Token> bean) {
        PrefUtil.putString(getActivity(), PrefKey.TOKEN, bean.getContent().getAccess_token());

        ToastUtil.showToast(getActivity(), "恭喜您！重置成功！");
        getActivity().finish();
    }

    @Override
    public void resetFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }
}
