package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.bbs.Post;
import com.app.qunadai.content.ui.bbs.PostDetailActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;
import com.app.qunadai.utils.LogU;
import com.app.qunadai.utils.RelativeDateFormat;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/6/5.
 */

public class PostAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Post> list;

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Post> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_post_title)
        TextView tv_post_title;
        @BindView(R.id.ll_post_item)
        LinearLayout ll_post_item;
        @BindView(R.id.iv_post_avatar)
        ImageView iv_post_avatar;
        @BindView(R.id.tv_post_username)
        TextView tv_post_username;
        @BindView(R.id.tv_post_time)
        TextView tv_post_time;
        @BindView(R.id.tv_post_view)
        TextView tv_post_view;
        @BindView(R.id.iv_post_img)
        ImageView iv_post_img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {

            final Post p = list.get(getAdapterPosition());

            String imgUrl = RxHttp.ROOT + "attachments/" + p.getUserAvatar();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_post_avatar);
            tv_post_username.setText(p.getUserNick());
            tv_post_time.setText(RelativeDateFormat.format(new Date(p.getCreatedTime())));
            tv_post_view.setText("" + p.getBrowseAmount());

            if (p.getPictures().size() > 0) {
                tv_post_title.setText("\u3000 " + p.getTitle());
                iv_post_img.setVisibility(View.VISIBLE);
            } else {
                tv_post_title.setText(p.getTitle());
                iv_post_img.setVisibility(View.GONE);
            }


            ll_post_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentDetail = new Intent(context, PostDetailActivity.class);
                    intentDetail.putExtra("post", p);
                    context.startActivity(intentDetail);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
