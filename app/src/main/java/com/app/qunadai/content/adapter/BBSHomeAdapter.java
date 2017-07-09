package com.app.qunadai.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import com.app.qunadai.utils.RelativeDateFormat;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/5/23.
 */

public class BBSHomeAdapter extends RecyclerView.Adapter {
    private Context context;

    private List<Post> list;

    public BBSHomeAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_bbs_home, parent, false);
        PostHolder viewHolder = new PostHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PostHolder) {
            PostHolder postHolder = (PostHolder) holder;
            postHolder.setData();
        }
    }


    class PostHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_post_item)
        LinearLayout ll_post_item;
        @BindView(R.id.iv_post_album)
        ImageView iv_post_album;
        @BindView(R.id.tv_post_title)
        TextView tv_post_title;
        @BindView(R.id.iv_post_avatar)
        ImageView iv_post_avatar;
        @BindView(R.id.tv_post_username)
        TextView tv_post_username;
        @BindView(R.id.tv_post_time)
        TextView tv_post_time;
        @BindView(R.id.tv_post_view)
        TextView tv_post_view;


        public PostHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            final Post p = list.get(getAdapterPosition());
            String imgUrl = RxHttp.ROOT + "attachments/" + p.getUserAvatar();
            ImgUtil.loadImgAvatar(context, imgUrl, iv_post_avatar);

            tv_post_title.setText(p.getTitle());
            tv_post_username.setText("去哪贷小蜜");
            tv_post_time.setText(RelativeDateFormat.format(new Date(p.getCreatedTime())));
            tv_post_view.setText("" + p.getBrowseAmount());

            if (p.getPictures().size() > 0) {
//                iv_post_album.
                String albumUrl = RxHttp.ROOT + "articlePics/"+p.getPictures().get(0);
                ImgUtil.loadImg(context,albumUrl, iv_post_album);
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


