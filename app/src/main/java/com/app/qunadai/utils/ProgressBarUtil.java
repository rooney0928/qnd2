package com.app.qunadai.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wayne on 2017/1/10.
 */

public class ProgressBarUtil {
    private static ProgressDialog dialog;

    public static void showLoadDialog(Context context) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = ProgressDialog.show(context, "", "正在加载中");
        }
        dialog.show();
    }

    public static void showLoadDialog(Context context, String title, String msg) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = ProgressDialog.show(context, title, msg);
        }
        dialog.show();
    }

    public static void showLoadDialog(Context context, String msg) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = ProgressDialog.show(context, "提示", msg);
        }
        dialog.show();
    }


    public static void hideLoadDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static void hideLoadDialogDelay(final Activity activity) {
        Observable.timer(400, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ProgressBarUtil.hideLoadDialog();
                            }
                        });
                    }
                }
        );

    }
}
