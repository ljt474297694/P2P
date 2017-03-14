package com.atguigu.p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.p2p.viewholder.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public abstract class MyBaseViewAdapter<Bean> extends BaseAdapter {

    private List<Bean> list = new ArrayList<>();

    public MyBaseViewAdapter(List<Bean> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = setHolder();
        }else{
            viewHolder = (BaseHolder) convertView.getTag();
        }

        Bean dataBean = list.get(position);

        viewHolder.setData(dataBean);
        return viewHolder.getView();
    }

    public abstract BaseHolder setHolder();


}