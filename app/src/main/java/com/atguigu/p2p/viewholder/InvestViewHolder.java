package com.atguigu.p2p.viewholder;

import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.InvestAllBean;
import com.atguigu.p2p.utils.UiUtils;
import com.atguigu.p2p.view.MyProgressBar;

import butterknife.Bind;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public class InvestViewHolder extends BaseHolder<InvestAllBean.DataBean> {
    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    MyProgressBar pProgresss;

    @Override
    protected View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }

    @Override
    protected void bindViewData() {
        pName.setText(data.getName());
        pProgresss.setProgress(Integer.parseInt(data.getProgress()));
    }
}
