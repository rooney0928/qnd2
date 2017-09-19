package com.app.qunadai.content.ui.me.frag;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.MyApp;
import com.app.qunadai.R;
import com.app.qunadai.bean.MeBean;
import com.app.qunadai.bean.PersonBean;
import com.app.qunadai.content.base.BaseFragment;
import com.app.qunadai.content.contract.v5.Me5Contract;
import com.app.qunadai.content.presenter.v5.Me5Presenter;
import com.app.qunadai.content.ui.me.AuthActivity;
import com.app.qunadai.content.ui.me.ExploreActivity;
import com.app.qunadai.content.ui.me.SettingActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.CheckUtil;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.PrefKey;
import com.app.qunadai.utils.PrefUtil;
import com.app.qunadai.utils.ReqKey;
import com.app.qunadai.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;

import static com.app.qunadai.MyApp.context;

/**
 * Created by wayne on 2017/9/7.
 */

public class Me5Fragment extends BaseFragment implements Me5Contract.View, View.OnClickListener {

    private Me5Presenter me5Presenter;
    @BindView(R.id.iv_me_avatar)
    ImageView iv_me_avatar;
    @BindView(R.id.tv_me_limit)
    TextView tv_me_limit;
    @BindView(R.id.tv_me_name)
    TextView tv_me_name;

    @BindView(R.id.ll_me_explore)
    LinearLayout ll_me_explore;
    @BindView(R.id.ll_me_calendar)
    LinearLayout ll_me_calendar;
    @BindView(R.id.ll_me_share)
    LinearLayout ll_me_share;
    @BindView(R.id.ll_me_message)
    LinearLayout ll_me_message;
    @BindView(R.id.ll_me_setting)
    LinearLayout ll_me_setting;


    public static Me5Fragment getInstance() {
        Me5Fragment meFragment = new Me5Fragment();
        Bundle bundle = new Bundle();
        meFragment.setArguments(bundle);
        return meFragment;
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {

    }


    @Override
    protected View createRootView() {
        View root = View.inflate(getActivity(), R.layout.fragment_me5, null);
        return root;
    }

    @Override
    protected void initData() {
        me5Presenter = new Me5Presenter(this);

        tv_me_limit.setOnClickListener(this);
        ll_me_explore.setOnClickListener(this);
        ll_me_calendar.setOnClickListener(this);
        ll_me_share.setOnClickListener(this);
        ll_me_message.setOnClickListener(this);
        ll_me_setting.setOnClickListener(this);

    }

    public void requestData() {

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
//tv_me_limit.setOnClickListener(this);
//        ll_me_explore.setOnClickListener(this);
//        ll_me_calendar.setOnClickListener(this);
//        ll_me_share.setOnClickListener(this);
//        ll_me_message.setOnClickListener(this);
//        ll_me_setting.setOnClickListener(this);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_me_explore:
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                Intent intentExplore = new Intent(getActivity(), ExploreActivity.class);
                startActivity(intentExplore);
                break;
            case R.id.ll_me_calendar:

                break;
            case R.id.ll_me_share:
                openBottom();
                break;
            case R.id.ll_me_message:
            case R.id.tv_me_limit:
                if (CommUtil.isNull(getToken())) {
                    exeLogin();
                    return;
                }
                Intent intentAuth = new Intent(getActivity(), AuthActivity.class);
                startActivity(intentAuth);
                break;
            case R.id.ll_me_setting:
                Intent intentSet = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intentSet, ReqKey.REQ_QUIT_SYSTEM);
                break;
        }
    }

    LinearLayout ll_me_timeline;
    LinearLayout ll_me_session;
    LinearLayout ll_me_link;
    TextView tv_me_cancel;

    String share_download_url = "https://m.qunadai.com/html/qrdownload/qrdownload.html";
    BottomSheetDialog dialog;

    private void openBottom() {
//        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.recyclerview, null);
        View view = View.inflate(getActivity(), R.layout.view_share, null);
        ll_me_timeline = (LinearLayout) view.findViewById(R.id.ll_me_timeline);
        ll_me_session = (LinearLayout) view.findViewById(R.id.ll_me_session);
        ll_me_link = (LinearLayout) view.findViewById(R.id.ll_me_link);
        tv_me_cancel = (TextView) view.findViewById(R.id.tv_me_cancel);
        ll_me_timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToWX(SendMessageToWX.Req.WXSceneTimeline);
            }
        });
        ll_me_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToWX(SendMessageToWX.Req.WXSceneSession);
            }
        });

        ll_me_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", share_download_url);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                ToastUtil.showToast(getActivity(), "已复制到剪切板");
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        tv_me_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog = new BottomSheetDialog(getActivity());
        dialog.setContentView(view);
        dialog.show();

    }

    public void requestUserData() {
        me5Presenter.requestCurrent(getToken());
        me5Presenter.requestPersonValue(getToken());
    }

    @Override
    public void getCurrent(MeBean meBean) {
        String imgUrl = RxHttp.ROOT + "attachments/" + meBean.getContent().getUser().getAvatar();
        ImgUtil.loadRound(getActivity(), imgUrl, iv_me_avatar);

        if (CheckUtil.isMobile(meBean.getContent().getUser().getNick())) {
            StringBuilder sb = new StringBuilder(meBean.getContent().getUser().getNick());
//            String username = sb.replace(2, meBean.getContent().getUser().getNick().length() - 2, "*******").toString();
            String username = sb.replace(3, meBean.getContent().getUser().getNick().length() - 4, "****").toString();

            tv_me_name.setText(username);
        } else {
            tv_me_name.setText(meBean.getContent().getUser().getNick());
        }
        if (CommUtil.isNull(tv_me_name)) {
            tv_me_name.setText(PrefUtil.getString(getActivity(), PrefKey.PHONE, ""));
        }
    }

    private void shareToWX(int WXChannel) {
        if (!MyApp.api.isWXAppInstalled()) {
            ToastUtil.showToast(getActivity(), "微信未安装");
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
//                webpage.webpageUrl = "http://www.bthai.net";
        webpage.webpageUrl = share_download_url;

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "要贷款就上去哪贷";
//        if (CommUtil.getText(tv_post_detail_content).length() >= 80) {
//            msg.description = CommUtil.getText(tv_post_detail_content).substring(0, 79);
//        } else {
        msg.description = "去哪贷-让贷款不再难,专业的贷款需求匹配平台";
//        }
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        msg.thumbData = bmpToByteArray(bmp, true);

        //构造一个req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;

//        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.scene = WXChannel;

        boolean result = MyApp.api.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void getCurrentFail(String error) {
        ToastUtil.showToast(getActivity(), error);
    }

    @Override
    public void getPersonValue(PersonBean bean) {
        tv_me_limit.setText(bean.getContent().getPersonalValue().getValuation() + "");
    }

    @Override
    public void getPersonValueFail(String error) {
        ToastUtil.showToast(getActivity(), error);

    }
}
