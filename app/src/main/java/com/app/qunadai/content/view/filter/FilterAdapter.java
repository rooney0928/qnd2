package com.app.qunadai.content.view.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;

/**
 * Created by wayne on 2017/6/13.
 */

public class FilterAdapter extends RecyclerView.Adapter {
    private Context context;
    private String[] list;
    private Boolean[] checkedList;
    OnCompatItemClickListener listener;



    public FilterAdapter(Context context, String[] tempList) {
        this.context = context;
        this.list = tempList;
        checkedList = new Boolean[tempList.length];
        for (int i = 0; i < checkedList.length; i++) {
            checkedList[i] = false;
        }
    }
    public void setItemClickListener(OnCompatItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(String[] tempList) {
        this.list = tempList;
        checkedList = new Boolean[tempList.length];
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false);
        FilterHolder holder = new FilterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FilterHolder){
            FilterHolder filterHolder = (FilterHolder) holder;
            filterHolder.setData();
        }
    }

    class FilterHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_filter_layout;
        ImageView iv_checked;
        TextView tv_text;

        public FilterHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            ll_filter_layout = (LinearLayout) itemView.findViewById(R.id.ll_filter_layout);
            iv_checked = (ImageView) itemView.findViewById(R.id.iv_checked);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }

        public void setData() {
            final int position = getAdapterPosition();
            if (checkedList[position]) {
                iv_checked.setVisibility(View.VISIBLE);
            }else{
                iv_checked.setVisibility(View.INVISIBLE);
            }

            tv_text.setText(list[position]);
            ll_filter_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeChecked(getAdapterPosition());
                    listener.onItemClick(v,position);
                }
            });
        }
    }

    public void changeChecked(int position) {
        for (int i = 0; i < checkedList.length; i++) {
            //check current item
            checkedList[i] = (i == position);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.length;
    }
}
