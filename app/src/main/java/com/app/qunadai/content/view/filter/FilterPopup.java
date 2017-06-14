package com.app.qunadai.content.view.filter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;

/**
 * Created by wayne on 2017/6/12.
 */

public class FilterPopup extends PopupWindow {
    private View conentView;
    private RecyclerView rv_filter;
    LinearLayoutManager linearLayoutManager;
    FilterAdapter adapter;
    private int maxItem = 4;
    private OnCompatItemClickListener listener;

    private String[] items;
    private int itemHeight;
    private int current = 0;

    public FilterPopup(Context context, String[] items) {
        super(context);
        this.items = items;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = View.inflate(context, R.layout.item_filter, null);
        conentView = inflater.inflate(R.layout.filter_content, null);
        rv_filter = (RecyclerView) conentView.findViewById(R.id.rv_filter);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);


        adapter = new FilterAdapter(context, items);
        adapter.setItemClickListener(new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (listener != null) {
                    current = position;
                    listener.onItemClick(view, position);
                }
                FilterPopup.this.dismiss();
            }
        });
        rv_filter.setLayoutManager(linearLayoutManager);
        rv_filter.setAdapter(adapter);

        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //找到item高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        itemView.measure(w, h);
        itemHeight = itemView.getMeasuredHeight();
        int width = itemView.getMeasuredWidth();

//        if (items.length >= maxItem) {
//            this.setHeight(itemHeight * maxItem);
//        } else {
//            this.setHeight(itemHeight * items.length);
//        }


        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
    }

    public void setItemClickListener(OnCompatItemClickListener listener) {
        this.listener = listener;
    }

    public void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }

    public int getCurrent() {
        return current;
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupWindow
            if (items.length >= maxItem) {
                this.setHeight(itemHeight * maxItem + maxItem - 1);
            } else {
                this.setHeight(itemHeight * items.length + maxItem - 1);
            }
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }


}
