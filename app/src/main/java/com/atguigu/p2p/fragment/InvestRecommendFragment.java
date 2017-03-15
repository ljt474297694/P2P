package com.atguigu.p2p.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.utils.UiUtils;
import com.atguigu.p2p.view.randomLayout.StellarMap;

import java.util.Random;

import butterknife.Bind;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public class InvestRecommendFragment extends BaseFragment {
    @Bind(R.id.stellarmap)
    StellarMap stellarmap;
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };
    private String[] oneDatas = new String[datas.length / 2];
    private String[] twoDatas = new String[datas.length - datas.length / 2];
    private Random random = new Random();

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_invest_recommend;
    }

    @Override
    protected void initData(String json, String error) {

        for (int i = 0; i < datas.length; i++) {
            //第一个组
            if (i < datas.length / 2) {
                oneDatas[i] = datas[i];
            } else {
                //第二个组
                twoDatas[i - datas.length / 2] = datas[i];
            }
        }
        stellarmap.setAdapter(new RecommendAdapter());
        //必须调用如下的两个方法，否则stellarMap不能显示数据
        //设置显示的数据在x轴、y轴方向上的稀疏度
        stellarmap.setRegularity(5, 7);
        //设置初始化显示的组别，以及是否需要使用动画
        stellarmap.setGroup(0, true);
        stellarmap.setInnerPadding(UiUtils.dp2px(10), UiUtils.dp2px(10),
                UiUtils.dp2px(10), UiUtils.dp2px(10));

    }

    @Override
    protected void initListener() {

    }

    class RecommendAdapter implements StellarMap.Adapter {

        /*
        * 有几个组
        * */
        @Override
        public int getGroupCount() {
            return 2;
        }

        /*
        * 每组有多少数量  根据不同的组返回不同的数量
        * */
        @Override
        public int getCount(int group) {
            return group == 0 ? datas.length / 2 : datas.length - datas.length / 2;
        }

        //返回view 根据不同的组返回不同的view
        @Override
        public View getView(int group, int position, View convertView) {
            TextView tv = new TextView(getActivity());
            if (group == 0) {
                tv.setText(oneDatas[position]);
            } else {
                tv.setText(twoDatas[position]);
            }
            int red = random.nextInt(211); //0-255 颜色值  0-211随机出来的值
            int green = random.nextInt(211); //0-255 颜色值
            int blue = random.nextInt(211); //0-255 颜色值

            tv.setTextColor(Color.rgb(red, green, blue));
            return tv;
        }

        //预留方法不用实现
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        //返回下一组的组号
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return group == 0 ? 1 : 0;
        }
    }
}
