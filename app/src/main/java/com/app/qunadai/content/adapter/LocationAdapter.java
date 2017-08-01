package com.app.qunadai.content.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.qunadai.R;
import com.app.qunadai.bean.City;
import com.app.qunadai.content.adapter.decoration.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wayne on 2017/7/31.
 */

public class LocationAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<List<City>> list;

    private String[] alpha = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public LocationAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<List<City>> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        LocHolder holder = new LocHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LocHolder) {
            LocHolder locHolder = (LocHolder) holder;
            locHolder.setData();
        }
    }

    class LocHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_title)
        TextView tv_city_title;
        @BindView(R.id.rv_city_child)
        RecyclerView rv_city_child;

        LocationChildAdapter adapter;
        LinearLayoutManager linearLayoutManager;

        public LocHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            adapter = new LocationChildAdapter(context);

            AppCompatActivity activity = (AppCompatActivity) context;
            int spacingInPixels = activity.getResources().getDimensionPixelSize(R.dimen.gap_line);
            rv_city_child.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
            rv_city_child.setLayoutManager(linearLayoutManager);
            rv_city_child.setAdapter(adapter);
        }

        public void setData() {
            tv_city_title.setText(alpha[getAdapterPosition()]);
            adapter.setList(list.get(getAdapterPosition()));
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
