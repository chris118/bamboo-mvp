package com.hh.bamboobase.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DensityUtils
{
    public static int dp2px(Context context, float dpValue)
    {
        test(context);
        float density = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2dp(Context context, float pxValue)
    {
        test(context);
        float density = getResources(context).getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    public static int getScreenWidth(Context context)
    {
        test(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(context, Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context)
    {
        test(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService(context, Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int sp2px(Context context, float spValue)
    {
        test(context);
        final float fontScale = getResources(context).getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getActionBarHeight(Context context){
        TypedArray actionbarSizeTypeArray = getApplication(context).
                obtainStyledAttributes(new int []{android.R.attr.actionBarSize});
        return (int)actionbarSizeTypeArray.getDimension(0,0);
    }

    private static Object getSystemService(Context context, String name){
        test(context);
        return getApplication(context).getSystemService(name);
    }

    private static Context getApplication(Context context){
        return context.getApplicationContext();
    }

    private static Resources getResources(Context context){
        return getApplication(context).getResources();
    }

    private static void test(Context context){
        if(context == null){
            throw new RuntimeException("Context is null");
        }
    }
}
