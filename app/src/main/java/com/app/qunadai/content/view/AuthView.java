package com.app.qunadai.content.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/10.
 */

public class AuthView extends RelativeLayout {

    @BindView(R.id.iv_auth_logo)
    ImageView iv_auth_logo;
    @BindView(R.id.tv_auth_name)
    TextView tv_auth_name;
    @BindView(R.id.iv_auth_status)
    ImageView iv_auth_status;

    private int logoId;
    private String name;
//    private String desc;
    private int status;

    public static final int AUTH_ERROR = -1;
    public static final int AUTH_NO = 0;
    public static final int AUTH_YES = 1;
    public static final int AUTH_ING = 2;// 认证中

    public AuthView(Context context) {
        super(context);
    }

    public AuthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }



    public AuthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.item_auth, this);
        ButterKnife.bind(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.authView);
        logoId = ta.getResourceId(R.styleable.authView_mLogo, -1);
        name = ta.getString(R.styleable.authView_mName);
//        desc = ta.getString(R.styleable.authView_mDesc);
        status = ta.getInteger(R.styleable.authView_mStatus, -1);

        if (status < 0 || status > 2) {
            throw new IllegalStateException("the authStatus is only 0,1,2");
        }
        if (logoId != -1) {
            iv_auth_logo.setImageResource(logoId);
        }

        tv_auth_name.setText(name);
//        tv_auth_desc.setText(desc);

        setAuthStatus(status);
    }

    public void setAuthStatus(int status) {
        this.status = status;
        if (status < 0 || status > 2) {
            throw new IllegalStateException("the authStatus is only 0,1,2");
        }

        switch (status) {
            case AUTH_NO:
                iv_auth_status.setImageResource(R.mipmap.icon_unauth);
                break;
            case AUTH_YES:
                iv_auth_status.setImageResource(R.mipmap.icon_authed);
                break;
            case AUTH_ING:
                iv_auth_status.setImageResource(R.mipmap.icon_authing);
                break;
            default:
                iv_auth_status.setImageResource(R.mipmap.icon_unauth);
                break;
        }
    }

    public int getStatus() {
        return status;
    }
}
