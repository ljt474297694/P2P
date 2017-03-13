package com.atguigu.p2p.utils;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.atguigu.p2p.app.Application;

/**
 * Created by 李金桐 on 2017/3/11.
 * QQ: 474297694
 * 功能: xxxx
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler() {
    }


    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.e("TAG", "CrashHandler uncaughtException()");
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(Application.getInstance(), "123", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        collection(e);
        AppManager.getInstance().removeAll();

        //结束当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //关闭虚拟机 0正常退出 其它都是非正常退出
        System.exit(0);

    }
    //处理错误信息 保存起来发送到服务器
    private void collection(Throwable e) {
        /*
         从系统属性中提取设备硬件和版本信息。
         常用属性如下：
         Build.BOARD // 主板
         Build.BRAND // android系统定制商
         Build.CPU_ABI // cpu指令集
         Build.DEVICE // 设备参数
         Build.DISPLAY // 显示屏参数
         Build.FINGERPRINT // 硬件名称
         Build.HOST
         Build.ID // 修订版本列表
         Build.MANUFACTURER // 硬件制造商
         Build.MODEL // 版本
         Build.PRODUCT // 手机制造商
         Build.TAGS // 描述build的标签
         Build.TIME
         // 当前开发代号
         Build.VERSION.CODENAME
         // 源码控制版本号
         Build.VERSION.INCREMENTAL
         // 版本字符串
         Build.VERSION.RELEASE
         // 版本号
         Build.VERSION.SDK
         // 版本号
         Build.VERSION.SDK_INT
         Build.TYPE // builder类型
         Build.USER
         */
    }
}
