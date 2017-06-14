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
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.LoanAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.ProductsContract;
import com.app.qunadai.content.presenter.ProductsPresenter;
import com.app.qunadai.content.view.filter.FilterPopup;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;


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
    @BindView(R.id.view_shadow)
    View view_shadow;


    LoanAdapter adapter;
    List<LoanDetail> list;
    LinearLayoutManager linearLayoutManager;
    boolean isRefresh;


    private FilterPopup proPop;
    private FilterPopup amountPop;
    private FilterPopup termPop;

    String tagName = null;
    String amount = null;
    String term = null;
    int lastVisibleItem;


    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_ON_BACK_ON);

        setTitleText("贷款");
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
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
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

        if (NetworkUtil.checkNetwork(this)) {
            productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);
        }
    }

    @Override
    public void onClick(View v) {

        super.onClick(v);

        switch (v.getId()) {
            case R.id.ll_pro:

                final String[] pros = getResources().getStringArray(R.array.filter_pro);
                if (proPop == null) {
                    proPop = new FilterPopup(this, pros);
                }
                proPop.setItemClickListener(new OnCompatItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        tv_pro.setText(pros[position]);
                        tv_pro.setSelected(true);
                        iv_pro.setSelected(true);
                        tagName = pros[position];
                        if (position == 0) {
                            tagName = null;
                            tv_pro.setSelected(false);
                            iv_pro.setSelected(false);
                        }
                        page = 0;

                        productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);

                    }
                });
                proPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view_shadow.setVisibility(View.GONE);
                    }
                });
                proPop.showPopupWindow(ll_filter);
                view_shadow.setVisibility(View.VISIBLE);


                break;
            case R.id.ll_amount:

                final String[] amounts = getResources().getStringArray(R.array.filter_amount);
                if (amountPop == null) {
                    amountPop = new FilterPopup(this, amounts);
                }
                amountPop.setItemClickListener(new OnCompatItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        tv_amount.setText(amounts[position]);
                        tv_amount.setSelected(true);
                        iv_amount.setSelected(true);
                        if (position == 0) {
                            amount = null;
                            tv_amount.setSelected(false);
                            iv_amount.setSelected(false);
                        } else {
                            amount = amounts[position].substring(0, amounts[position].indexOf("元"));
                        }
                        page = 0;
                        //调用接口
                        productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);

                    }
                });
                amountPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view_shadow.setVisibility(View.GONE);
                    }
                });
                amountPop.showPopupWindow(ll_filter);
                view_shadow.setVisibility(View.VISIBLE);

                break;
            case R.id.ll_term:


                final String[] terms = getResources().getStringArray(R.array.filter_term);
                if (termPop == null) {
                    termPop = new FilterPopup(this, terms);
                }
                termPop.setItemClickListener(new OnCompatItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        tv_term.setText(terms[position]);
                        tv_term.setSelected(true);
                        iv_term.setSelected(true);
                        term = terms[position];
                        if (position == 0) {
                            term = null;
                            tv_term.setSelected(false);
                            iv_term.setSelected(false);
                        }
                        page = 0;
                        productsPresenter.requestProducts(page, PAGE_SIZE, tagName, amount, term);

                    }
                });
                termPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view_shadow.setVisibility(View.GONE);
                    }
                });
                termPop.showPopupWindow(ll_filter);
                view_shadow.setVisibility(View.VISIBLE);
                LogU.t("tagName是：" + tagName + "，amount是：" + amount + "，term是：" + term);

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
