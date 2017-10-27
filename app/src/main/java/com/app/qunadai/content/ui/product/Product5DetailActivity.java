package com.app.qunadai.content.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.qunadai.QNDFactory;
import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.NewReply;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.ProComments;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.bean.v5.Product5DetailBean;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.adapter.v5.ProCommentAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.Product5DetailContract;
import com.app.qunadai.content.inter.OnKeyBoardChangeListener;
import com.app.qunadai.content.inter.OnReLinkListener;
import com.app.qunadai.content.presenter.v5.Product5DetailPresenter;
import com.app.qunadai.content.ui.home.AddCommentActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventAddComment;
import com.app.qunadai.third.keyboard.CheckSoftInputLayout;
import com.app.qunadai.third.keyboard.KeyboardUtil;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;
import com.willy.ratingbar.ScaleRatingBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/9/14.
 */

public class Product5DetailActivity extends BaseActivity implements Product5DetailContract.View {

    private Product5DetailPresenter product5DetailPresenter;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.sv_layout)
    ScrollView sv_layout;
    @BindView(R.id.iv_detail_avatar)
    ImageView iv_detail_avatar;
    @BindView(R.id.tv_detail_name)
    TextView tv_detail_name;
    @BindView(R.id.srb_detail_score)
    ScaleRatingBar srb_detail_score;
    @BindView(R.id.tv_detail_limit)
    TextView tv_detail_limit;
    //    @BindView(R.id.tv_detail_period)
//    TextView tv_detail_period;
    @BindView(R.id.tv_detail_rate)
    TextView tv_detail_rate;
    @BindView(R.id.tv_detail_loan_time)
    TextView tv_detail_loan_time;
    //    @BindView(R.id.tv_detail_suc)
//    TextView tv_detail_suc;
    @BindView(R.id.tv_detail_person)
    TextView tv_detail_person;
    //    @BindView(R.id.tv_detail_average)
//    TextView tv_detail_average;
    @BindView(R.id.iv_detail_add)
    ImageView iv_detail_add;
    @BindView(R.id.rv_comment)
    RecyclerView rv_comment;
    @BindView(R.id.iv_empty)
    ImageView iv_empty;

    @BindView(R.id.bt_submit)
    Button bt_submit;
    @BindView(R.id.view_input)
    View view_input;

    @BindView(R.id.et_reply_content)
    EditText et_reply_content;
    @BindView(R.id.ll_input_layout)
    LinearLayout ll_input_layout;
    @BindView(R.id.iv_reply_send)
    ImageView iv_reply_send;

    @BindView(R.id.content_main)
    CheckSoftInputLayout content_main;

    private ProCommentAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    boolean isRefresh;
    private static final int PAGE_SIZE = 5;
    int page = 0;
    int lastVisibleItem;

    List<ProComment> list;
    String pid;
    //当前评论id
    String cid;

    String name;


    InputMethodManager inputMethodManager;

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
        LogU.t("open");
        EventBus.getDefault().register(this);

        product5DetailPresenter = new Product5DetailPresenter(this);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        adapter = new ProCommentAdapter(this);
        pid = getIntent().getStringExtra("pid");
        name = getIntent().getStringExtra("name");

        list = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);
        rv_comment.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_comment.setLayoutManager(linearLayoutManager);
//        rv_comment.setHasFixedSize(true);
        rv_comment.setNestedScrollingEnabled(false);
        rv_comment.setItemAnimator(new DefaultItemAnimator());
        rv_comment.setAdapter(adapter);

        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                if (NetworkUtil.checkNetwork(Product5DetailActivity.this)) {
                    page = 0;
                    product5DetailPresenter.getProduct5Detail(pid, getToken());
                    product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);
                } else {
                    setViewOffline();
                }
            }

        });

        adapter.setLoadMoreListener(new ProCommentAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);
            }
        });

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


    }


    @Override
    public void initViewData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv_layout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    bt_submit.setVisibility(View.VISIBLE);
                    view_input.setVisibility(View.GONE);
                    KeyboardUtil.hideSoftInput(Product5DetailActivity.this);
                }
            });
        }
        bt_submit.setOnClickListener(this);
        iv_detail_add.setOnClickListener(this);
        iv_reply_send.setOnClickListener(this);
