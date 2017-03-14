package com.atguigu.p2p.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.p2p.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: InvestPagerAdapter
 */

public class InvestPagerAdapter extends FragmentPagerAdapter {
    private  List<BaseFragment> fragments = new ArrayList<>();
    String []  titles = {"全部理财","推荐理财","热门理财"};
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public InvestPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
