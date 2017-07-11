package com.app.qunadai.content.ui.product;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.bean.ProductDetailBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.ProductDetailContract;
import com.app.qunadai.content.presenter.ProductDetailPresenter;
import com.app.qunadai.content.ui.me.BankCardActivity;
import com.app.qunadai.content.ui.me.PersonInfoActivity;
import com.app.qunadai.content.view.AuthView;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/14.
 */

public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.View {
    private ProductDetailPresenter productDetailPresenter;

    //头部
    @BindView(R.id.iv_detail_avatar)
    ImageView iv_detail_avatar;
    @BindView(R.id.tv_detail_name)
    TextView tv_detail_name;
    @BindView(R.id.tv_detail_ratesuc)
    TextView tv_detail_ratesuc;
    @BindView(R.id.tv_detail_count)
    TextView tv_detail_count;
    @BindView(R.id.tv_detail_desc)
    TextView tv_detail_desc;

    //金额，期限
    @BindView(R.id.tv_detail_amount)
    TextView tv_detail_amount;
    @BindView(R.id.et_detail_amount)
    EditText et_detail_amount;
    @BindView(R.id.tv_detail_period_limit)
    TextView tv_detail_period_limit;
    @BindView(R.id.tv_detail_term)
    TextView tv_detail_term;

    @BindView(R.id.ll_detail_acount)
    LinearLayout ll_detail_acount;
    @BindView(R.id.rl_detail_period)
    RelativeLayout rl_detail_period;

    //利率计算
    @BindView(R.id.ll_detail_repay_mon)
    LinearLayout ll_detail_repay_mon;
    @BindView(R.id.tv_detail_repay_mon)
    TextView tv_detail_repay_mon;
    @BindView(R.id.tv_detail_rate)
    TextView tv_detail_rate;
    @BindView(R.id.tv_detail_time)
    TextView tv_detail_time;

    //申请条件
    @BindView(R.id.tv_detail_condition)
    TextView tv_detail_condition;

    //需要材料
    @BindView(R.id.av_bankcard)
    AuthView av_bankcard;
    @BindView(R.id.av_realinfo)
    AuthView av_realinfo;

    @BindView(R.id.bt_submit)
    Button bt_submit;

    private ProductDetailBean productDetailBean;

    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitleText("借款详情");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_loan_detail, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        productDetailPresenter = new ProductDetailPresenter(this);
        String pid = getIntent().getStringExtra("pid");
        String token = PrefUtil.getString(this, PrefKey.TOKEN, "");


        if(NetworkUtil.checkNetwork(this)){
            productDetailPresenter.requestPersonValue(token);
            productDetailPresenter.requestProductDetail(pid);
        }
    }

    @Override
    public void initViewData() {
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                productDetailPresenter.applyOrder(PrefUtil.getString(ProductDetailActivity.this, PrefKey.TOKEN, ""),
//                        mAmount, mTime, mTimeType, mPid, "H5");
                if (av_bankcard.getStatus() != AuthView.AUTH_YES || av_realinfo.getStatus() != AuthView.AUTH_YES) {
                    ToastUtil.showToast(ProductDetailActivity.this, "请完善认证后提交");
                    return;
                }

                //在这里提交
                if (productDetailBean != null) {
                    String mAmount = CommUtil.getText(et_detail_amount);
                    String mTime = "" + CommUtil.str2int(CommUtil.getText(tv_detail_term));
                    String mTimeType = CommUtil.getLastChar(CommUtil.getText(tv_detail_term));
                    String mPid = productDetailBean.getContent().getProduct().getId();
                    productDetailPresenter.applyOrder(PrefUtil.getString(ProductDetailActivity.this, PrefKey.TOKEN, ""),
                            mAmount, mTime, mTimeType, mPid, "H5");

//                    Intent intent = new Intent(ProductDetailActivity.this, H5WebActivity.class);
//                    intent.putExtra("url", productDetailBean.getContent().getProduct().getUrl());
//                    intent.putExtra("title",productDetailBean.getContent().getProduct().getName());
//                    startActivity(intent);

                    Intent intent = new Intent(ProductDetailActivity.this, BrowserActivity.class);
                    intent.putExtra("url", productDetailBean.getContent().getProduct().getUrl());
                    intent.putExtra("title",productDetailBean.getContent().getProduct().getName());
                    startActivity(intent);
                }
            }
        });
    }

    View.OnClickListener nullClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public void getProductDetail(ProductDetailBean pdbean) {
        productDetailBean = pdbean;
        ProductDetailBean.ContentBean.ProductBean bean = pdbean.getContent().getProduct();
        //头部
        String imgUrl = RxHttp.ROOT + "attachments/" + bean.getIcon();
        ImgUtil.loadImgAvatar(this, imgUrl, iv_detail_avatar);
        tv_detail_name.setText(bean.getName());
        tv_detail_ratesuc.setText("成功率：" + bean.getSucRate());
        tv_detail_desc.setText(bean.getDescribe());

        tv_detail_rate.setText(bean.getRate());
        tv_detail_time.setText(bean.getLoanTime());
        //条件
        tv_detail_condition.setText(bean.getApplicationConditions());

        //计算部分
        LogU.t(bean.getTerm());
//        bean.setAmount("8,9元");
//        bean.setAmount("8-90元");
        if (-1 != bean.getAmount().indexOf("-")) {
            //金额
            final String[] amounts = bean.getAmount().split("-");
            tv_detail_amount.setText("借款金额(" + bean.getAmount() + ")");
            et_detail_amount.setEnabled(true);
            String maxMoney = "" + CommUtil.str2int(amounts[amounts.length - 1]);
            et_detail_amount.setText(maxMoney);
            //设置最大长度
            et_detail_amount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxMoney.length())});
            et_detail_amount.setSelection(et_detail_amount.length());
            et_detail_amount.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!"".equals(charSequence.toString())) {
                        int money = Integer.parseInt(et_detail_amount.getText().toString());
                        et_detail_amount.removeTextChangedListener(this);
                        et_detail_amount.setText("" + money);
                        et_detail_amount.addTextChangedListener(this);
                        Editable etext = et_detail_amount.getText();
                        Selection.setSelection(etext, etext.length());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    countPerMonthRepay();

                }
            });
        } else if (-1 != bean.getAmount().indexOf(",")) {
            et_detail_amount.setEnabled(false);
            tv_detail_amount.setText("借款金额(" + bean.getAmount() + ")");

            String[] moneyLimits = bean.getAmount().split(",");
            String maxMoney = "" + CommUtil.str2int(moneyLimits[moneyLimits.length - 1]);
            et_detail_amount.setText(maxMoney);
            et_detail_amount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxMoney.length())});

            final List<String> moneyArr = new ArrayList<>();
            for (String m : moneyLimits) {
                moneyArr.add(CommUtil.str2int(m) + "元");
            }
            int size = moneyArr.size();
            final String[] arr = (String[]) moneyArr.toArray(new String[size]);


            ll_detail_acount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showMoneyDialog(arr);
                }
            });
            et_detail_amount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMoneyDialog(arr);
                }
            });
        }
        tv_detail_period_limit.setText("借款期限（" + bean.getTerm() + ")");
        if (bean.getTerm().indexOf("-") != -1) {
            //期限
            String[] timeLimits = bean.getTerm().split("-");
            tv_detail_term.setText(timeLimits[timeLimits.length - 1]);

            int termMin = CommUtil.str2int(timeLimits[0]);
            int termMax = CommUtil.str2int(timeLimits[1]);

            final List<String> termArr = new ArrayList<>();
            final String unit = bean.getTerm().substring(bean.getTerm().length() - 1, bean.getTerm().length());
            for (int i = termMin; i <= termMax; i++) {
                termArr.add(i + unit);
            }
            int size = termArr.size();
            final String[] arr = (String[]) termArr.toArray(new String[size]);
            rl_detail_period.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTermDialog(arr);
                }
            });
        } else if (bean.getTerm().indexOf(",") != -1) {
            String[] timeLimits = bean.getTerm().split(",");
            tv_detail_term.setText(timeLimits[timeLimits.length - 1]);
            final String unit = bean.getTerm().substring(bean.getTerm().length() - 1, bean.getTerm().length());
            final List<String> termArr = new ArrayList<>();
            for (String term : timeLimits) {
                termArr.add(CommUtil.str2int(term) + "" + unit);
            }
            int size = termArr.size();
            final String[] arr = (String[]) termArr.toArray(new String[size]);
            rl_detail_period.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTermDialog(arr);
                }
            });
        }else{
            tv_detail_term.setText(bean.getTerm());
        }

        //载入完先计算一遍
        countPerMonthRepay();

    }

    /**
     * 计算每月还款额
     * 月还款额=（借款金额/借款期限）+（借款金额*月利率）
     */
    public void countPerMonthRepay() {
        if (productDetailBean == null) {
            return;
        }
        ProductDetailBean.ContentBean.ProductBean bean = productDetailBean.getContent().getProduct();


        String amount = et_detail_amount.getText().toString().trim();
        if ("".equals(amount)) {
            return;
        }
        String perMonStr = "";
        //最低月利率
        String rate = bean.getRate().split("%")[0];
        double rate2 = Double.parseDouble(rate);

        //天数，月数
        String date = tv_detail_term.getText().toString().trim();


        BigDecimal bigRate = new BigDecimal(rate2);
        BigDecimal bigAmount = new BigDecimal(amount);
        BigDecimal bigDate = new BigDecimal(CommUtil.str2int(date));
        BigDecimal perMonth = null;
        if (bean.getTerm().indexOf("天") != -1) {
//            perMonth = perMonth.multiply(new BigDecimal(CommUtil.));
            if (CommUtil.str2int(date) < 30) {
                //小于30天则隐藏
                ll_detail_repay_mon.setVisibility(View.GONE);
                return;
            } else {
                ll_detail_repay_mon.setVisibility(View.VISIBLE);
            }
            //以天做单位则将天数做转换后计算
            perMonth = bigAmount.divide(new BigDecimal(CommUtil.day2month(bigDate)), 2, BigDecimal.ROUND_DOWN);
        } else {
            ll_detail_repay_mon.setVisibility(View.VISIBLE);
            //月为单位泽直接除以月份
            perMonth = bigAmount.divide(bigDate, 2, BigDecimal.ROUND_DOWN);
        }

        //利息=利率*成本
        BigDecimal permonRate = bigAmount.multiply(bigRate).divide(new BigDecimal(100), 2, BigDecimal.ROUND_CEILING);


        BigDecimal bigResult = perMonth.add(permonRate);
        String strResult = bigResult.setScale(2, BigDecimal.ROUND_CEILING).toString();
        tv_detail_repay_mon.setText(strResult);

    }

    @Override
    public void getProductDetailFail(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void getPersonValue(PersonBean bean) {
        //银行卡验证
        setBindStatus(av_bankcard, bean.getContent().getPersonalValue().getBankStatus());
        //真实信息
        setBindStatus(av_realinfo, bean.getContent().getPersonalValue().getRealInfoStatus());
//        if (av_bankcard.getStatus() != AuthView.AUTH_YES) {
//            av_bankcard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(ProductDetailActivity.this, BankCardActivity.class);
//                    intent.putExtra("titleHide", true);
//                    startActivity(intent);
//                }
//            });
//        } else {
//            av_bankcard.setOnClickListener(nullClick);
//        }

        //银行卡点击
//        if(av_bankcard.getStatus()==AuthView.AUTH_YES){
//            av_bankcard.setOnClickListener(nullClick);
//        }else if(av_bankcard.getStatus()==AuthView.AUTH_ING){
//            av_bankcard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ToastUtil.showToast(ProductDetailActivity.this,"正在认证中");
//                }
//            });
//        }else{
//            av_bankcard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(ProductDetailActivity.this, BankCardActivity.class);
//                    intent.putExtra("titleHide", true);
//                    startActivity(intent);
//                }
//            });
//        }

        av_bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, BankCardActivity.class);
                intent.putExtra("titleHide", true);
                startActivity(intent);
            }
        });

        av_realinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, PersonInfoActivity.class);
                intent.putExtra("titleHide", true);
                intent.putExtra("isFromDetail", true);
                startActivity(intent);
            }
        });
//        if (av_realinfo.getStatus() != AuthView.AUTH_YES) {
//
//        } else {
//            av_realinfo.setOnClickListener(nullClick);
//        }
    }

    @Override
    public void getPersonValueFail(String error) {
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
//        ProgressBarUtil.showLoadDialog(this);
        showLoading();
    }


    @Override
    public void requestEnd() {
//        ProgressBarUtil.hideLoadDialogDelay(this);
        hideLoading();
    }

    /**
     * @param items
     */
    private void showMoneyDialog(final String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("金额选择");
        //定义列表中的选项
        //设置列表选项
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                et_detail_amount.setText("" + CommUtil.str2int(items[i]));
                countPerMonthRepay();
            }
        });
        // 取消选择
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    /**
     * 贷款期限
     *
     * @param items
     */
    private void showTermDialog(final String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("期限选择");
        //定义列表中的选项
        //设置列表选项
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv_detail_term.setText(items[i]);
                countPerMonthRepay();
            }
        });
        // 取消选择
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
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
}
