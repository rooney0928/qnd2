package com.app.qunadai.content.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.view.LoanProductSelectPW;
import com.app.qunadai.utils.LogU;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static com.app.qunadai.content.view.LoanProductSelectPW.TYPE_LOAN_MONEY;
import static com.app.qunadai.content.view.LoanProductSelectPW.TYPE_LOAN_TERM;
import static com.app.qunadai.content.view.LoanProductSelectPW.TYPE_LOAN_PROFESSIONAL;

/**
 * Created by wayne on 2017/5/12.
 */

public class ProductsActivity extends BaseActivity {

    @BindView(R.id.ll_pro)
    LinearLayout ll_pro;
    @BindView(R.id.ll_amount)
    LinearLayout ll_amount;
    @BindView(R.id.ll_term)
    LinearLayout ll_term;

    @BindView(R.id.tv_pro)
    TextView tv_pro;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    @BindView(R.id.tv_term)
    TextView tv_term;

    @BindView(R.id.iv_pro)
    ImageView iv_pro;
    @BindView(R.id.iv_amount)
    ImageView iv_amount;
    @BindView(R.id.iv_term)
    ImageView iv_term;

    @BindView(R.id.ll_filter)
    LinearLayout ll_filter;

    private LoanProductSelectPW proPW;
    private LoanProductSelectPW amountPW;
    private LoanProductSelectPW termPW;

    String tagName = "";
    String amount = "";
    String term = "";
    int page;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("贷款");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_products, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initViewData() {
        ll_pro.setOnClickListener(this);
        ll_amount.setOnClickListener(this);
        ll_term.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        super.onClick(v);

        switch (v.getId()) {
            case R.id.ll_pro:
                if (proPW == null) {
                    List list = Arrays.asList(getResources().getStringArray(R.array.filter_pro));
                    proPW = new LoanProductSelectPW(this, list, TYPE_LOAN_PROFESSIONAL);
                    proPW.setOnItemTextClickListener(new LoanProductSelectPW.ItemTextClickListener() {
                        @Override
                        public void onItemClick(String text, TextView textView, int type) {
                            tv_pro.setText(text);
                            tv_pro.setSelected(true);
                            iv_pro.setSelected(true);

                            tagName = text;
                            if(text.equals("职业身份")){
                                tagName = "";
                                tv_pro.setSelected(false);
                                iv_pro.setSelected(false);
                            }
                            LogU.t("tagName是：" + tagName + "，amount是：" + amount + "，term是：" + term);
                            page = 0;
                        }
                    });
                    proPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            resetTextViewStatus(tv_pro, iv_pro, "职业身份");
                            resetTextViewStatus(tv_amount, iv_amount, "贷款额度");
                            resetTextViewStatus(tv_term, iv_term, "贷款期限");
                        }
                    });
                }
                proPW.showAsDropDown(ll_filter);
                break;
            case R.id.ll_amount:
                changeSelectedStyle(tv_amount, iv_amount, true);

                if (amountPW == null) {
                    List list = Arrays.asList(getResources().getStringArray(R.array.filter_amount));
                    amountPW = new LoanProductSelectPW(this, list, TYPE_LOAN_MONEY);
                    amountPW.setOnItemTextClickListener(new LoanProductSelectPW.ItemTextClickListener() {
                        @Override
                        public void onItemClick(String text, TextView textView, int type) {
                            tv_amount.setText(text);
                            tv_amount.setSelected(true);
                            iv_amount.setSelected(true);
                            if (text.equals("贷款额度")){
                                amount = "";
                                tv_amount.setSelected(false);
                                iv_amount.setSelected(false);
                            }else{
                                amount=text.substring(0,text.indexOf("元"));
                            }
                            LogU.t("tagName是：" + tagName + "，amount是：" + amount + "，term是：" + term);
                            page = 0;
                            //调用接口
                        }
                    });
                    amountPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            resetTextViewStatus(tv_pro, iv_pro, "职业身份");
                            resetTextViewStatus(tv_amount, iv_amount, "贷款额度");
                            resetTextViewStatus(tv_term, iv_term, "贷款期限");
                        }
                    });
                }

                amountPW.showAsDropDown(ll_filter);
                break;
            case R.id.ll_term:
                changeSelectedStyle(tv_term, iv_term, true);

                if (termPW == null) {
                    List list = Arrays.asList(getResources().getStringArray(R.array.filter_term));
                    termPW = new LoanProductSelectPW(this, list, TYPE_LOAN_TERM);
                    termPW.setOnItemTextClickListener(new LoanProductSelectPW.ItemTextClickListener() {
                        @Override
                        public void onItemClick(String text, TextView textView, int type) {
                            tv_term.setText(text);
                            tv_term.setSelected(true);
                            iv_term.setSelected(true);

                            term=text;
                            if(text.equals("贷款期限")){
                                term = "";
                                tv_term.setSelected(false);
                                iv_term.setSelected(false);
                            }
                            LogU.t("tagName是：" + tagName+"，amount是：" + amount+"，term是：" + term);
                            page=0;
                        }
                    });
                    termPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            resetTextViewStatus(tv_pro, iv_pro, "职业身份");
                            resetTextViewStatus(tv_amount, iv_amount, "贷款额度");
                            resetTextViewStatus(tv_term, iv_term, "贷款期限");
                        }
                    });
                }

                termPW.showAsDropDown(ll_filter);
                break;

        }
    }

    public void resetTextViewStatus(TextView textView, ImageView imageView, String text) {
        String currentText = textView.getText().toString().trim();
        if (currentText.equals(text)) {
            textView.setSelected(false);
            imageView.setSelected(false);
        }
    }

    /**
     * 更改选项的样式
     */
    public void changeSelectedStyle(TextView textView, ImageView imageView, boolean selected) {
        if (textView.isSelected()) {
            return;
        }
        textView.setSelected(selected);
        imageView.setSelected(selected);
    }
}
