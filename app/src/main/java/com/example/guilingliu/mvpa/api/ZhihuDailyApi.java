package com.example.guilingliu.mvpa.api;

import com.example.guilingliu.mvpa.bean.ZhiHuDaily;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuDailyApi {

    //获取最近的日报
    @GET("news/latest")
    Observable<ZhiHuDaily> getLatestZhihuDaily();

    //获取某一时间之前的日报（本例用于加载更多）
    @GET("news/before/{date}")
    Observable<ZhiHuDaily> getZhihuDaily(@Path("date") String date);


}
