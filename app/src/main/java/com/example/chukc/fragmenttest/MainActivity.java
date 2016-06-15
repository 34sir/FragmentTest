package com.example.chukc.fragmenttest;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragmentArray;
    private TabFragmentPagerAdapter mFragmentPagerAdapter;
    private TextView tab1, tab2;// 页卡头标
    private List<View> listViews; // Tab页面列表
    private View view_tab1, view_tab2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        InitViewPager();
    }
    /**
     * 初始化头标
     */
    private void InitView() {
        tab1 = (TextView) (findViewById(R.id.rel_tab1).findViewById(R.id.tab1));
        tab2 = (TextView) (findViewById(R.id.rel_tab2).findViewById(R.id.tab2));
        view_tab1 = findViewById(R.id.rel_tab1).findViewById(R.id.view_tab1);
        view_tab2 = findViewById(R.id.rel_tab2).findViewById(R.id.view_tab2);

        listViews = new ArrayList<View>();
        listViews.add(tab1);
        listViews.add(tab2);
        tab1.setOnClickListener(new MyOnClickListener(0));
        tab2.setOnClickListener(new MyOnClickListener(1));
    }

    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewpager.setCurrentItem(index);
        }
    }

    /**
     * 初始化ViewPager
     */
    private void InitViewPager() {
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        fragmentArray = new ArrayList<Fragment>();
        fragmentArray.add(fragmentA);
        fragmentArray.add(fragmentB);
        FragmentManager mFragmentManager = ((FragmentActivity) this).getSupportFragmentManager();
        mFragmentPagerAdapter = new TabFragmentPagerAdapter(mFragmentManager, fragmentArray);
        viewpager.setAdapter(mFragmentPagerAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            tabChange(arg0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * 切换页面tab ui
     * @param tab
     */
    public void tabChange(int tab) {
        switch (tab) {
            case 0:
                tab1.setTextColor(Color.parseColor("#15a9bc"));
                tab2.setTextColor(Color.parseColor("#000000"));
                view_tab1.setVisibility(View.VISIBLE);
                view_tab2.setVisibility(View.GONE);
                break;
            case 1:
                tab2.setTextColor(Color.parseColor("#15a9bc"));
                tab1.setTextColor(Color.parseColor("#000000"));
                view_tab2.setVisibility(View.VISIBLE);
                view_tab1.setVisibility(View.GONE);
                break;
            default:
                break;
        }

    }



    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragmentList = null;

        public TabFragmentPagerAdapter(android.support.v4.app.FragmentManager mFragmentManager, ArrayList<Fragment> fragmentList) {
            super(mFragmentManager);
            mFragmentList = fragmentList;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            if (position < mFragmentList.size()) {
                fragment = mFragmentList.get(position);
            } else {
                fragment = mFragmentList.get(0);
            }
            return fragment;

        }
    }

}
