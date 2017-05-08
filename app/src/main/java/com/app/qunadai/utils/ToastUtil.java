package com.app.qunadai.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by wayne on 2016/12/27.
 */

public class ToastUtil {
    private static Toast toast;

    /**
     * 单例toast
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        if (!TextUtils.isEmpty(msg)) {
            LogU.t(msg);
            toast.setText(msg);
            toast.show();
        }
    }
}
