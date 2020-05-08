package com.bw.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SPUtils {

    public static final String NAME = "userinfo";
    public static final String USERID = "userId";
    public static final String SESSIONID = "sessionId";

    public static void putString(Context context,String name,String key,String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public static String getString(Context context,String name,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, "");
        return string;
    }
    public static void putBoolen(Context context,String name,String key,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
    public static Boolean getBoolean(Context context,String name,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        boolean aBoolean = sharedPreferences.getBoolean(key, false);
        return aBoolean;
    }


}
