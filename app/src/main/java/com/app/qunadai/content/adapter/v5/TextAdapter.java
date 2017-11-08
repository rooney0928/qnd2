package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/11/8.
 */

public class TextAdapter extends RecyclerView.Adapter {
    private Context context;

    private String[] texts;
    private OnCompatItemClickListener listener;

    public TextAdapter(Context context) {
        this.context = context;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public void setOnItemClickListener(OnCompatItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        TextHolder holder = new TextHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TextHolder){
            TextHolder textHolder = (TextHolder) holder;
            textHolder.setData();
        }
    }

    class TextHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tv_item;

        public TextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            String t = texts[getAdapterPosition()];
            tv_item.setText(t);
            tv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getAdapterPosition());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return texts == null ? 0 : texts.length;
    }
}
