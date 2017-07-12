package com.app.qunadai.content.ui.bbs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.app.qunadai.R;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.view.HackyViewPager;
import com.app.qunadai.third.eventbus.EventAlbum;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.github.chrisbanes.photoview.PhotoView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/7/12.
 */

public class AlbumActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    HackyViewPager view_pager;

    private List<String> images;
    private PicPagerAdapter adapter;

    @Override
    protected void updateTopViewHideAndShow() {
//        clearTitleBar();
        setTitleText("预览");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_album, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        List<String> list = getIntent().getStringArrayListExtra("pics");
        int position = getIntent().getIntExtra("position", 0);


        adapter = new PicPagerAdapter(this);
        view_pager.setAdapter(adapter);
        adapter.setPics(list);
        view_pager.setCurrentItem(position);
        adapter.notifyDataSetChanged();
        LogU.t("end");
    }

    @Override
    public void initViewData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(EventAlbum event) {
        LogU.t("start");
        adapter = new PicPagerAdapter(this);
        adapter.setPics(event.getPics());
        adapter.notifyDataSetChanged();
        view_pager.setCurrentItem(event.getPosition());
        LogU.t("end");

    }


    static class PicPagerAdapter extends PagerAdapter {
        private Context context;
        private List<String> pics;

        public PicPagerAdapter(Context context) {
            this.context = context;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        @Override
        public int getCount() {
            return pics == null ? 0 : pics.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            String url = pics.get(position);
            LogU.t("url--"+url);
            ImgUtil.loadImg(context, url, photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
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
}
