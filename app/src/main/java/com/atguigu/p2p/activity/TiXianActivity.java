package com.atguigu.p2p.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2p.R;

import butterknife.Bind;

public class TiXianActivity extends BaseActivity {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.account_zhifubao)
    TextView accountZhifubao;
    @Bind(R.id.select_bank)
    RelativeLayout selectBank;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.et_input_money)
    EditText etInputMoney;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @Override
    protected void initTitle() {

        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        baseTitle.setText("提现");

        baseSetting.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String money = s.toString().trim();
                if (TextUtils.isEmpty(money)){
                    btnTixian.setClickable(false);
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                }else{
                    btnTixian.setClickable(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });

        btnTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean aBoolean = getSharedPreferences("tog_state", Context.MODE_PRIVATE).getBoolean("isOpen", false);
                
                if(aBoolean) {
                    startActivityForResult(new Intent(TiXianActivity.this,GestureVerifyActivity.class),0);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            boolean msg = data.getBooleanExtra("msg", false);
            if(msg) {
                Toast.makeText(TiXianActivity.this, "提现申请成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(TiXianActivity.this, "提现申请失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
