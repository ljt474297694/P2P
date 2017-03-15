package com.atguigu.p2p.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.atguigu.p2p.utils.AppManager;

import butterknife.ButterKnife;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);
        initTitle();
        initData();
        initListener();
    }

    protected abstract @LayoutRes int setLayoutId();

    protected abstract void initTitle();

    protected abstract void initData();
    protected abstract void initListener();

    protected void startActivity(Class activityClass){
        startActivity(new Intent(this,activityClass));
    }

    protected void showToast(String content){
        Toast.makeText(BaseActivity.this, content, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
        ButterKnife.unbind(this);
    }





}
