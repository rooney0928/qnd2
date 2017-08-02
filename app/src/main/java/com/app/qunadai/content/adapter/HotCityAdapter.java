package com.app.qunadai.content.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.City;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.third.eventbus.EventLoc;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/7/28.
 */

public class HotCityAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<City> list;

    public HotCityAdapter(Context context) {
        this.context = context;
    }


    public void setList(List<City> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_grid, parent, false);
        HotHolder viewHolder = new HotHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotHolder) {
            HotHolder hotHolder = (HotHolder) holder;
            hotHolder.setData();
        }
    }

    class HotHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_city)
        TextView tv_city;

        public HotHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            final City c = list.get(getAdapterPosition());
            tv_city.setText(c.getName());
            tv_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EventLoc(c.getName()));
                    EventBus.getDefault().post(new EventClose("loc"));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
