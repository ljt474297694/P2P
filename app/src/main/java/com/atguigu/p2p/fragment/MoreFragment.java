package com.atguigu.p2p.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.activity.LoginActivity;
import com.atguigu.p2p.utils.AppManager;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 李金桐 on 2017/3/10.
 * QQ: 474297694
 * 功能: xxxx
 */

public class MoreFragment extends BaseFragment {
    @Bind(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @Bind(R.id.tv_user_change)
    TextView tvUserChange;
    @Bind(R.id.btn_user_logout)
    Button btnUserLogout;

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData(String json, String error) {

    }

    @Override
    protected void initListener() {

    }



    @OnClick(R.id.btn_user_logout)
    public void onClick() {
        new AlertDialog.Builder(getActivity())
                .setTitle("退出登录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
                        sp.edit().clear().commit();//清除文件中的数据。

                        //移除栈中所有的activity
                        AppManager.getInstance().removeAll();
                        //重新加载首页面
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
