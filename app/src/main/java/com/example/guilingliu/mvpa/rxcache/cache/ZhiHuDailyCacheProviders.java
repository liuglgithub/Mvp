package com.example.guilingliu.mvpa.rxcache.cache;

import com.example.guilingliu.mvpa.bean.ZhiHuDaily;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;

public interface ZhiHuDailyCacheProviders {

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     * @param zhihu
     * @param date 驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey   可以明确地清理指定的数据 DynamicKey.
     * @return
     */
    @LifeCache(duration = 1,timeUnit = TimeUnit.MINUTES)
    Observable<ZhiHuDaily> getLoadMoreData(Observable<ZhiHuDaily> zhihu, DynamicKey date, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 1,timeUnit = TimeUnit.MINUTES)
    Observable<ZhiHuDaily> getLoadMoreDataAgain(Observable<ZhiHuDaily> zhihu, DynamicKey date, EvictDynamicKey evictDynamicKey);

}
