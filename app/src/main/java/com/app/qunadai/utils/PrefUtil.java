package com.app.qunadai.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 专门访问和设置SharePreference的工具类, 保存和配置一些设置信息
 */
public class PrefUtil {

	private static final String SHARE_PREFS_NAME = "config";

	public static void putBoolean(Context ctx, String key, boolean value) {
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		pref.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		return pref.getBoolean(key, defaultValue);
	}

	public static void putString(Context ctx, String key, String value) {
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		pref.edit().putString(key, value).commit();
	}

	public static String getString(Context ctx, String key, String defaultValue) {
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		return pref.getString(key, defaultValue);
	}

	public static void putInt(Context ctx, String key, int value) {
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		pref.edit().putInt(key, value).commit();
	}

	public static int getInt(Context ctx, String key, int defaultValue) {
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);

		return pref.getInt(key, defaultValue);
	}

	public static void removeItem(Context ctx, String key){
		SharedPreferences pref = ctx.getSharedPreferences(SHARE_PREFS_NAME,
				Context.MODE_PRIVATE);
		pref.edit().remove(key);
	}


}
