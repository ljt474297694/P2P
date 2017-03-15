package com.atguigu.p2p.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.Bind;

import static com.atguigu.p2p.R.id.id_flowlayout;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public class InvestHotFragment extends BaseFragment {
    String[] datas = {"尚硅谷", "JAVA", "Android", "HTML5", "PHP", "UI", "Activity", "Fragment", "Button", "TextView", "JNI", "NDK", "手机影音", "硅谷社交", "硅谷商城", "硅谷金融", "自定义控件", "硅谷", "OKHttp", "Volley", "xUtils", "Imageloader", "Glide", "尚硅谷", "WEB基础", "混合开发", "尚硅谷"};
    @Bind(id_flowlayout)
    TagFlowLayout idFlowlayout;
    private int[] colors = {
            Color.parseColor("#59a9ff"), Color.parseColor("#6B50BC"), Color.parseColor("#7C249A"),
            Color.parseColor("#4ba5e2"), Color.parseColor("#6B50BC"), Color.parseColor("#f0a420"),
            Color.parseColor("#7C249A"), Color.parseColor("#59a9ff"), Color.parseColor("#4ba5e2")
    };
    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_invest_hot;
    }

    @Override
    protected void initData(String json, String error) {
        idFlowlayout.setAdapter(new TagAdapter<String>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView view = (TextView) View.inflate(getActivity(), R.layout.item_text, null);
                view.setText(o);
                GradientDrawable myGrad = (GradientDrawable) view.getBackground();
                myGrad.setColor(colors[position % colors.length]);
                return view;
            }
        });
        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), datas[position], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void initListener() {

    }


}
