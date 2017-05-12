package com.app.qunadai.content.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.Space;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.utils.CommUtil;
import com.app.qunadai.utils.LogU;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class LoanProductSelectPW extends PopupWindow {
    private static final String TAG = "LoanProductSelectPW";

    private Context mContext;
    private List<String> datas = new ArrayList<>();
    private LinearLayout mainView;

    public static final int PER_COLUMN_NUM = 4;
    public static final int TYPE_LOAN_TERM = 1;//贷款期限
    public static final int TYPE_LOAN_MONEY = 2;//贷款金额
    public static final int TYPE_LOAN_PROFESSIONAL = 3;//职业
    public static final int TYPE_LOAN_SCREENING = 4;//职业

    public int selectType = 0;

    private TextView preSelectTextView;
    private LinearLayout contentView;
    private LinearLayout preSelectItem;

    private ItemTextClickListener onItemTextClickListener;
    private final ScrollView scrollview;

    public LoanProductSelectPW(Context context, List<String> datas, int selectType) {
        this.selectType = selectType;
        this.datas = datas;
        this.mContext = context;

        mainView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pw_search_product, null);
        contentView = (LinearLayout) mainView.findViewById(R.id.ll_content);
        scrollview = (ScrollView) mainView.findViewById(R.id.scrollView);

        ViewTreeObserver observer = contentView.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //contentView.getMeasuredHeight();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) scrollview.getLayoutParams();
                if (contentView.getMeasuredHeight() > CommUtil.getScreenHeight(mContext) * 2 / 5) {
                    params.height = CommUtil.getScreenHeight(mContext) * 2 / 5;
                }
                contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        //LogUtils.logD(TAG, "F");
        setContentView(mainView);
        addView();
        setWidth(CommUtil.getScreenWidth(mContext));
        setHeight(CommUtil.getScreenHeight(mContext) - CommUtil.getStatusBarHeight(mContext));
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context,R.color.gap)));

        mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void setOnItemTextClickListener(ItemTextClickListener onItemTextClickListener) {
        this.onItemTextClickListener = onItemTextClickListener;
    }

    private void addView() {
        if (datas.size() == 0) {
            this.dismiss();
            return;
        }

        for (int i = 0; i < datas.size(); i++) {
            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setPadding(CommUtil.dip2px(mContext, 15)
                    , CommUtil.dip2px(mContext, 15), 0, CommUtil.dip2px(mContext, 15));
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof LinearLayout) {
                        LinearLayout parentLayout = (LinearLayout) v;
                        TextView itemName = (TextView) parentLayout.getChildAt(1);
                        changeItemStatus(parentLayout);
                        dismiss();
                        if (onItemTextClickListener != null) {
                            onItemTextClickListener.onItemClick(itemName.getText().toString().trim(), itemName, selectType);
                        }
                    }
                }
            });

            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.mipmap.icon_filter_check);

            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setVisibility(View.INVISIBLE);


            TextView textView = new TextView(mContext);
            textView.setText(datas.get(i));
//            textView.setTextColor(mContext.getResources().getColorStateList(R.color.selector_filter_text));
            textView.setTextColor(ContextCompat.getColorStateList(mContext,R.color.selector_selected_green_gray));
            textView.setTextSize(15);

            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            textParams.leftMargin = CommUtil.dip2px(mContext, 15);
            textView.setLayoutParams(textParams);

            linearLayout.addView(imageView);
            linearLayout.addView(textView);

            contentView.addView(linearLayout);

            /*View view = new View(mContext);
            view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            view.setBackgroundColor(mContext.getResources().getColor(R.color.common_share_btn_bg));

            if (i != datas.size() - 1) {
                contentView.addView(view);
            }*/

        }

    }

    private void changeItemStatus(LinearLayout itemLayout) {
        if (preSelectItem == null) {
            changeItemStatus(itemLayout, true);
        } else if (preSelectItem != itemLayout) {
            changeItemStatus(preSelectItem, false);
            changeItemStatus(itemLayout, true);
        }
        preSelectItem = itemLayout;
    }

    private void changeItemStatus(LinearLayout itemLayout, boolean selected) {
        TextView textView = (TextView) itemLayout.getChildAt(1);
        ImageView imageView = (ImageView) itemLayout.getChildAt(0);
        textView.setSelected(selected);
        imageView.setVisibility(selected ? View.VISIBLE : View.INVISIBLE);
    }


    public String getResultText(String originalText) {
        String resultText = "";
        if (TextUtils.isEmpty(originalText)) {
            return resultText;
        }
        if (selectType == 0) {
            return resultText;
        }

        switch (selectType) {
            case TYPE_LOAN_MONEY:
                resultText = originalText.substring(0, originalText.length() - 1);
                break;
            case TYPE_LOAN_PROFESSIONAL:
                resultText = originalText;
                break;
            case TYPE_LOAN_TERM:
                if (originalText.contains("个月")) {
                    resultText = originalText.substring(0, originalText.indexOf("个月"));
                }
                break;
            case TYPE_LOAN_SCREENING:
                resultText = originalText;
                break;
        }

        LogU.d(TAG, "resultText：" + resultText);
        return resultText;
    }

    public Space getSpace() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommUtil.dip2px(mContext, 15),
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Space space = new Space(mContext);

        space.setLayoutParams(params);
        return space;
    }


    public interface ItemTextClickListener {
        void onItemClick(String text, TextView textView, int type);
    }

}
