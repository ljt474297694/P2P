package com.atguigu.p2p.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.UpdateBean;
import com.atguigu.p2p.fragment.HomeFragment;
import com.atguigu.p2p.fragment.InvestFragment;
import com.atguigu.p2p.fragment.MoreFragment;
import com.atguigu.p2p.fragment.PropertyFragment;
import com.atguigu.p2p.utils.AppNetConfig;
import com.atguigu.p2p.utils.NetUtils;
import com.atguigu.p2p.utils.ThreadPool;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_rg)
    RadioGroup mainRg;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    private Fragment tempFragment;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }
    private void checkUpdate() {

       if (isOnLine()) {
            //连网
            NetUtils.getInstance().asyncHttpPost(AppNetConfig.UPDATE, new NetUtils.resultBean<String>() {
                @Override
                public void onResponse(String context) {
                    Log.i("update", "success: "+context);
                    final UpdateBean updateBean = new Gson().fromJson(context, UpdateBean.class);
                    //判断当前的版本号
                    if (!getVersion().equals(updateBean.getVersion())){
                        //提示有新版本
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("有新版本你怎么看？")
                                .setMessage(updateBean.getDesc())
                                .setPositiveButton("不差钱", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        updateVerison(updateBean.getApkUrl());
                                    }
                                })
                                .setNegativeButton("哥很穷", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onError(String error) {
                    Log.e("TAG", "error: "+error);
                }

            });
        }else{
            //不要在动画没有执行完之前做进入主界面的动作
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    showToast("当前没有网络");
                }
            },2000);
        }
    }
    private void updateVerison(final String apkUrl) {
        //展示进度条
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        //下载
        ThreadPool.getInstance().startThread(new Runnable() {

            private FileOutputStream os;
            private InputStream inputStream;

            @Override
            public void run() {
                try {
                    //获取url地址
                    URL url = new URL(AppNetConfig.BASE_URL+"app_new.apk");
                    //打开连接
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(5000);//读取超时
                    conn.setConnectTimeout(5000);//连接超时
                    conn.setRequestMethod("GET");//请求方式
                    conn.connect();//连接网络

                    if (conn.getResponseCode() == 200){//连网成功
                        //进度条的总长度
                        progressDialog.setMax(conn.getContentLength());

                        inputStream = conn.getInputStream();

                        File path;
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            path = getExternalFilesDir("");
                        }else{
                            path = getFilesDir();
                        }
                        File file = new File(path, "update.apk");
                        os = new FileOutputStream(file);

                        byte[] bytes = new byte[1024];
                        int len;
                        /*
                        * inputStream.read(bytes) 将数据装到bytes数组里
                        * */
                        while ((len = inputStream.read(bytes))!=-1){
                            progressDialog.incrementProgressBy(len);
                            os.write(bytes,0,len);
                        }

                        //下载成功了
                        progressDialog.dismiss();
                        //安装
                        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
                        intent.setData(Uri.parse("file:" + file.getAbsolutePath()));
                        startActivity(intent);
                    }else{//连网失败
                        showToast("连网失败你丫看着办");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (os != null){
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

    }
    private String getVersion() {

        try {
            //拿到包的管理器
            PackageManager packageManager = getPackageManager();
            //拿到包的信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //versionCode每次发布新版本一定要加1
            int versionCode = packageInfo.versionCode;
            //当前的版本号
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    //判断是否有网络
    private boolean isOnLine() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
         return connected;
    }
    @Override
    protected void initData() {
        homeFragment = new HomeFragment();
        investFragment = new InvestFragment();
        moreFragment = new MoreFragment();
        propertyFragment = new PropertyFragment();
        switchFragment(homeFragment);
        checkUpdate();
    }
    @Override
    protected void initListener() {
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
