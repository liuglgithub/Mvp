package com.example.guilingliu.mvpa.rxcache.cache;

import com.example.guilingliu.mvpa.GloableApp;
import com.example.guilingliu.mvpa.config.Config;
import com.example.guilingliu.mvpa.utils.StorageUtils;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

public class RxcacheProvider {

    private static ZhiHuDailyCacheProviders zhiHuDailyCacheProviders;

    public synchronized static ZhiHuDailyCacheProviders getUserCache() {
        if (zhiHuDailyCacheProviders == null) {
            zhiHuDailyCacheProviders = new RxCache.Builder()
                    .persistence(StorageUtils.getExternalCacheDirPath(GloableApp.context, Config.ZHIHU_CACHE),
                            new GsonSpeaker())//缓存文件的配置、数据的解析配置
                    .using(ZhiHuDailyCacheProviders.class);//这些配置对应的缓存接口
        }
        return zhiHuDailyCacheProviders;
    }

}
