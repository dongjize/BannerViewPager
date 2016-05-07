package com.dong.bannerviewpagerdemo;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * Description:
 * <p/>
 * Author: dong
 * Date: 16/5/7
 */
public class MainApplication extends Application {
    //UniversalImageLoader的缓存期限
    private final long DiscCacheLimitTime = 3600 * 24 * 15L;
    private final int ImageLoaderCacheSize = 20 * 1024 * 1024;

    @Override
    public void onCreate() {
        super.onCreate();
        checkUniversalImageLoaderConfig(getApplicationContext());
    }

    /**
     * UniversalImageLoader相关配置
     *
     * @param context
     */
    private void checkUniversalImageLoaderConfig(Context context) {
        if (!ImageLoaderUtils.checkImageLoader()) {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                    .threadPriority(Thread.NORM_PRIORITY - 2) //开3个线程
                    .denyCacheImageMultipleSizesInMemory()
                    .diskCacheSize(ImageLoaderCacheSize)
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                    .diskCache(new LimitedAgeDiskCache(StorageUtils.getCacheDirectory(context), DiscCacheLimitTime))
                    .build();
            ImageLoader.getInstance().init(config);
        }
    }
}
