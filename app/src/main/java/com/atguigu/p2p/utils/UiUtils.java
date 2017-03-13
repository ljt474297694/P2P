package com.atguigu.p2p.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.p2p.app.Application;

/**
 * Created by 李金桐 on 2017/3/11.
 * QQ: 474297694
 * 功能: xxxx
 */

public class UiUtils {

    public static Context getContext() {
        return Application.getInstance();
    }

    public static View getView(int layoutid) {
        return LayoutInflater.from(getContext()).inflate(layoutid, null);
    }

    public static int getColor(int color) {
        return getContext().getResources().getColor(color);
    }

    public static String[] getStringArrary(int stringid) {
        return getContext().getResources().getStringArray(stringid);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }
}
