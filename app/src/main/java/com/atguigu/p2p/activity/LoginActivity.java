package com.atguigu.p2p.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.utils.AppNetConfig;
import com.atguigu.p2p.utils.NetUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_login_number)
    TextView tvLoginNumber;
    @Bind(R.id.login_et_number)
    EditText loginEtNumber;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @Bind(R.id.login_et_pwd)
    EditText loginEtPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.login_regitster_tv)
    TextView loginRegitsterTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initTitle() {
        baseTitle.setText("登陆");
        baseBack.setVisibility(View.GONE);
        baseSetting.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验
                String phone = loginEtNumber.getText().toString().trim();
                String pw = loginEtPwd.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    showToast("账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pw)){
                    showToast("密码不能为空");
                    return;
                }
                Map<String,String> map = new HashMap<String, String>();
                map.put("phone",phone);
                map.put("password",pw);
                NetUtils.getInstance().asyncHttpPost(AppNetConfig.LOGIN, map, new NetUtils.resultBean<String>() {
                    @Override
                    public void onResponse(String o) {
                        Log.e("TAG", "LoginActivity onResponse()"+o);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e("TAG", "LoginActivity onError()");
                    }
                });


            }
        });
    }


}
