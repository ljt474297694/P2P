package com.atguigu.p2p.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
    public void okhttpUtilsget( String url, final Class clazz, final resultBean result) {
        if (result == null) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            result.onError("url为空无法请求");
            return;
        }
        if (clazz == null) {
            result.onError("字节码为空无法请求");
            return;
        }
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                result.onError(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                if (TextUtils.isEmpty(response)) {
                    result.onError("请求结果为空 无法解析");
                    return;
                }
                result.onResponse(new Gson().fromJson(response, clazz));
            }
        });
    }


    /**
     * @param url    url
     * @param clazz  clazz必须和result的泛型保持一致 否则会抛类型转换的异常
     * @param result resultBean 回调bean的接口
     */
    public void asyncHttpPost ( String url, final Class clazz, final resultBean result) {
        if (result == null) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            result.onError("url为空无法请求");
            return;
        }
        if (clazz == null) {
            result.onError("字节码为空无法请求");
            return;
        }
        new AsyncHttpClient().post(url,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                if (TextUtils.isEmpty(content)) {
                    result.onError("请求结果为空 无法解析");
                    return;
                }
                result.onResponse(new Gson().fromJson(content, clazz));
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                result.onError(content);
            }
        });
    }


    public interface resultBean<Bean> {
        void onResponse(Bean bean);

        void onError(String error);
    }

}
