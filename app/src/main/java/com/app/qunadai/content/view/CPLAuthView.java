package com.app.qunadai.content.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.utils.LogU;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/10.
 */

public class CPLAuthView extends RelativeLayout {

    @BindView(R.id.iv_auth_logo)
    ImageView iv_auth_logo;
    @BindView(R.id.tv_auth_name)
    TextView tv_auth_name;
    @BindView(R.id.tv_auth_unchecked)
    TextView tv_auth_unchecked;

    private int logoId;
    private String name;
    //    private String desc;
    private int status;

    public static final int AUTH_NO = 0;
    public static final int AUTH_YES = 1;

    public CPLAuthView(Context context) {
        super(context);
    }

    public CPLAuthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public CPLAuthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.item_auth_cpl, this);
        ButterKnife.bind(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CPLAuthView);
        logoId = ta.getResourceId(R.styleable.CPLAuthView_cpl_logo, -1);
        name = ta.getString(R.styleable.CPLAuthView_cpl_name);
//        desc = ta.getString(R.styleable.authView_mDesc);
        status = ta.getInteger(R.styleable.CPLAuthView_cpl_checked, -1);
        LogU.t("st__" + status);
        if (status == 0 || status == 1) {
            if (logoId != -1) {
                iv_auth_logo.setImageResource(logoId);
            }

            tv_auth_name.setText(name);
//        tv_auth_desc.setText(desc);

            setAuthStatus(status);
        } else {
            throw new IllegalStateException("the authStatus is only 0,1");
        }

    }

    public void setAuthStatus(int status) {
        this.status = status;
        if (status != 0 && status != 1) {
            throw new IllegalStateException("the authStatus is only 0,1");
        }

        switch (status) {
            case AUTH_NO:
                tv_auth_unchecked.setVisibility(GONE);
                break;
            case AUTH_YES:
                tv_auth_unchecked.setVisibility(VISIBLE);
                break;
            default:
                tv_auth_unchecked.setVisibility(GONE);
                break;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        postInvalidate();
    }
}
