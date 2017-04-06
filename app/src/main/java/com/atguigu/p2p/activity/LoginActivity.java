package com.atguigu.p2p.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.UserInfo;
import com.atguigu.p2p.utils.AppNetConfig;
import com.atguigu.p2p.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

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
                if (TextUtils.isEmpty(phone)) {
                    showToast("账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pw)) {
                    showToast("密码不能为空");
                    return;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("phone", phone);
                map.put("password", pw);
                new RetrofitUtils<LoginService>()
                        .createRetrofitServes(AppNetConfig.BASE_URL, LoginService.class)
                        .login("login", map).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<JsonObject, String>() {
                            @Override
                            public String apply(JsonObject jsonObject) throws Exception {
                                return jsonObject.toString();
                            }
                        })
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(String s) throws Exception {
                                JSONObject jsonObject = new JSONObject(s);
                                Boolean success = jsonObject.optBoolean("success");
                                if (!success)
                                    Toast.makeText(LoginActivity.this, "有问题,你检查下", Toast.LENGTH_SHORT).show();
                                return success;
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String json) throws Exception {
                                UserInfo userInfo = new Gson().fromJson(json, UserInfo.class);
                                if (userInfo != null) {
                                    saveUser(userInfo);
                                    //跳转到登录页面
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    //结束此页
                                    finish();
                                }
                            }
                        });

            }
        });
        loginRegitsterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegesterActivity.class);
            }
        });
    }

    interface LoginService {
        @FormUrlEncoded
        @POST()
        Observable<JsonObject> login(@Url String url, @FieldMap Map<String, String> map);
    }

}
