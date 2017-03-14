package com.atguigu.p2p.adapter;

import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.InvestAllBean;
import com.atguigu.p2p.utils.UiUtils;

import java.util.List;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public class InvestAllAdapter1 extends BaseInvestAllAdapter<InvestAllBean.DataBean> {

    public InvestAllAdapter1(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    protected void setData(InvestAllBean.DataBean dataBean, View convertView) {
        TextView textVeiw = (TextView) convertView.findViewById(R.id.p_name);
        textVeiw.setText(dataBean.getName());
    }

    @Override
    protected View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }
}