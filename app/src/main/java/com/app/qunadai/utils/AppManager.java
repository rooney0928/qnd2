package com.app.qunadai.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wayne on 2017/5/17.
 */

public class AppManager {
    private static List<Activity> activityList = new ArrayList<Activity>();

    public static void remove(Activity activity) {
        activityList.remove(activity);
    }

    public static void add(Activity activity) {
        activityList.add(activity);
    }

    public static void finishProgram() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void closeActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    public static void removerObj(Activity obj) {
        for (Activity activity : activityList) {
            if (activity == obj)
                activity.finish();
        }
    }

    public static void closeActivityWithout(Activity tempAct) {
        for (Activity activity : activityList) {
            if (!activity.equals(tempAct)) {
                activity.finish();
            }
        }
    }

}