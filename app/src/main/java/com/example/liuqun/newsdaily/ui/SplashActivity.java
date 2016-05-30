package com.example.liuqun.newsdaily.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.liuqun.newsdaily.R;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager       pager;
    private ArrayList<View> list;

    int[] pics = {R.drawable.welcome, R.drawable.small, R.drawable.bd, R.drawable
            .wy};
    //四个点
    private ImageView[] points = new ImageView[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //初始化4个点
        initPoints();
        //初始化ViewPager
        initView();
    }

    private void initPoints() {
        points[0] = (ImageView) findViewById(R.id.iv_p1);
        points[1] = (ImageView) findViewById(R.id.iv_p2);
        points[2] = (ImageView) findViewById(R.id.iv_p3);
        points[3] = (ImageView) findViewById(R.id.iv_p4);
        setPoint(0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setPoint(int index) {
        for (int i = 0; i < points.length; i++) {
            if (i == index) {
                points[i].setImageAlpha(255);
            } else {
                points[i].setImageAlpha(100);
            }
        }
    }

    private void initView() {
        list = new ArrayList<>();

        pager = (ViewPager) findViewById(R.id.vp_guide);

        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(pics[i]);
            list.add(iv);
        }
        pager.setAdapter(new MyPagerAdapter(list));
        pager.addOnPageChangeListener(this);

    }

    //界面切换时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //界面切换后调用
    @Override
    public void onPageSelected(int position) {
        setPoint(position);
        if (position >= 3) {
            Intent intent = new Intent(SplashActivity.this, LogoActivity.class);
            startActivity(intent);
            finish();
        }

    }

    //滑动状态变化时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //自定义MyPagerAdapter继承PagerAdapter
    private class MyPagerAdapter extends PagerAdapter {

        private ArrayList<View> list;

        public MyPagerAdapter(ArrayList<View> list) {
            this.list = list;
        }

        //初始化position 展现到界面上来
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position), 0);
            return list.get(position);
        }

        //当不可见时,销毁position
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
