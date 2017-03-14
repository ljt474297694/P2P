package com.atguigu.p2p.viewholder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public abstract class BaseHolder<Bean> {
    protected View rootView;

    public Bean getData() {
        return data;
    }

    protected Bean data;

    public BaseHolder() {
        rootView = initView();
        ButterKnife.bind(this,rootView);
        rootView.setTag(this);
    }

    public  View getView() {
      return  rootView;
    }

    protected abstract View initView();

    public void setData(Bean dataBean){
        this.data = dataBean;
        bindViewData();
    }
    protected abstract void bindViewData();

}
