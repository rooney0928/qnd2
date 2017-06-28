package com.app.qunadai.content.ui.bbs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.PostImgAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/24.
 */

public class PostActivity extends BaseActivity {

    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;


    @BindView(R.id.rl_post_layout)
    RelativeLayout rl_post_layout;
    @BindView(R.id.iv_post_album)
    ImageView iv_post_album;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    GridLayoutManager gridLayoutManager;
    PostImgAdapter adapter;
    private ArrayList<String> mImageList;

    @Override
    protected void updateTopViewHideAndShow() {
        clearTitleBar();
        setTitleBarVisible(true);
        setTitleLeftText("取消");
        setTitleText("发帖");
        setTitleRightImg(R.mipmap.ic_send);
        setTitleLeftEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });
    }

    AlertDialog dialog;

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("返回会丢失所编辑内容，确定返回么?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PostActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog = builder.show();
    }


    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_post, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    @Override
    protected void initView() {
        mImageList = new ArrayList<>();
        adapter = new PostImgAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                previewImage(position);
            }
        });
        gridLayoutManager = new GridLayoutManager(this, 4);
        rv_list.setLayoutManager(gridLayoutManager);
        rv_list.setAdapter(adapter);
        adapter.setImages(mImageList);
    }

    /**
     * 点击进入预览图片
     *
     * @param position
     */
    private void previewImage(int position) {
        Album.gallery(this)
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
//                .navigationBarColor(navigationBarColor) // NavigationBar color.

                .checkedList(mImageList) // List of pictures to preview.
                .currentPosition(position) // First display position image of the list.
                .checkFunction(true) // Anti-election function.
                .start(ACTIVITY_REQUEST_PREVIEW_PHOTO);
    }

    @Override
    public void initViewData() {
        iv_post_album.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_post_album:
                Point p = CommUtil.getSize(this);
                Album.album(this)
                        .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO) // Request code.
                        .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                        .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .title("相册") // Title.
                        .selectCount(9) // Choose up to a few pictures.
                        .columnCount(p.x >= 720 ? 4 : 3) // 宽度大于720px则显示4列
                        .camera(true) // Have a camera function.
                        .checkedList(mImageList) // Has selected the picture, automatically select.
                        .start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO:
                //选取图片，
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    adapter.setImages(mImageList);
//                    LogU.t(mImageList.size() + "-select");
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    Snackbar.make(rl_post_layout, "没有选择图片", Snackbar.LENGTH_LONG).show();
                }
                break;
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    adapter.setImages(mImageList);

                }
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public void onBackPressed() {
        if (dialog == null || !dialog.isShowing()) {
            showExitDialog();
        }
    }
}
