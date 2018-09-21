package com.example.guilingliu.mvpa.api;

import com.example.guilingliu.mvpa.config.Config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private ZhihuDailyApi mDailyApi;
    private static ApiManager sApiManager;
    private static OkHttpClient mClient;

    private ApiManager() {
    }

    public static ApiManager getInstence() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        mClient = new OkHttpClient.Builder()
//                .addInterceptor(new CustomInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return sApiManager;
    }
    /**
     * 封装配置知乎API
     */
    public ZhihuDailyApi getZhihuService() {
        if (mDailyApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.ZHIHU_API_URL)
                    .client(mClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mDailyApi = retrofit.create(ZhihuDailyApi.class);
        }
        return mDailyApi;
    }
}
