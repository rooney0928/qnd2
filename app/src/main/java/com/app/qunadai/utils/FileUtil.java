package com.app.qunadai.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wayne on 2017/5/15.
 */

public class FileUtil {


    private static final String LOCAL_TEMP = "qnd";

    public static String getSDPath(Context context) {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        } else {
            sdDir = Environment.getDataDirectory();
            if (sdDir == null) {
                sdDir = context.getFilesDir();
            }
        }
        return sdDir != null ? sdDir.getAbsolutePath() : "";

    }

    public static File getImageDir(Context context) {
        File dir = new File(getSDPath(context) + File.separator + LOCAL_TEMP);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static String getRandomImgFilename() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str + ".jpg";
    }
}
