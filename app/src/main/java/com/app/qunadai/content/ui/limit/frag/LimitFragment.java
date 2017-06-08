package com.app.qunadai.content.ui.limit.frag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.MyApp;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.LimitContract;
import com.app.qunadai.content.presenter.LimitPresenter;
import com.app.qunadai.content.ui.MainActivity;
import com.app.qunadai.content.ui.home.RecommendActivity;
import com.app.qunadai.content.ui.me.BankCardActivity;
import com.app.qunadai.content.ui.me.PersonInfoActivity;
import com.app.qunadai.content.view.AuthView;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ReqKey;
import com.app.qunadai.utils.ToastUtil;
import com.moxie.client.model.MxParam;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/10.
 */

public class LimitFragment extends BaseFragment implements LimitContract.View, View.OnClickListener {

    private LimitPresenter limitPresenter;

    @BindView(R.id.rl_limit_get)
    RelativeLayout rl_limit_get;
    @BindView(R.id.rl_limit_borrow)
    RelativeLayout rl_limit_borrow;
    @BindView(R.id.ll_borrow_money)
    LinearLayout ll_borrow_money;

    @BindView(R.id.av_bankcard)
    AuthView av_bankcard;
    @BindView(R.id.av_realinfo)
    AuthView av_realinfo;
    @BindView(R.id.av_ebank)
    AuthView av_ebank;
    @BindView(R.id.av_carrier)
    AuthView av_carrier;
    @BindView(R.id.av_alipay)
    AuthView av_alipay;
    @BindView(R.id.av_email)
    AuthView av_email;
    @BindView(R.id.av_fund)
    AuthView av_fund;
    @BindView(R.id.av_taobao)
    AuthView av_taobao;
    @BindView(R.id.av_credit)
    AuthView av_credit;

    @BindView(R.id.tv_limit_money)
    TextView tv_limit_money;
    @BindView(R.id.tv_limit_total)
    TextView tv_limit_total;

    @BindView(R.id.bt_limit_get_limit)
    Button bt_limit_get_limit;
    @BindView(R.id.bt_limit_raise)
    Button bt_limit_raise;
    @BindView(R.id.bt_limit_borrow)
    Button bt_limit_borrow;

    private PersonBean localPersonBean;

