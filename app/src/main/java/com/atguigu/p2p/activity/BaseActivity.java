package com.atguigu.p2p.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 李金桐 on 2017/3/14.
 * QQ: 474297694
 * 功能: xxxx
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    protected abstract @LayoutRes int setLayoutId();

    protected abstract void initTitle();

    protected abstract void initData();

    protected void startActivity(Class activityClass){
        startActivity(new Intent(this,activityClass));
    }



}