/*
        controlKeyboardLayout(cl_detail_layout, ll_input_layout, new OnKeyBoardChangeListener() {
            @Override
            public void keyBoardChange(boolean isShow) {
                if (isShow) {
                    bt_submit.setVisibility(View.GONE);
                    view_input.setVisibility(View.VISIBLE);
                } else {
                    bt_submit.setVisibility(View.VISIBLE);
                    view_input.setVisibility(View.GONE);
                }
            }
        });


*/

        content_main.setOnResizeListener(new CheckSoftInputLayout.OnResizeListener() {
            @Override
            public void onResize(int w, int h, int oldw, int oldh) {
                //如果第一次初始化
                if (oldh == 0) {
                    return;
                }
                //如果用户横竖屏转换
                if (w != oldw) {
                    return;
                }
                if (h < oldh) {
                    //输入法弹出
                    bt_submit.setVisibility(View.GONE);
                    view_input.setVisibility(View.VISIBLE);
                } else if (h > oldh) {
                    //输入法关闭
//                    setInputViewEnabled(false);
                    bt_submit.setVisibility(View.VISIBLE);
                    view_input.setVisibility(View.GONE);
                }

            }
        });
        adapter.setOnClickReplyListener(new ProCommentAdapter.OnClickReplyListener() {
            @Override
            public void replyComment(int position) {
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }


                view_input.setVisibility(View.VISIBLE);
                bt_submit.setVisibility(View.GONE);
                ProComment pc = list.get(position);
                if (CheckUtil.isMobile(pc.getUsernick())) {
                    StringBuilder sb = new StringBuilder(pc.getUsernick());
                    String username = sb.replace(3, pc.getUsernick().length() - 4, "****").toString();
//                    tv_comment_username.setText(username);
                    et_reply_content.setHint("回复 " + username);

                } else {
                    et_reply_content.setHint("回复 " + pc.getUsernick());
                }
                cid = pc.getId();

                et_reply_content.setText("");
                et_reply_content.setFocusable(true);
//                inputMethodManager.showSoftInput(et_reply_content,InputMethodManager.SHOW_FORCED);
                openKeyboard(et_reply_content);
            }
        });
        if (NetworkUtil.checkNetwork(this)) {
            product5DetailPresenter.getProduct5Detail(pid, getToken());
            product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);
        } else {
            setViewOffline();
        }

        setOnReLinkListener(new OnReLinkListener() {
            @Override
            public void doNewRequest() {
                page = 0;
                product5DetailPresenter.getProduct5Detail(pid, getToken());
                product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);
            }
        });


//        controlKeyboardLayout(content_main,et_reply_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommUtil.isNull(name)) {
            CommUtil.tcStart(this, name);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!CommUtil.isNull(name)) {
            CommUtil.tcEnd(this, name);
        }
    }

    /**
     * 解决在页面底部置输入框，输入法弹出遮挡部分输入框的问题
     *
     * @param root 页面根元素
     */
    public static void controlKeyboardLayout(final View root,
                                             final View editLayout) {
        // TODO Auto-generated method stub
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInVisibleHeigh = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInVisibleHeigh > 100) {
                    int[] location = new int[2];
                    //获取editLayout在窗体的坐标
                    editLayout.getLocationInWindow(location);
                    //计算root滚动高度，使editLayout在可见区域
                    int srollHeight = (location[1] + editLayout.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back://返回
                this.finish();
                break;
            case R.id.iv_reply_send:
                //发帖
                if (CommUtil.getText(et_reply_content).length() < QNDFactory.COMMENT_MIN) {
                    ToastUtil.showToast(this, "评论内容不能少于" + QNDFactory.COMMENT_MIN + "个字符");

                    return;
                }

                product5DetailPresenter.sendNewReply(cid, getToken(), CommUtil.getText(et_reply_content));
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
                        if (p != null) {
                            product5DetailPresenter.applyOrder(getToken(), pid);
                            Intent intent = new Intent(Product5DetailActivity.this, BrowserActivity.class);

                            intent.putExtra("url", p.getUrl());
//                            intent.putExtra("url", "http://a.app.qq.com/o/simple.jsp?pkgname=com.lianchuan.kaledai");
//                            intent.putExtra("url", "file:///android_asset/file.html");

                            intent.putExtra("title", p.getName());
                            startActivity(intent);
                        }
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

        if (swipe_layout != null && swipe_layout.isRefreshing()) {
            swipe_layout.setRefreshing(false);
        }

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
        CommUtil.tcEvent(this, p.getName(), "产品详情页");

        String imgUrl = RxHttp.ROOT + "attachments/" + p.getIcon();
        ImgUtil.loadImgAvatar(this, imgUrl, iv_detail_avatar);
        tv_detail_name.setText(p.getName());

        DecimalFormat dfdot = new DecimalFormat("#,###");
        tv_detail_limit.setText(dfdot.format(p.getMaxAmount()) + "");

//        double stars = (double) p.getTotalStarNumber();
//        double comments = (double) p.getTotalCommentNumber();
//        long star = Math.round(stars / comments);
//
//        if (star <= 3) {
//            srb_detail_score.setVisibility(View.GONE);
//            srb_detail_score.setRating(star);
//
//        } else {
//            srb_detail_score.setVisibility(View.VISIBLE);
//            srb_detail_score.setRating(star);
//        }
//        srb_detail_score.setVisibility(p.getTotalCommentNumber() <= 3 ? View.GONE : View.VISIBLE);


//        tv_detail_period.setText(p.getMaxTerm() + p.getTermUnit());

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

//        tv_detail_suc.setText(p.getSucRate() + "%");
        tv_detail_person.setText(p.getNum() + "人");


        double amount = (p.getMaxAmount() + p.getMinAmount()) / 2;

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

//        tv_detail_average.setText(df.format(amount) + "元");
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

        setEmpty();
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
        setEmpty();
    }

    public void setEmpty() {
        if (list.size() > 0) {
            iv_empty.setVisibility(View.GONE);
        } else {
            iv_empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getProduct5CommentsFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Override
    public void sendNewReply(BaseBean<NewReply> bean) {
        ToastUtil.showToast(this, "恭喜您!回复成功!");
        page = 0;
        product5DetailPresenter.getProduct5Comments(pid, page, PAGE_SIZE);

        hideKeyboard(et_reply_content);

        bt_submit.setVisibility(View.VISIBLE);
        view_input.setVisibility(View.GONE);
    }

    @Override
    public void sendNewReplyFail(String error) {
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
    public void tokenFail() {
        super.tokenFail();
        PrefUtil.removeItem(this, PrefKey.TOKEN);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

}