    public static LimitFragment getInstance() {
        LimitFragment limitFragment = new LimitFragment();
        Bundle bundle = new Bundle();
        limitFragment.setArguments(bundle);
        return limitFragment;
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_limit, null);
        return root;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        limitPresenter = new LimitPresenter(this);
        limitPresenter.requestPersonValue(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));

        av_bankcard.setOnClickListener(this);
        av_realinfo.setOnClickListener(this);
        av_ebank.setOnClickListener(this);
        av_carrier.setOnClickListener(this);
        av_alipay.setOnClickListener(this);
        av_email.setOnClickListener(this);
        av_fund.setOnClickListener(this);
        av_taobao.setOnClickListener(this);
        av_credit.setOnClickListener(this);


        bt_limit_get_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入身份认证
                Intent intentInfo = new Intent(getActivity(), PersonInfoActivity.class);
                startActivityForResult(intentInfo,ReqKey.REQ_BANK_INFO);
            }
        });
        bt_limit_raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入身份认证
                Intent intentInfo = new Intent(getActivity(), PersonInfoActivity.class);
                startActivityForResult(intentInfo,ReqKey.REQ_BANK_INFO);
            }
        });
        bt_limit_borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入贷款推荐
                Intent intentReco = new Intent(getActivity(), RecommendActivity.class);
                startActivity(intentReco);
            }
        });
    }

    @Override
    public void getPersonValue(PersonBean bean) {
        localPersonBean = bean;
        rl_limit_get.setVisibility(View.GONE);
        rl_limit_borrow.setVisibility(View.GONE);
        ll_borrow_money.setVisibility(View.GONE);
        tv_limit_money.setText(bean.getContent().getPersonalValue().getValuation() + "");
        tv_limit_total.setText(bean.getContent().getPersonalValue().getBalance() + "");


        if (0 != bean.getContent().getPersonalValue().getValuation()) {
            rl_limit_borrow.setVisibility(View.VISIBLE);
            ll_borrow_money.setVisibility(View.VISIBLE);
        } else {
            rl_limit_get.setVisibility(View.VISIBLE);
        }

        //银行卡验证
        setBindStatus(av_bankcard, bean.getContent().getPersonalValue().getBankStatus());


        //真实信息
        setBindStatus(av_realinfo, bean.getContent().getPersonalValue().getRealInfoStatus());
        //网银
        setBindStatus(av_ebank, bean.getContent().getPersonalValue().getEbankStatus());
        //运营商
        setBindStatus(av_carrier, bean.getContent().getPersonalValue().getOperatorStatus());
        //支付宝
        setBindStatus(av_alipay, bean.getContent().getPersonalValue().getAlipayStatus());
        //邮箱
        setBindStatus(av_email, bean.getContent().getPersonalValue().getEmailStatus());
        //公积金
        setBindStatus(av_fund, bean.getContent().getPersonalValue().getFundStatus());
        //淘宝
        setBindStatus(av_taobao, bean.getContent().getPersonalValue().getTaobaoStatus());
        //征信
        setBindStatus(av_credit, bean.getContent().getPersonalValue().getZxStatus());

        switch (bean.getContent().getPersonalValue().getBankStatus()){
            case "SUCCESS":
            case "HRISK":
            case "MRISK":
            case "LRISK":
            case "PROCESSING":
                PrefUtil.putBoolean(getActivity(),PrefKey.BANK_CHECKED,true);
                break;
            default:
                PrefUtil.putBoolean(getActivity(),PrefKey.BANK_CHECKED,false);
                break;
        }
    }

    /**
     * 设置认证状态
     *
     * @param status
     */
    private void setBindStatus(AuthView av, String status) {
        /** 新建,处理中,验证成功,验证失败,处理错误,用户被封禁,高风险,中风险,低风险 */
        //CREATED, PROCESSING, SUCCESS, FAILURE, ERROR, BANNED, HRISK, MRISK, LRISK
        switch (status) {
            case "SUCCESS":
            case "HRISK":
            case "MRISK":
            case "LRISK":
                av.setAuthStatus(AuthView.AUTH_YES);
                break;
            case "PROCESSING":
                av.setAuthStatus(AuthView.AUTH_ING);
                break;
            default:
                av.setAuthStatus(AuthView.AUTH_NO);
                break;
        }

    }

    @Override
    public void getPersonValueFail(String error) {
        ToastUtil.showToast(getActivity(), error);
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
    public void refreshMsg(){
        if(NetworkUtil.checkNetwork(getActivity())){
            if(getActivity()!=null){
                limitPresenter.requestPersonValue(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v instanceof AuthView) {
            Bundle bundle = new Bundle();
            MainActivity mainActivity = (MainActivity) getActivity();

            MxParam mxParam = new MxParam();
            mxParam.setUserId(PrefUtil.getString(getActivity(), PrefKey.PHONE, ""));
            mxParam.setApiKey(MyApp.MX_KEY);
            mxParam.setThemeColor("#31B7C1");//主题色（非必传）
//            mxParam.setAgreementUrl(mainActivity.getSharedPreferValue("agreementUrl"));//自定义协议地址（非必传）
            mxParam.setAgreementEntryText("同意数据获取协议");    //自定义协议相关说明（非必传）
//            mxParam.setCacheDisable(MxParam.PARAM_COMMON_YES);//不使用缓存（非必传）
//            mxParam.setLoadingViewText("验证过程中不会浪费您任何流量\n请稍等片刻");  //设置导入过程中的自定义提示文案，为居中显示
//            mxParam.setQuitDisable(true); //设置导入过程中，触发返回键或者点击actionbar的返回按钮的时候，不执行魔蝎的默认行为


//            MxParam.PARAM_FUNCTION_MAIL（或MxParam.PARAM_FUNCTION_EMAIL）：邮箱导入
//            MxParam.PARAM_FUNCTION_ONLINEBANK：网银导入
//            MxParam.PARAM_FUNCTION_CARRIER：运营商
//            MxParam.PARAM_FUNCTION_QQ：QQ验证
//            MxParam.PARAM_FUNCTION_ALIPAY：支付宝
//            MxParam.PARAM_FUNCTION_TAOBAO：淘宝
//            MxParam.PARAM_FUNCTION_JINGDONG：京东
//            MxParam.PARAM_FUNCTION_CHSI：学信网
//            MxParam.PARAM_FUNCTION_FUND：公积金
//            MxParam.PARAM_FUNCTION_ZHENGXIN：征信
//            MxParam.PARAM_FUNCTION_INSURANCE：保险
//            MxParam.PARAM_FUNCTION_MAIMAI：脉脉
//            MxParam.PARAM_FUNCTION_ZHIXINGCOURT：法院被执行人查询
//            MxParam.PARAM_FUNCTION_SHIXINCOURT：法院失信人查询
//            MxParam.PARAM_FUNCTION_LINKEDIN：领英
//            MxParam.PARAM_FUNCTION_TAX：个税
//            MxParam.PARAM_FUNCTION_SECURITY：社保

            switch (v.getId()) {

                case R.id.av_ebank:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_ONLINEBANK);
                    break;
                case R.id.av_carrier:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_CARRIER);
                    break;
                case R.id.av_alipay:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_ALIPAY);
                    break;
                case R.id.av_email:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_EMAIL);
                    break;
                case R.id.av_fund:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_FUND);
                    break;
                case R.id.av_taobao:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_TAOBAO);
                    break;
                case R.id.av_credit:
                    mxParam.setFunction(MxParam.PARAM_FUNCTION_ZHENGXIN);
                    break;
            }
            if (v.getId() == R.id.av_bankcard || v.getId() == R.id.av_realinfo) {
                switch (v.getId()) {
                    case R.id.av_bankcard:
                        //进入银行卡认证
                        if (av_bankcard.getStatus() == AuthView.AUTH_YES) {
//                            av_bankcard.setOnClickListener(nullClick);

                        } else if (av_bankcard.getStatus() == AuthView.AUTH_ING) {
                            av_bankcard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtil.showToast(getActivity(), "正在认证中");
                                }
                            });
                        } else {
                            av_bankcard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intentBank = new Intent(getActivity(), BankCardActivity.class);
                                    intentBank.putExtra("titleHide", true);
                                    startActivityForResult(intentBank,ReqKey.REQ_BANK_INFO);
                                }
                            });
                        }

                        break;
                    case R.id.av_realinfo:
                        //进入个人信息认证
                        Intent intentInfo = new Intent(getActivity(), PersonInfoActivity.class);
                        intentInfo.putExtra("titleHide", true);
                        intentInfo.putExtra("isFromDetail", true);
                        startActivityForResult(intentInfo,ReqKey.REQ_BANK_INFO);
                        break;
                }

            } else {
                bundle.putParcelable("param", mxParam);
                Intent intent = new Intent(getActivity(), com.moxie.client.MainActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, ReqKey.REQ_MOXIE);
            }


        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case ReqKey.REQ_BANK_INFO:
                    limitPresenter.requestPersonValue(PrefUtil.getString(getActivity(), PrefKey.TOKEN, ""));

                    break;
                case ReqKey.REQ_MOXIE:
                    Bundle b = data.getExtras();              //data为B中回传的Intent
                    String result = b.getString("result");    //result即为回传的值(JSON格式)

                    if (TextUtils.isEmpty(result)) {
                        ToastUtil.showToast(getActivity(), "没有进行导入操作!");
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            LogU.t("json0--" + jsonObject.toString());

                            int code = jsonObject.getInt("code");
                            String taskType = jsonObject.getString("taskType");
                            switch (code) {
                                case -1:
                                    ToastUtil.showToast(getActivity(), "没有进行导入操作!");

                                    break;
                                case -2:
                                    ToastUtil.showToast(getActivity(), "导入失败(服务问题)!");
                                    break;
                                case -3:
                                    ToastUtil.showToast(getActivity(), "导入失败(服务异常)!");
                                    break;
                                case -4:
//                                ToastUtil.showToast(getActivity(),"导入失败(");
                                    break;
                                case 0:
//                                ToastUtil.showToast(getActivity(),"导入失败!");
                                    break;
                                case 1:
                                    requestUpdateStates(taskType);
                                    ToastUtil.showToast(getActivity(), "导入成功!");
                                    break;
                                case 2:
                                    /**
                                     * 如果用户中途导入魔蝎SDK会出现这个情况，如需获取最终状态请轮询贵方后台接口
                                     * 魔蝎后台会向贵方后台推送Task通知和Bill通知
                                     * Task通知：登录成功/登录失败
                                     * Bill通知：账单通知
                                     */
                                    ToastUtil.showToast(getActivity(), "导入中");
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    break;
            }
        }



    }

    private void requestUpdateStates(String taskType) {
        String phone = PrefUtil.getString(getActivity(), PrefKey.PHONE, "");
        String token = PrefUtil.getString(getActivity(), PrefKey.TOKEN, "");
        limitPresenter.updateStatus(phone, taskType, token);
        limitPresenter.requestPersonValue(token);
    }
}
