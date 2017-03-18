package com.atguigu.p2p.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.atguigu.p2p.R;
import com.atguigu.p2p.activity.GestureEditActivity;

import butterknife.Bind;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 李金桐 on 2017/3/10.
 * QQ: 474297694
 * 功能: xxxx
 */

public class MoreFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @Bind(R.id.toggle_more)
    ToggleButton toggleMore;
    @Bind(R.id.tv_more_reset)
    TextView tvMoreReset;
    @Bind(R.id.tv_more_phone)
    TextView tvMorePhone;
    @Bind(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @Bind(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @Bind(R.id.tv_more_share)
    TextView tvMoreShare;
    @Bind(R.id.tv_more_about)
    TextView tvMoreAbout;

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
        toggleMore.setChecked(getState());
    }

    @Override
    protected void initListener() {
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!getIsSetting()) {
                        startActivity(GestureEditActivity.class);
                    }
                }
                saveState(isChecked);
            }
        });

        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重置手势密码
                //设置手势密码
                startActivity(new Intent(getActivity(),
                        GestureEditActivity.class));
            }
        });

        tvMorePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:010-56253825");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

    }

    private boolean getIsSetting() {
        SharedPreferences sp
                = getActivity().getSharedPreferences("setting",
                Context.MODE_PRIVATE);
        return sp.getBoolean("isSetting", false);
    }

    public void setSetting(boolean setting) {
        SharedPreferences sp = getActivity().getSharedPreferences("setting",
                Context.MODE_PRIVATE);
        sp.edit().putBoolean("isSetting", setting).commit();
    }

    public void saveState(boolean isOpen) {
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isOpen", isOpen).commit();
    }

    public boolean getState() {
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        return sp.getBoolean("isOpen", false);
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://www.atguigu.com/images/logo.gif");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.baidu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("测试");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("测试");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.baidu.com");

// 启动分享GUI
        oks.show(getActivity());
    }
}
