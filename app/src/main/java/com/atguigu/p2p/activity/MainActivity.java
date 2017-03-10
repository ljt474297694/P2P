package com.atguigu.p2p.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.fragment.HomeFragment;
import com.atguigu.p2p.fragment.InvestFragment;
import com.atguigu.p2p.fragment.MoreFragment;
import com.atguigu.p2p.fragment.PropertyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_rg)
    RadioGroup mainRg;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        moreFragment = new MoreFragment();
        propertyFragment = new PropertyFragment();
        switchFragment(homeFragment);
    }

    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main:
                        switchFragment(homeFragment);
                        break;
                    case R.id.rb_invest:
                        switchFragment(investFragment);
                        break;
                    case R.id.rb_more:
                        switchFragment(moreFragment);
                        break;
                    case R.id.rb_propert:
                        switchFragment(propertyFragment);
                        break;
                }
            }
        });
    }

    //切换Fragment方法
    private void switchFragment(Fragment f) {
        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (f.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(f);
            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.main_fl, f);
            }
            ft.commit();
            tempFragment = f;
        }
    }

    private long time;

    //双击Back 退出应用
    @Override
    public void onBackPressed() {
        //如果和上次点击间隔小于2秒 finish
        if (System.currentTimeMillis() - time < 2000)   finish();
        time = System.currentTimeMillis();
        Toast.makeText(MainActivity.this, "双击退出应用", Toast.LENGTH_SHORT).show();
    }
}
