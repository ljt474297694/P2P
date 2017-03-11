package com.atguigu.p2p.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 李金桐 on 2017/3/10.
 * QQ: 474297694
 * 功能: NetUtils 简单封装
 */

public class NetUtils {

    private NetUtils() {
    }

    static class Tool {
        private static NetUtils okhttpUtils = new NetUtils();
    }

    public static NetUtils getInstance() {
        return Tool.okhttpUtils;
    }

    /**
     * @param url    url
     * @param clazz  clazz必须和result的泛型保持一致 否则会抛类型转换的异常
     * @param result resultBean 回调bean的接口
     */
    public void okhttpUtilsget(final String url, final Class clazz, final resultBean result) {
        if (TextUtils.isEmpty(url)) throw new RuntimeException("url为空 无法请求");
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                result.onError(call, e, id);
            }

            @Override
            public void onResponse(String response, int id) {
                if (TextUtils.isEmpty(url)) throw new RuntimeException("结果为空 无法解析");

                result.onResponse(new Gson().fromJson(response, clazz));
            }
        });
    }


    public interface resultBean<Bean> {
        void onResponse(Bean bean);
        void onError(Call call, Exception e, int id);
    }

}
