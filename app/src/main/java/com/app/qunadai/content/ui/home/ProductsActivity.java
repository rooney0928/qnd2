package com.app.qunadai.content.ui.home;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.LoanDetail;
import com.app.qunadai.bean.ProductsBean;
import com.app.qunadai.content.adapter.LoanAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.ProductsContract;
import com.app.qunadai.content.presenter.ProductsPresenter;
import com.app.qunadai.content.view.LoanProductSelectPW;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

import static com.app.qunadai.content.view.LoanProductSelectPW.TYPE_LOAN_MONEY;
import static com.app.qunadai.content.view.LoanProductSelectPW.TYPE_LOAN_TERM;
import static com.app.qunadai.content.view.LoanProductSelectPW.TYPE_LOAN_PROFESSIONAL;

/**
 * Created by wayne on 2017/5/12.
 */

public class ProductsActivity extends BaseActivity implements ProductsContract.View {
    private static final int PAGE_SIZE = 10;
    int page = 0;


    private ProductsPresenter productsPresenter;

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

    @BindView(R.id.tv_product_amount)
    TextView tv_product_amount;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    LoanAdapter adapter;
    List<LoanDetail> list;
    LinearLayoutManager linearLayoutManager;
    boolean isRefresh;

    private LoanProductSelectPW proPW;
    private LoanProductSelectPW amountPW;
    private LoanProductSelectPW termPW;

    String tagName = null;
    String amount = null;
    String term = null;
    int lastVisibleItem;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitle("搜索贷款");
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
        productsPresenter = new ProductsPresenter(this);
        list = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new LoanAdapter(this);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(adapter);
    }

    @Override
    public void initViewData() {
        ll_pro.setOnClickListener(this);
        ll_amount.setOnClickListener(this);
        ll_term.setOnClickListener(this);
        int mainColor = ContextCompat.getColor(this, R.color.mainColor);
        swipe_layout.setColorSchemeColors(mainColor);

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isRefresh) {
                    swipe_layout.setRefreshing(false);
                    return;
                }
                if (NetworkUtil.checkNetwork(ProductsActivity.this)) {
                    page = 0;
                    productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);
                } else {
                    swipe_layout.setRefreshing(false);
                }
            }
        });
        rv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    productsPresenter.requestProductsMore(page, PAGE_SIZE, tagName, amount, term);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });
        swipe_layout.setRefreshing(true);
        productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);
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
                            if (text.equals("职业身份")) {
                                tagName = null;
                                tv_pro.setSelected(false);
                                iv_pro.setSelected(false);
                            }
                            LogU.t("tagName是：" + tagName + "，amount是：" + amount + "，term是：" + term);
                            page = 0;
                            productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);
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
                            if (text.equals("贷款额度")) {
                                amount = null;
                                tv_amount.setSelected(false);
                                iv_amount.setSelected(false);
                            } else {
                                amount = text.substring(0, text.indexOf("元"));
                            }
                            LogU.t("tagName是：" + tagName + "，amount是：" + amount + "，term是：" + term);
                            page = 0;
                            //调用接口
                            productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);

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
                            term = text;
                            if (text.equals("贷款期限")) {
                                term = null;
                                tv_term.setSelected(false);
                                iv_term.setSelected(false);
                            }
                            LogU.t("tagName是：" + tagName + "，amount是：" + amount + "，term是：" + term);
                            page = 0;
                            productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);

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

    @Override
    public void getProducts(ProductsBean bean) {
        //刷新
        List<LoanDetail> productList = bean.getContent().getPages().getContent();
        list.clear();
        list = productList;
        adapter.setList(list);
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getProductsMore(ProductsBean bean) {
//加载更多
        List<LoanDetail> productList = bean.getContent().getPages().getContent();
        if (productList.size() > 0) {
            list.addAll(productList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void getProductsFail(String error) {
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
        isRefresh = true;
    }

    @Override
    public void requestEnd() {
        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            //延迟500毫秒关闭swipe
            Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(
                    new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    swipe_layout.setRefreshing(false);
                                }
                            });
                        }
                    }
            );

        }
        isRefresh = false;
        tv_product_amount.setText(list == null ? "0" : list.size() + "");
    }

}
