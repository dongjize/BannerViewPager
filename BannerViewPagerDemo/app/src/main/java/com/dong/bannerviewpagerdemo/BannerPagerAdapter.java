package com.dong.bannerviewpagerdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: dong
 * Date: 16/5/4
 */
public class BannerPagerAdapter extends PagerAdapter {
    private LayoutInflater mInflater;
    private List<ImageView> viewList;
    private OnBannerViewClickListener mListener;
    private List<BannerItem> bannerItemList;
    private BannerViewPager bannerViewPager;
    private ViewPager viewPager;
    private PointGroup pointGroup;

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public BannerPagerAdapter(Context context, BannerViewPager pager, List<ImageView> views, List<BannerItem> items) {
        mInflater = LayoutInflater.from(context);
        this.viewList = views;
        this.bannerItemList = items;
        this.bannerViewPager = pager;
        this.viewPager = bannerViewPager.getViewPager();

        this.pointGroup = new PointGroup(context, bannerItemList.size());
        this.bannerViewPager.setPointGroup(pointGroup);
        this.bannerViewPager.setOnBannerPageChangeListener();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (viewPager.getCurrentItem() == bannerItemList.size() - 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                handler.sendEmptyMessageDelayed(0, bannerViewPager.getInterval());
            }
        };

    }

    public void setOnBannerViewClickListener(OnBannerViewClickListener listener) {
        this.mListener = listener;
    }

    public interface OnBannerViewClickListener {
        void onBannerClick(View itemView, int position);
    }

    @Override
    public int getCount() {
        return bannerItemList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View view = mInflater.inflate(R.layout.item_viewpager, null);
        RelativeLayout picLayout = (RelativeLayout) view.findViewById(R.id.picture_layout);
        ImageView imageView = viewList.get(position);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ViewGroup parent = (ViewGroup) imageView.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        picLayout.addView(imageView);

        BannerItem item = bannerItemList.get(position);
        if (!TextUtils.isEmpty(item.getIntro())) {
            TextView textView = (TextView) view.findViewById(R.id.tv_intro);
            textView.setText(item.getIntro());
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onBannerClick(view, position);
                }
            }
        });
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(viewList.get(position));
    }
}
