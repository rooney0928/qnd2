package com.app.qunadai.content.ui.bbs;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.PostNewBean;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.PostImgAdapter;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.bbs.PostNewContract;
import com.app.qunadai.content.presenter.bbs.PostNewPresenter;
import com.app.qunadai.third.eventbus.EventRefresh;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.FileSizeUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ToastUtil;
import com.bumptech.glide.Glide;
import com.yanzhenjie.album.Album;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.BitmapBatchCallback;
import com.zxy.tiny.callback.FileBatchCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/24.
 */

public class PostActivity extends BaseActivity implements PostNewContract.View {

    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;

    private PostNewPresenter postNewPresenter;

    @BindView(R.id.rl_post_layout)
    RelativeLayout rl_post_layout;
    @BindView(R.id.iv_post_album)
    ImageView iv_post_album;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.et_post_title)
    EditText et_post_title;
    @BindView(R.id.et_post_content)
    EditText et_post_content;

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
        setTitleRightEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里提交
                sendPost();

            }
        });
        setTitleLeftEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });
    }

    private void sendPost() {
        final String title = CommUtil.getText(et_post_title);
        final String content = CommUtil.getText(et_post_content);

        if (title.length() < 3) {
            ToastUtil.showToast(this, "标题长度不足3位");
            return;
        }
        String[] pics = mImageList.toArray(new String[mImageList.size()]);

        /*
        Tiny.getInstance().source(pics).batchAsFile().batchCompress(new FileBatchCallback() {
            @Override
            public void callback(boolean isSuccess, String[] outfile) {
                if (isSuccess) {
                    String token = PrefUtil.getString(PostActivity.this, PrefKey.TOKEN, "");

                    String[] base64 = new String[outfile.length];

                    for (int i = 0; i < outfile.length; i++) {
//                        BitmapFactory.Options options = new BitmapFactory.Options();
//                        options.inTempStorage = new byte[1024*1024*5]; //5MB的临时存储空间
//                        Bitmap bm = BitmapFactory.decodeFile(outfile[i],options);
                        Bitmap bm = BitmapFactory.decodeFile(outfile[i]);

                        base64[i] = CommUtil.Bitmap2StrByBase64(bm);
//                        LogU.t(base64[i]);
//                        System.out.println(base64[i]);

//                        ImgUtil.loadImg();
//                        Glide.with(PostActivity.this).load(bm).into(iv_post_album);
                    }
//                    CommUtil.Bitmap2StrByBase64(bm);
                    postNewPresenter.postNew(token, title, content, base64);

                }
            }
        });
*/
        if (pics.length > 0) {
            Tiny.getInstance().source(pics).batchAsBitmap().batchCompress(new BitmapBatchCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap[] bitmaps) {
                    //return the batch compressed bitmap object
                    String[] base64 = new String[bitmaps.length];
                    String token = PrefUtil.getString(PostActivity.this, PrefKey.TOKEN, "");

                    for (int i = 0; i < bitmaps.length; i++) {
                        base64[i] = CommUtil.Bitmap2StrByBase64(bitmaps[i]);
                    }
                    postNewPresenter.postNew(token, title, content, base64);
                }
            });
        } else {
            String token = PrefUtil.getString(PostActivity.this, PrefKey.TOKEN, "");
            postNewPresenter.postNew(token, title, content, new String[]{});
        }


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
        postNewPresenter = new PostNewPresenter(this);
        mImageList = new ArrayList<>();
        adapter = new PostImgAdapter(this, new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                previewImage(position);
            }
        });
        adapter.setCanDelete(true);
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
                        .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                        .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .title("相册") // Title.
                        .selectCount(3) // Choose up to a few pictures.
                        .columnCount(p.x >= 720 ? 4 : 3) // 宽度大于720px则显示4列
                        .camera(true) // Have a camera function.
                        .checkedList(mImageList) // Has selected the picture, automatically select.
                        .start(ACTIVITY_REQUEST_SELECT_PHOTO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO:
                //选取图片
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
        showLoading();
    }

    @Override
    public void requestEnd() {
        hideLoading();
    }

    @Override
    public void onBackPressed() {
        if (dialog == null || !dialog.isShowing()) {
            showExitDialog();
        }
    }

    @Override
    public void postNewOk(PostNewBean bean) {
        ToastUtil.showToast(this, "恭喜您，发帖成功");
        if (manager != null) {
            manager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                    0);
        }
        EventBus.getDefault().post(new EventRefresh());
        finish();
    }

    @Override
    public void postNewFail(String error) {
        ToastUtil.showToast(this, error);
    }


}
