package com.atguigu.p2p.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 李金桐 on 2017/3/11.
 * QQ: 474297694
 * 功能: xxxx
 */

public class AppManager {


    /**
     * * 统一应用程序中所有的Activity的栈管理（单例）
     * * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
     */
    private AppManager() {}

    private static AppManager appManager = new AppManager();

    public static AppManager getInstance() {
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    public void addActivity(Activity activity) {
        //校验
        if (activity != null) {
            stack.add(activity);
        }
    }

    public void removeActivity(Class clazz) {
        if (clazz != null) {
            for (int i = stack.size() - 1; i >= 0; i--) {
                if (clazz.equals(stack.get(i).getClass())) {
                    stack.get(i).finish();
                    stack.remove(i);
                }
            }

        }
    }

    public void removeActivity(Activity a) {
        if (a != null) {
            for (int i = stack.size() - 1; i >= 0; i--) {
                if (a.equals(stack.get(i))) {
                    stack.get(i).finish();
                    stack.remove(i);
                }
            }
        }
    }

    public void removeAll(){
        for (int i = stack.size() - 1; i >= 0; i--) {
                stack.get(i).finish();
                stack.remove(i);
        }
    }
    public int getStackSize() {
        return stack.size();
    }
}
