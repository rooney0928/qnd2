package com.app.qunadai.content.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.utils.ImgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/24.
 */

public class PostImgAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> images;
    private OnCompatItemClickListener itemClickListener;
    private boolean canDelete;

    public PostImgAdapter(Context context, OnCompatItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public void setImages(List<String> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_img, parent, false);
        ImgHolder imgHolder = new ImgHolder(view);
        return imgHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImgHolder) {
            ImgHolder imgHolder = (ImgHolder) holder;
            imgHolder.setData();
        }
    }

    class ImgHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_post_img)
        ImageView iv_post_img;
        @BindView(R.id.iv_post_delete)
        ImageView iv_post_delete;

        public ImgHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void setData() {
            String path = images.get(getAdapterPosition());
            ImgUtil.loadImg(context, path, iv_post_img);
            if (canDelete) {
                iv_post_delete.setVisibility(View.VISIBLE);
                iv_post_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        images.remove(getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
            } else {
                iv_post_delete.setVisibility(View.GONE);
            }

            iv_post_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();

    }
}
