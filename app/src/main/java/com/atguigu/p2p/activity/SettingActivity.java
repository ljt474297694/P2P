package com.atguigu.p2p.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.utils.AppManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @Bind(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @Bind(R.id.tv_user_change)
    TextView tvUserChange;
    @Bind(R.id.btn_user_logout)
    Button btnUserLogout;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_seeting;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
        ivUserIcon.setImageBitmap((Bitmap)getIntent().getParcelableExtra("image"));

    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_user_logout)
    public void onClick() {
        new AlertDialog.Builder(this)
                .setTitle("退出登录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
                        sp.edit().clear().commit();//清除文件中的数据。

                        //移除栈中所有的activity
                        AppManager.getInstance().removeAll();
                        //重新加载首页面
                        startActivity(LoginActivity.class);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
