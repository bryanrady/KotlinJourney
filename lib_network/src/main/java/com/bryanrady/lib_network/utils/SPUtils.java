package com.bryanrady.lib_network.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {

    private static SharedPreferences sp = null;

    private static void getSP(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
    }

    public static void putString(Context context,String key,String value){
        getSP(context);
        sp.edit().putString(key,value).apply();
    }

    public static String getString(Context context,String key,String defValue) {
        getSP(context);
        return sp.getString(key,defValue);
    }

    public static void putBoolean(Context context,String key, boolean value){
        getSP(context);
        sp.edit().putBoolean(key,value).apply();
    }

    public static boolean getBoolean(Context context,String key,boolean defValue) {
        getSP(context);
        return sp.getBoolean(key,defValue);
    }
}