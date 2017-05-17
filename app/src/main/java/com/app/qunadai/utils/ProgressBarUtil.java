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

    public static void showLoadDialog(final Activity activity, String title, final String msg) {
        Observable.timer(10, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dialog == null || !dialog.isShowing()) {
                                    dialog = ProgressDialog.show(activity, "提示", msg);
                                }
                                dialog.show();
                            }
                        });
                    }
                }
        );
    }

    public static void showLoadDialog(final Activity activity, final String msg) {

        Observable.timer(10, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (dialog == null || !dialog.isShowing()) {
                                    dialog = ProgressDialog.show(activity, "提示", msg);
                                }
                                dialog.show();
                            }
                        });
                    }
                }
        );

    }


    public static void hideLoadDialogDelay(final Activity activity) {
        Observable.timer(200, TimeUnit.MILLISECONDS).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (activity != null && !activity.isFinishing()) {
                                    if (dialog != null) {
                                        dialog.dismiss();
                                    }
                                }
                            }
                        });
                    }
                }
        );

    }
}
