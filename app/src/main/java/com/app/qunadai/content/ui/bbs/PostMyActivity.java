package com.app.qunadai.content.ui.bbs;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Post;
import com.app.qunadai.bean.bbs.PostListBean;
import com.app.qunadai.content.adapter.PostAdapter;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.bbs.PostMyContract;
import com.app.qunadai.content.presenter.bbs.PostMyPresenter;
import com.app.qunadai.third.eventbus.EventMe;
import com.app.qunadai.third.eventbus.EventRefresh;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.NetworkUtil;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
 * Created by wayne on 2017/7/4.
 */

public class PostMyActivity extends BaseActivity implements PostMyContract.View {
    private static final int PAGE_SIZE = 10;
    int page = 0;

    private PostMyPresenter postMyPresenter;
    //
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
//    @BindView(R.id.rv_list)
//    RecyclerView rv_list;

    @BindView(R.id.rv_list)
    SwipeMenuRecyclerView rv_list;

    LinearLayoutManager linearLayoutManager;
    PostAdapter adapter;

    boolean isRefresh;

    List<Post> list;

    private String token;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("我的帖子");
        setTitleRightImg(R.mipmap.ic_post);
        setTitleRightEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入发帖
                Intent intentPost = new Intent(PostMyActivity.this, PostActivity.class);
                startActivity(intentPost);
            }
        });
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_post_my, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        list = new ArrayList<>();
        postMyPresenter = new PostMyPresenter(this);
        adapter = new PostAdapter(this);
        token = PrefUtil.getString(this, PrefKey.TOKEN, "");

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.gap_line);


        rv_list.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        rv_list.setLayoutManager(linearLayoutManager);
//        rv_list.setHasFixedSize(true);
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(adapter);
        //滚动监听
        rv_list.addOnScrollListener(mOnScrollListener);


        int mainColor = ContextCompat.getColor(this, R.color.mainColor);
        swipe_layout.setColorSchemeColors(mainColor);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                if (isRefresh) {
                    return;
                }
                if (NetworkUtil.checkNetwork(PostMyActivity.this)) {
                    page = 0;
                    postMyPresenter.getPostList(token, page, PAGE_SIZE);
                } else {
                    swipe_layout.setRefreshing(false);
                }
            }
        });


    }

    /**
     * 加载更多
     */
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            LogU.t("load my");
            if (!recyclerView.canScrollVertically(1)) {// 手指不能向上滑动了
                // TODO 这里有个注意的地方，如果你刚进来时没有数据，
                // TODO 但是设置了适配器，这个时候就会触发加载更多，需要开发者判断下是否有数据，如果有数据才去加载更多。
                if (list != null && list.size() > 0) {
                    page++;
                    postMyPresenter.getPostList(token, page, PAGE_SIZE);
                }
            }
        }
    };


    @Override
    public void initViewData() {
        int mainColor = ContextCompat.getColor(this, R.color.mainColor);
        swipe_layout.setColorSchemeColors(mainColor);


        if (NetworkUtil.checkNetwork(this)) {
//            recommendPresenter.getRecommend(page, PAGE_SIZE);
            postMyPresenter.getPostList(token, page, PAGE_SIZE);

        }
    }

    @Override
    public void updateView(Object serverData) {

    }

    @Override
    public void updateError(String error) {
        ToastUtil.showToast(this, error);
    }

    @Override
    public void requestStart() {
//        showLoading();
        isRefresh = true;
    }

    @Override
    public void requestEnd() {
//        hideLoading();
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
    }


    @Override
    public void postList(PostListBean bean) {
        //刷新
        List<Post> postList = bean.getContent().getMyArticles().getContent();
        list.clear();
        list = postList;
        adapter.setList(list);
    }

    @Override
    public void postListMore(PostListBean bean) {
        //加载更多
        List<Post> postList = bean.getContent().getMyArticles().getContent();
        if (postList.size() > 0) {
            list.addAll(postList);
            adapter.setList(list);
        } else {
            if (page > 0) {
                page--;
            }
            ToastUtil.showToast(this, "没有更多数据");
        }
    }

    @Override
    public void postListFail(String error) {
        ToastUtil.showToast(this, error);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventRefresh event) {
        page = 0;
        postMyPresenter.getPostList(token, page, PAGE_SIZE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
