package com.atguigu.p2p.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.atguigu.p2p.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 李金桐 on 2017/3/13.
 * QQ: 474297694
 * 功能: LoadingPager
 */

public abstract class LoadingPager extends FrameLayout {

    private Context mContext;

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View sucessView;

    private int STATE_LOADING = 1; //加载中
    private int STATE_ERROR = 2; //加载失败
    private int STATE_SUCCESS = 3; //加载成功
    private int STATE_EMPTY = 4; //空
    private int current_state = STATE_LOADING;

    private LayoutParams params;

    public LoadingPager(Context context) {
        this(context, null);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }
    //初始化各个布局
    private void init() {

        //设置全屏属性
        params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        loadingView = View.inflate(mContext, R.layout.page_loading, null);
        addView(loadingView, params);

        emptyView = View.inflate(mContext, R.layout.page_empty, null);
        addView(emptyView, params);

        errorView = View.inflate(mContext, R.layout.page_error, null);
        addView(errorView, params);

        sucessView = View.inflate(mContext, setLayoutId(), null);
        addView(sucessView, params);

        //根据状态切换页面 默认是loading
        showStateView();
    }

    //根据状态切换页面
    private void showStateView() {
        //是否展示错误页面
        errorView.setVisibility(
                current_state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        //是否展示加载界面
        loadingView.setVisibility(
                current_state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        //是否展示空页面
        emptyView.setVisibility(
                current_state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        //是否展示成功页面
        sucessView.setVisibility(
                current_state == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }
    //加载数据回调给实现类
    public void loadData() {
        OkHttpUtils.post().url(setUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                current_state = STATE_ERROR;
                onSuccess("",e.getMessage(), sucessView);
                showStateView();
            }

            @Override
            public void onResponse(String response, int id) {
                if(TextUtils.isEmpty(response)) {
                    current_state = STATE_EMPTY;
                    onSuccess("","请求结果为空", sucessView);
                }else{
                    current_state = STATE_SUCCESS;
                    onSuccess(response,"请求成功", sucessView);
                }
                showStateView();
            }
        });
    }

    protected abstract String setUrl();


    protected abstract int setLayoutId();


    protected abstract void onSuccess(String json,String error, View sucessView);

}
