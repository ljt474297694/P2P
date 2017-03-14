package com.atguigu.p2p.fragment;

import android.widget.ListView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.adapter.InvestAllAdapter2;
import com.atguigu.p2p.bean.InvestAllBean;
import com.atguigu.p2p.utils.AppNetConfig;
import com.google.gson.Gson;

import butterknife.Bind;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public class InvestAllFragment extends BaseFragment {
    @Bind(R.id.listview)
    ListView listview;

    @Override
    protected String setUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected void initData(String json, String error) {
        InvestAllBean investAllBean = new Gson().fromJson(json, InvestAllBean.class);

//        InvestAllAdapter adapter =
//                new InvestAllAdapter(investAllBean.getData());
//        InvestAllAdapter1 adapter =
//                new InvestAllAdapter1(investAllBean.getData());
        InvestAllAdapter2 adapter =
                new InvestAllAdapter2(investAllBean.getData());

        listview.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }


}
