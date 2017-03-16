package com.atguigu.toolbardemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;


    List<Fragment> fragments;

    List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化 👇
        initView();
        initData();
        initListener();


    }

    private void initData() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            fragments.add(new RecyclerFragment(i));
        }
        viewpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        datas = new ArrayList<>();
        for(int i = 0; i <fragments.size() ; i++) {
          datas.add("第"+(i+1)+"页");
        }
        listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas));


    }

    private void initListener() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerlayout.closeDrawers();
                viewpager.setCurrentItem(position);
            }
        });

    }

    private void initView() {
        ButterKnife.bind(this);
        toolBar.setLogo(R.mipmap.ic_launcher);
        toolBar.setNavigationIcon(android.R.drawable.ic_menu_delete);
        toolBar.setTitle("L.T");
        toolBar.setSubtitle("L.T副标题");

    }

    class PagerAdapter extends FragmentPagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return "第" + (position + 1) + "页";
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
