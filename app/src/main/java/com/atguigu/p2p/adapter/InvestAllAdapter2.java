package com.atguigu.p2p.adapter;

import com.atguigu.p2p.bean.InvestAllBean;
import com.atguigu.p2p.viewholder.BaseHolder;
import com.atguigu.p2p.viewholder.InvestViewHolder;

import java.util.List;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public class InvestAllAdapter2 extends BaseInvestAllAdapter1<InvestAllBean.DataBean> {

    public InvestAllAdapter2(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    public BaseHolder setHolder() {
        return new InvestViewHolder();
    }
}