package com.app.qunadai.content.ui.home;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.AddComment;
import com.app.qunadai.bean.v5.ProComment;
import com.app.qunadai.bean.v5.Product;
import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.content.contract.v5.AddCommentContract;
import com.app.qunadai.content.presenter.v5.AddCommentPresenter;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.third.eventbus.EventAddComment;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.ToastUtil;
import com.willy.ratingbar.BaseRatingBar;
import com.willy.ratingbar.ScaleRatingBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by wayne on 2017/9/14.
 */

public class AddCommentActivity extends BaseActivity implements AddCommentContract.View {
    private AddCommentPresenter addCommentPresenter;
    @BindView(R.id.iv_add_avatar)
    ImageView iv_add_avatar;
    @BindView(R.id.tv_add_name)
    TextView tv_add_name;
    @BindView(R.id.srb_add_star)
    ScaleRatingBar srb_add_star;
    @BindView(R.id.et_add_content)
    EditText et_add_content;
    @BindView(R.id.tv_add_submit)
    TextView tv_add_submit;

    float rating;

    @Override
    protected void updateTopViewHideAndShow() {
        setTitleText("添加评论");
    }

    @Override
    protected View createCenterView() {
        View view = View.inflate(this, R.layout.activity_addcomment, null);
        return view;
    }

    @Override
    protected View createBottomView() {
        return null;
    }

    Product p;

    @Override
    protected void initView() {
        p = (Product) getIntent().getSerializableExtra("product");
        addCommentPresenter = new AddCommentPresenter(this);

        tv_add_name.setText(p.getName());
        String imgUrl = RxHttp.ROOT + "attachments/" + p.getIcon();
        ImgUtil.loadImgAvatar(this, imgUrl, iv_add_avatar);
        tv_add_submit.setOnClickListener(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_submit:
                LogU.t(srb_add_star.getRating() + "");

                if (srb_add_star.getRating() == 0) {
                    ToastUtil.showToast(this, "评分不能为空");
                    return;
                }
                addCommentPresenter.addComment(p.getId(), getToken(),
                                            (int) srb_add_star.getRating(),
                                            CommUtil.getText(et_add_content));

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


    @Override
    public void addComment(BaseBean<AddComment> bean) {
        ToastUtil.showToast(this,"发送成功");
        ProComment pc = bean.getContent().getNewComment();
        EventAddComment eventAddComment = new EventAddComment();
        eventAddComment.setProComment(pc);
        EventBus.getDefault().post(eventAddComment);
        finish();
    }

    @Override
    public void addCommentFail(String error) {
        ToastUtil.showToast(this, error);

    }
}
