package com.atguigu.p2p.fragment;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.HomeBean;
import com.atguigu.p2p.utils.AppNetConfig;
import com.atguigu.p2p.view.MyProgressBar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 李金桐 on 2017/3/10.
 * QQ: 474297694
 * 功能: xxxx
 */

public class HomeFragment extends BaseFragment {
    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.my_progress)
    MyProgressBar myProgress;



    @Override
    protected void initData(String json,String error) {
        baseTitle.setText("主页");
        baseBack.setVisibility(View.GONE);
        baseSetting.setVisibility(View.GONE);
        if(TextUtils.isEmpty(json)) {
            return;
        }
        Log.e("TAG", "HomeFragment initData()"+error);
        HomeBean homeBean = new Gson().fromJson(json, HomeBean.class);
        setBannerData(homeBean);
        myProgress.setProgress(Integer.parseInt(homeBean.getProInfo().getProgress()));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected String setUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    private void setBannerData(HomeBean bean) {
        tvHomeProduct.setText(bean.getProInfo().getName());
        tvHomeYearrate.setText(bean.getProInfo().getYearRate() + "%");
        //显示圆形指示器和标题（水平显示）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置标题集合（当banner样式有显示title时）
        String[] titles = new String[]{"分享返学费1111元", "人脉总动员", "想不到你是这样的APP", "11月兑物节"};
        banner.setBannerTitles(Arrays.asList(titles));
        //设置图片集合
        List<String> images = new ArrayList<>();
        for (int i = 0; i < bean.getImageArr().size(); i++) {
            images.add(AppNetConfig.BASE_URL + bean.getImageArr().get(i).getIMAURL());
        }
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

    }


}
