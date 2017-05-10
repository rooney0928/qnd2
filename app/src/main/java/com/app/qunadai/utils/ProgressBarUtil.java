package com.app.qunadai.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by wayne on 2017/1/10.
 */

public class ProgressBarUtil {
    private static ProgressDialog dialog;

    public static void showLoadDialog(Context context) {
        if (dialog == null || !dialog.isShowing()) {
            dialog = ProgressDialog.show(context, "提示", "正在加载中");
        }
        dialog.show();
    }
    public static void showLoadDialog(Context context,String msg) {
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
}
