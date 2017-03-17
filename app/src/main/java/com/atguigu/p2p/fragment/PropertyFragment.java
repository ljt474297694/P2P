package com.atguigu.p2p.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.activity.BaseActivity;
import com.atguigu.p2p.activity.PayActivity;
import com.atguigu.p2p.activity.SettingActivity;
import com.atguigu.p2p.activity.TouziActivity;
import com.atguigu.p2p.activity.TouziZhiguanActivity;
import com.atguigu.p2p.activity.ZichanActivity;
import com.atguigu.p2p.utils.AppNetConfig;
import com.atguigu.p2p.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;

/**
 * Created by 李金桐 on 2017/3/10.
 * QQ: 474297694
 * 功能: xxxx
 */

public class PropertyFragment extends BaseFragment {
    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;
    private Bitmap circleBitmap;

    @Override
    protected String setUrl() {
        return null;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_property;
    }

    @Override
    protected void initData(String json, String error) {
        BaseActivity activity = (BaseActivity) getActivity();

        tvMeName.setText(activity.getUser().getData().getName());
        Picasso.with(getActivity()).load(AppNetConfig.BASE_URL + "/images/tx.png")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        circleBitmap = BitmapUtils.circleBitmap(source);
                        source.recycle();
                        return circleBitmap;
                    }

                    @Override
                    public String key() {
                        return "";
                    }
                })
//                .transform(new CropCircleTransformation())
//                .transform(new ColorFilterTransformation(Color.parseColor("#66FFccff")))
                .into(ivMeIcon);
    }

    @Override
    protected void initListener() {
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), SettingActivity.class).putExtra("image",circleBitmap),0);
            }
        });
        llTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TouziActivity.class);
            }
        });
        llTouziZhiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TouziZhiguanActivity.class);
            }
        });
        llZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ZichanActivity.class);
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(PayActivity.class);
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TiXianActivity.class);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            Bitmap bitmap = data.getParcelableExtra("image");
            if(bitmap!=null) {
                ivMeIcon.setImageBitmap(bitmap);
            }
        }
    }
}
