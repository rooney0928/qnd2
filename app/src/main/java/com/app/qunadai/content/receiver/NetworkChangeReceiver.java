package com.app.qunadai.content.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.app.qunadai.content.base.BaseActivity;
import com.app.qunadai.utils.LogU;

/**
 * Created by wayne on 2017/9/28.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetEvent netEvent = BaseActivity.netEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        LogU.t("net change");

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "当前网络可用", Toast.LENGTH_SHORT).show();
            netEvent.onNetChange(true);
        } else {
            Toast.makeText(context, "当前网络不可用", Toast.LENGTH_SHORT).show();
            netEvent.onNetChange(false);
        }

    }

    public interface NetEvent{
        void onNetChange(boolean available);
    }
}
