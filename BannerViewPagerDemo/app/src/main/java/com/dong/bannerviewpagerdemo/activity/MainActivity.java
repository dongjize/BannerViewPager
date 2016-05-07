package com.dong.bannerviewpagerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dong.bannerviewpagerdemo.R;
import com.dong.bannerviewpagerdemo.banner.BannerItem;
import com.dong.bannerviewpagerdemo.banner.BannerPagerAdapter;
import com.dong.bannerviewpagerdemo.banner.BannerViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BannerViewPager bannerViewPager;
    private BannerPagerAdapter mAdapter;
    private List<BannerItem> bannerItems;
    private List<ImageView> viewList;
    private int[] images = {R.mipmap.sh_house_njxlgg02, R.mipmap.sh_house_njxlgg03,
            R.mipmap.sh_house_wdtxy01, R.mipmap.sh_house_wdtxy02, R.mipmap.sh_house_wdtxy04,
            R.mipmap.sh_jwthy01};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerViewPager = (BannerViewPager) findViewById(R.id.banner_viewpager);

        bannerItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            BannerItem bannerItem = new BannerItem();
            bannerItem.setTitle("title " + i);
            bannerItem.setIntro("我是第" + i + "条banner");
            bannerItem.setPicUrl("http://pic70.nipic.com/file/20150618/21278791_104700147417_2.jpg");
            bannerItems.add(bannerItem);
        }

        viewList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            viewList.add(imageView);
        }

        mAdapter = new BannerPagerAdapter(this, bannerViewPager, bannerItems, viewList);
        mAdapter.setOnBannerViewClickListener(new BannerPagerAdapter.OnBannerViewClickListener() {
            @Override
            public void onBannerClick(View itemView, int position) {
//                Toast.makeText(MainActivity.this, bannerItems.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ShowImageActivity.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
        bannerViewPager.setAdapter(mAdapter);
    }
}
