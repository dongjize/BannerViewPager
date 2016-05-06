package com.dong.bannerviewpagerdemo;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Description:
 * <p>
 * Author: dong
 * Date: 16/5/5
 */
public class BannerViewPager extends RelativeLayout {
    private ViewPager viewPager;
    private LinearLayout pointsLayout;
    private PointGroup mPointGroup;
    private OnBannerPageChangeListener mListener;
    private BannerViewPagerScroller mScroller;


    public BannerViewPager(Context context) {
        super(context);
        init(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_banner_viewpager, this, true);
        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        pointsLayout = (LinearLayout) this.findViewById(R.id.points_layout);

    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setAdapter(BannerPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
        Handler handler = adapter.getHandler();
        handler.sendEmptyMessageDelayed(0, 4000);
    }

    public void setPointGroup(PointGroup pointGroup) {
        this.mPointGroup = pointGroup;
        pointsLayout.addView(mPointGroup);
    }

    public void setDuration(int duration) {
        try {
            Class<?> viewPager = ViewPager.class;
            Field scroller = viewPager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = viewPager.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            if (mScroller == null) {
                mScroller = new BannerViewPagerScroller(getContext());
            }
            scroller.set(this.viewPager, mScroller);
            mScroller.setDuration(duration);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    class BannerViewPagerScroller extends Scroller {
        private int mDuration = 2000;

        public BannerViewPagerScroller(Context context) {
            super(context);
        }

        public BannerViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setDuration(int duration) {
            this.mDuration = duration;
        }
    }

    private class OnBannerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mPointGroup != null) {
                mPointGroup.setCurrent(position);
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void setOnBannerPageChangeListener() {
        this.mListener = new OnBannerPageChangeListener();
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(mListener);
        }
    }
}
