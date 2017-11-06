package com.app.qunadai.content.ui.me.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.qunadai.R;
import com.app.qunadai.bean.base.BaseBean;
import com.app.qunadai.bean.v5.FeedBack;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.FeedbackContract;
import com.app.qunadai.content.presenter.v5.FeedbackPresenter;
import com.app.qunadai.third.eventbus.EventTurn;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by wayne on 2017/11/6.
 */

public class FeedStep1Fragment extends BaseFragment implements FeedbackContract.View {
    private FeedbackPresenter feedbackPresenter;

    @BindView(R.id.et_feedback_content)
    EditText et_feedback_content;
    @BindView(R.id.tv_feedback_submit)
    TextView tv_feedback_submit;

    public static FeedStep1Fragment getInstance() {
        FeedStep1Fragment feedStep1Fragment = new FeedStep1Fragment();
        Bundle bundle = new Bundle();
        feedStep1Fragment.setArguments(bundle);
        return feedStep1Fragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        feedbackPresenter = new FeedbackPresenter(this);

        tv_feedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CommUtil.isNull(et_feedback_content)) {
                    feedbackPresenter.addFeedback(getToken(), CommUtil.getText(et_feedback_content));
                } else {
                    ToastUtil.showToast(getContext(), "内容不能为空");
                }
            }
        });
    }

    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_feedback1, null);
        return root;
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
    public void addFeedback(BaseBean<FeedBack> bean) {
        ToastUtil.showToast(getContext(), "恭喜您！发送成功");
        EventBus.getDefault().post(new EventTurn(0, "feedback"));
    }

    @Override
    public void addFeedbackFail(String error) {
        ToastUtil.showToast(getContext(), error);

    }
}
