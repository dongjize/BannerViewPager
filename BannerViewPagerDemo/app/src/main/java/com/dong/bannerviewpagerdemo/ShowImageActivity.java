package com.dong.bannerviewpagerdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dong.bannerviewpagerdemo.imageview.ZoomImagePagerAdapter;

/**
 * Description:
 * <p>
 * Author: dong
 * Date: 16/5/7
 */
public class ShowImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ZoomImagePagerAdapter mAdapter;
    private int index;

    private int[] images = {R.mipmap.sh_house_njxlgg02, R.mipmap.sh_house_njxlgg03,
            R.mipmap.sh_house_wdtxy01, R.mipmap.sh_house_wdtxy02, R.mipmap.sh_house_wdtxy04,
            R.mipmap.sh_jwthy01};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        index = getIntent().getIntExtra("index", 0);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (mAdapter == null) {
            mAdapter = new ZoomImagePagerAdapter(ShowImageActivity.this, images);
        }
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(index);
    }
}
