package com.atguigu.p2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.p2p.view.LoadingPage;

import butterknife.ButterKnife;

/**
 * Created by 李金桐 on 2017/3/13.
 * QQ: 474297694
 * 功能: xxxx
 */

public  abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(getActivity()) {
            @Override
            public String setUrl() {
                return BaseFragment.this.setUrl();
            }

            @Override
            public int setLayoutId() {
                return setLayout();
            }

            @Override
            protected void onSuccess(String json, View sucessView) {
                ButterKnife.bind(BaseFragment.this,sucessView);
                initData(json);
            }
        };
    return loadingPage;
}

    protected abstract String setUrl() ;

    protected abstract int setLayout();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingPage.loadData();
        initListener();
    }

    protected abstract void initData(String json);

    protected abstract void initListener();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
