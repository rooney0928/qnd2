package com.app.qunadai.content.ui.product;

import android.content.Intent;
import android.icu.math.BigDecimal;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ProCommentAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.Product5DetailContract;
import com.app.qunadai.content.presenter.v5.Product5DetailPresenter;
import com.app.qunadai.content.ui.home.AddCommentActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventAddComment;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.ToastUtil;
import com.willy.ratingbar.ScaleRatingBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/9/14.
 */

public class Product5DetailActivity extends BaseActivity implements Product5DetailContract.View {

    private Product5DetailPresenter product5DetailPresenter;

    @BindView(R.id.iv_detail_avatar)
    ImageView iv_detail_avatar;
    @BindView(R.id.tv_detail_name)
    TextView tv_detail_name;
    @BindView(R.id.srb_detail_score)
    ScaleRatingBar srb_detail_score;
    @BindView(R.id.tv_detail_limit)
    TextView tv_detail_limit;
    @BindView(R.id.tv_detail_period)
    TextView tv_detail_period;
    @BindView(R.id.tv_detail_rate)
    TextView tv_detail_rate;
    @BindView(R.id.tv_detail_loan_time)
    TextView tv_detail_loan_time;
    @BindView(R.id.tv_detail_suc)
    TextView tv_detail_suc;
    @BindView(R.id.tv_detail_person)
    TextView tv_detail_person;
    @BindView(R.id.tv_detail_average)
    TextView tv_detail_average;
    @BindView(R.id.iv_detail_add)
    ImageView iv_detail_add;
    @BindView(R.id.rv_comment)
    RecyclerView rv_comment;

    @BindView(R.id.bt_submit)
    Button bt_submit;

    private ProCommentAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    boolean isRefresh;
    private static final int PAGE_SIZE = 5;
    int page = 0;
    int lastVisibleItem;

    List<ProComment> list;
    String pid;

    @Override
    protected void updateTopViewHideAndShow() {

    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_post_detail5, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        product5DetailPresenter = new Product5DetailPresenter(this);

        adapter = new ProCommentAdapter(this);
        pid = getIntent().getStringExtra("pid");

        list = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_comment.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_comment.setLayoutManager(linearLayoutManager);
//        rv_comment.setHasFixedSize(true);
        rv_comment.setNestedScrollingEnabled(false);
        rv_comment.setItemAnimator(new DefaultItemAnimator());
        rv_comment.setAdapter(adapter);

        //        adapter.setLoadMoreListener();
        rv_comment.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
//                    swipe_layout.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
//                    handler.sendEmptyMessageDelayed(0, 3000);
                    page++;
                    product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }

        });

        product5DetailPresenter.getProduct5Detail(pid, getToken());
        product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);


    }

    @Override
    public void initViewData() {
        bt_submit.setOnClickListener(this);
        iv_detail_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back://返回
                this.finish();
                break;
            case R.id.iv_detail_add:
                if (p != null) {
                    if (CommUtil.isNull(getToken())) {
                        exeLogin();
                    } else {
                        Intent intentAdd = new Intent(this, AddCommentActivity.class);
                        intentAdd.putExtra("product", p);
                        startActivity(intentAdd);
                    }

                }
                break;
            case R.id.bt_submit:
                if (NetworkUtil.checkNetwork(this)) {
                    if (CommUtil.isNull(getToken())) {
                        exeLogin();
                    } else {
                        product5DetailPresenter.applyOrder(getToken(), pid);
                        Intent intent = new Intent(Product5DetailActivity.this, BrowserActivity.class);
                        intent.putExtra("url", p.getUrl());
                        intent.putExtra("title", p.getName());
                        startActivity(intent);
                    }
                }
                break;
        }
        super.onClick(v);
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {

    }

    @Override
    public void requestStart() {
        showLoading();
    }

    @Override
    public void requestEnd() {
        hideLoading();
    }

    Product p;

    @Override
    public void getProduct5Detail(BaseBean<Product5DetailBean> bean) {
        if (!bean.getCode().equals("000")) {
            ToastUtil.showToast(this, bean.getMsg());
            return;
        }
        p = bean.getContent().getProduct();
        setTitleText(p.getName());

        String imgUrl = RxHttp.ROOT + "attachments/" + p.getIcon();
        ImgUtil.loadImgAvatar(this, imgUrl, iv_detail_avatar);
        tv_detail_name.setText(p.getName());
        tv_detail_limit.setText(p.getMaxAmount() + "");

        double stars = (double) p.getTotalStarNumber();
        double comments = (double) p.getTotalCommentNumber();
        long star = Math.round(stars / comments);

        if (star < 3) {
            srb_detail_score.setVisibility(View.GONE);
        } else {
            srb_detail_score.setVisibility(View.VISIBLE);
            srb_detail_score.setRating(star);
        }

        tv_detail_period.setText(p.getMaxTerm() + p.getTermUnit());

        String unit = "";
        switch (p.getRateStatus()) {
            case "MONTH":
                unit = "月";
                break;
            case "WEEK":
                unit = "周";
                break;
            case "DAY":
                unit = "天";
                break;
            default:
                unit = "期";
                break;
        }
        tv_detail_rate.setText(p.getMinRate() + "%/" + unit);


        tv_detail_loan_time.setText(p.getLoanTime() + getUnit(p.getLoanTimeUnit()));

        tv_detail_suc.setText(p.getSucRate() + "%");
        tv_detail_person.setText(p.getNum() + "人");


        double amount = (p.getMaxAmount() + p.getMinAmount()) / 2;

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

        tv_detail_average.setText(df.format(amount) + "元");
    }

    @Override
    public void getProduct5DetailFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void getProduct5Comments(BaseBean<ProComments> bean) {
        List<ProComment> tempList = bean.getContent().getComments().getContent();
        list.clear();
        list = tempList;
        adapter.setList(list);
        adapter.setTotalComment(bean.getContent().getComments().getTotalElements());
        linearLayoutManager.scrollToPosition(0);
    }

    @Override
    public void getProduct5CommentsMore(BaseBean<ProComments> bean) {
        //加载更多
        List<ProComment> tempList = bean.getContent().getComments().getContent();
        if (tempList.size() > 0) {
            list.addAll(tempList);
            adapter.setList(list);
            adapter.setTotalComment(bean.getContent().getComments().getTotalElements());

        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void getProduct5CommentsFail(String error) {
        ToastUtil.showToast(this, error);

    }

    public String getUnit(String unit) {
        if (unit.equalsIgnoreCase("SECONDS") || unit.equalsIgnoreCase("SECOND")) {
            return "秒";
        } else if (unit.equalsIgnoreCase("MINUTES") || unit.equalsIgnoreCase("MINUTE")) {
            return "分钟";
        } else if (unit.equalsIgnoreCase("HOURS") || unit.equalsIgnoreCase("HOUR")) {
            return "小时";
        } else {
            return "";
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventAddComment event) {
        page = 0;
        product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
