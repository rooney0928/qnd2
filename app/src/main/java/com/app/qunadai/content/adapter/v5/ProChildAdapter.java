package com.app.qunadai.content.adapter.v5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.qunadai.R;
import com.app.qunadai.bean.v5.RoomBean;
import com.app.qunadai.content.ui.product.Product5DetailActivity;
import com.app.qunadai.http.RxHttp;
import com.app.qunadai.utils.ImgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/9/7.
 */

public class ProChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<RoomBean> list;

    public ProChildAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<RoomBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_pro, parent, false);
        RoomHolder viewHolder = new RoomHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RoomHolder) {
            RoomHolder roomHolder = (RoomHolder) holder;
            roomHolder.setData();
        }
    }

    class RoomHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pro_pic)
        ImageView iv_pro_pic;

        public RoomHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            final RoomBean bean = list.get(getAdapterPosition());

            String imgUrl = RxHttp.ROOT + "attachments/" + bean.getContentImg();

            ImgUtil.loadImg(context, imgUrl, iv_pro_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtil.showToast(context, p.getId());
                    Intent intentDetail = new Intent(context, Product5DetailActivity.class);
                    intentDetail.putExtra("pid", bean.getContentMappingId());
                    context.startActivity(intentDetail);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() >= 3 ? 3 : list.size();
    }
}
