package com.app.qunadai.content.ui.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.AvatarBean;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.AccountContract;
import com.app.qunadai.content.presenter.AccountPresenter;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventNick;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.FileUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.yalantis.ucrop.UCrop;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wayne on 2017/5/15.
 */

public class AccountActivity extends BaseActivity implements AccountContract.View {
    private AccountPresenter accountPresenter;

    private static final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private static final int ACTIVITY_REQUEST_TAKE_PICTURE = 101;
    private static final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;
    private static final int ACTIVITY_REQUEST_NICKNAME = 103;

    @BindView(R.id.ll_account_layout)
    LinearLayout ll_account_layout;

    @BindView(R.id.iv_account_avatar)
    ImageView iv_account_avatar;
    @BindView(R.id.tv_account_nickname)
    TextView tv_account_nickname;
    @BindView(R.id.tv_account_phone)
    TextView tv_account_phone;

    @BindView(R.id.rl_account_avatar)
    RelativeLayout rl_account_avatar;
    @BindView(R.id.rl_account_nickname)
    RelativeLayout rl_account_nickname;
    @BindView(R.id.rl_account_phone)
    RelativeLayout rl_account_phone;
    private ArrayList<String> mImageList;

    Uri uriCrop;

    @Override
    protected void updateTopViewHideAndShow() {
//        setTitleBarStatus(TITLE_ON_BACK_ON);
        setTitleText("账号信息");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_account, null);

        return view;
    }

    @Override
    protected View createBottomView() {

        return null;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        accountPresenter = new AccountPresenter(this);
        mImageList = new ArrayList<>();

        String nickname = getIntent().getStringExtra("nickname");
        String phone = getIntent().getStringExtra("phone");
        String avatar = getIntent().getStringExtra("avatar");

        String imgUrl = RxHttp.ROOT + "/attachments/" + avatar;

        ImgUtil.loadRound(this, imgUrl, iv_account_avatar);

        tv_account_nickname.setText(nickname);
        tv_account_phone.setText(hideUserPhone(phone));

        rl_account_avatar.setOnClickListener(this);
        rl_account_nickname.setOnClickListener(this);
        rl_account_phone.setOnClickListener(this);
    }

    @Override
    public void initViewData() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_account_avatar:
                showPicDialog();
                break;
            case R.id.rl_account_nickname:
                Intent intent = new Intent(this, NicknameActivity.class);
                intent.putExtra("nickname", CommUtil.getText(tv_account_nickname));
                startActivity(intent);
                break;
        }
    }


    private void showPicDialog() {
        Point p = CommUtil.getSize(this);

        mImageList = new ArrayList<>();
        Album.album(AccountActivity.this)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO) // Request code.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .title("相册") // Title.
                .selectCount(1) // Choose up to a few pictures.
                .columnCount(p.x >= 720 ? 4 : 3) // 宽度大于720px则显示4列
                .camera(true) // Have a camera function.
                .checkedList(mImageList) // Has selected the picture, automatically select.
                .start();
    }

    public String hideUserPhone(String phone) {
        String resultPhone = "";
        if (phone.length() != 11) {
            return "";
        } else {
            resultPhone = phone.substring(0, 3) + "****" + phone.substring(7);
        }
        return resultPhone;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO: {
                //选取图片，选取完成后进入裁剪
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    refreshImage();
                    LogU.t(mImageList.size() + "-select");
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    Snackbar.make(ll_account_layout, "没有选择图片", Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case ACTIVITY_REQUEST_TAKE_PICTURE: {
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.addAll(imageList);
                    refreshImage();
                    LogU.t(mImageList.size() + "-take");
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                    Snackbar.make(ll_account_layout, "没有选择图片", Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList = Album.parseResult(data); // Parse select result.
                    refreshImage();
                    LogU.t(mImageList.size() + "preview");
                }
                break;
            }
            case UCrop.REQUEST_CROP: {
                //裁剪
                if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
                    final Uri resultUri = UCrop.getOutput(data);

                    uploadImg(resultUri);
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                    LogU.t(cropError.getMessage());

                }
                break;
            }


        }
    }

    /**
     * 在这里执行上传图片
     *
     * @param resultUri
     */
    private void uploadImg(Uri resultUri) {
//        CommUtil.Bitmap2StrByBase64()
        //图片解析成Bitmap对象
        try {
            Bitmap bm = BitmapFactory.decodeStream(
                    getContentResolver().openInputStream(resultUri));
            String base64 = CommUtil.Bitmap2StrByBase64(bm);
            accountPresenter.uploadAvatar(PrefUtil.getString(this, PrefKey.TOKEN, ""), base64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int size = 3;

    private void refreshImage() {
        //在这里上传图片
        if (mImageList != null && mImageList.size() > 0) {
            uriCrop = Uri.fromFile(new File(FileUtil.getImageDir(this) + File.separator + FileUtil.getRandomImgFilename()));
            Uri origin = Uri.fromFile(new File(mImageList.get(0)));
            UCrop.Options option = new UCrop.Options();
            option.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            option.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            option.setHideBottomControls(true);
            option.setCircleDimmedLayer(true);
            UCrop.of(origin, uriCrop)
                    .withOptions(option)
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(400, 400)
                    .start(this);
        }
    }

    @Override
    public void uploadAvatar(AvatarBean bean) {
        String imgUrl = RxHttp.ROOT + "/attachments/" + bean.getContent().getUser().getAvatar();
        ImgUtil.loadRound(this, imgUrl, iv_account_avatar);
    }

    @Override
    public void uploadAvatarFail(String error) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventNick event) {
        tv_account_nickname.setText(event.getNickname());
    }

}
