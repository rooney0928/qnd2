package com.app.qunadai.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.app.qunadai.R;
import com.app.qunadai.content.adapter.OnCompatItemClickListener;
import com.app.qunadai.content.adapter.v5.TextAdapter;

/**
 * Created by wayne on 2017/11/8.
 */

public class DialogUtil {

    public static void showBottomSheetDialog(final Context context, final String[] array,
                                             final OnCompatItemClickListener clickListener) {
        final BottomSheetDialog mDialog = new BottomSheetDialog(context);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater
                .from(context).inflate(R.layout.view_bottom_cpl, null);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        TextAdapter adapter = new TextAdapter(context);
        adapter.setTexts(array);
        adapter.setOnItemClickListener(new OnCompatItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clickListener.onItemClick(view,position);
                mDialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        mDialog.setContentView(recyclerView);
        mDialog.show();
    }
}
