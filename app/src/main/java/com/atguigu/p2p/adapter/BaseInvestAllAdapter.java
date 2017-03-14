package com.atguigu.p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public abstract class BaseInvestAllAdapter<Bean> extends BaseAdapter {

    private List<Bean> list = new ArrayList<>();

    public BaseInvestAllAdapter(List<Bean> list) {
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = initView();
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Bean dataBean = list.get(position);

        setData(dataBean,convertView);
        return convertView;
    }

    protected abstract void setData(Bean dataBean, View convertView);

    protected abstract View initView() ;


    class ViewHolder {

        ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }
}