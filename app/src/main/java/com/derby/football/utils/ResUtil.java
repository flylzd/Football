package com.derby.football.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;

public class ResUtil {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    private static void checkNotNull() {
        if (mContext == null) {
            throw new NullPointerException(
                    "Must initial call ResUtil.init(Context context) in your " +
                            "<? " +
                            "extends Application class>");
        }
    }

    public static int getColor(int resId) {
        checkNotNull();
        return mContext.getResources().getColor(resId);
    }

//    public int getColor(int colorId, Resources.Theme theme) {
//        checkNotNull();
//        return mContext.getResources().getColor(colorId, theme);
//    }

    public static String getString(int resId) {
        checkNotNull();
        return mContext.getResources().getString(resId);
    }

    public static String getString(int resId, Object... formatArgs) {
        checkNotNull();
        return mContext.getResources().getString(resId, formatArgs);
    }

    public static String[] getStringArray(int resId) {
        checkNotNull();
        return mContext.getResources().getStringArray(resId);
    }

    public static Drawable getDrawable(int resId) {
        checkNotNull();
        return mContext.getResources().getDrawable(resId);
    }

}
