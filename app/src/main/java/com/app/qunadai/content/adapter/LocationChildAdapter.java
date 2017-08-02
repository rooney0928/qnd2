package com.app.qunadai.content.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.City;
import com.app.qunadai.third.eventbus.EventClose;
import com.app.qunadai.third.eventbus.EventLoc;
import com.app.qunadai.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/7/31.
 */

public class LocationChildAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<City> list;

    public LocationChildAdapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_child, parent, false);
        LocChildHolder holder = new LocChildHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LocChildHolder){
            LocChildHolder locChildHolder = (LocChildHolder) holder;
            locChildHolder.setData();
        }
    }

    class LocChildHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_city_content)
        TextView tv_city_content;

        public LocChildHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(){
            final City c = list.get(getAdapterPosition());
            tv_city_content.setText(c.getName());
            tv_city_content.setOnClickListener(new View.OnClickListener() {
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
