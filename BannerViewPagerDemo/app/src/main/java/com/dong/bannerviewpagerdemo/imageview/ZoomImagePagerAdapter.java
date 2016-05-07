package com.dong.bannerviewpagerdemo.imageview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Author: dong
 * Date: 16/5/7
 */
public class ZoomImagePagerAdapter extends PagerAdapter {
    private List<ZoomImageView> imageViewList;
    private Context context;
    private List<String> urlList;
    private int[] imagePaths;
    private int imageSource;
    private final int FROM_INTERNET = 0;
    private final int FROM_LOCAL = 1;

    /**
     * 网络请求构造函数
     *
     * @param context
     * @param urlList 图片的url列表
     */
    public ZoomImagePagerAdapter(Context context, List<String> urlList) {
        imageSource = FROM_INTERNET;
        this.context = context;
        this.urlList = urlList;
        imageViewList = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            imageViewList.add(new ZoomImageView(context));
        }
    }

    /**
     * 本地加载构造函数
     *
     * @param context
     * @param imagePaths 本地的路径数组
     */
    public ZoomImagePagerAdapter(Context context, int[] imagePaths) {
        imageSource = FROM_LOCAL;
        this.context = context;
        this.imagePaths = imagePaths;
        imageViewList = new ArrayList<>();
        for (int i = 0; i < imagePaths.length; i++) {
            imageViewList.add(new ZoomImageView(context));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ZoomImageView imageView = imageViewList.get(position);
        if (imageSource == FROM_INTERNET) {
            //TODO 网络请求图片方法
        } else if (imageSource == FROM_LOCAL) {
            imageView.setImageResource(imagePaths[position]);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(imageViewList.get(position));
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
