package com.example.guilingliu.mvpa.mvp.Presenter;

import android.util.Log;

import com.example.guilingliu.mvpa.api.ApiManager;
import com.example.guilingliu.mvpa.bean.ZhiHuDaily;
import com.example.guilingliu.mvpa.mvp.contract.ZhiHuHomeContract;
import com.example.guilingliu.mvpa.mvp.observer.BaseObserver;
import com.example.guilingliu.mvpa.mvp.view.ImpBaseView;
import com.example.guilingliu.mvpa.rxcache.cache.RxcacheProvider;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;

public class ZhiHuPresenter extends BasePresenter implements ZhiHuHomeContract.Presenter {

    private ZhiHuHomeContract.View mZhiHuDailyFrag;
    private String date;

    private HashMap h;

    /**
     * 刷新最新的一条
     */
    @Override
    public void refreshZhihuDaily() {
//
        Observable<ZhiHuDaily> zhiHuDaily = ApiManager.getInstence()
                .getZhihuService()
                .getLatestZhihuDaily();

        RxcacheProvider.getUserCache().getLoadMoreData(zhiHuDaily, new DynamicKey("refresh"), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ZhiHuDaily>() {
                    @Override
                    public void onBaseSubscribe(Disposable d) {

                    }

                    @Override
                    public void onBaseonNext(ZhiHuDaily daily) {
                        date = daily.getDate();
                        mZhiHuDailyFrag.refreshSuccessed(daily);
                    }

                    @Override
                    public void onBaseError(Throwable e) {
                        Log.e("ceshi",""+e.getMessage());
                        Log.e("ceshi",""+e.getCause());
                        Log.e("ceshi",""+e.getStackTrace());

//                        Log.e("ceshi",""+ HttpExceptionUtils.FilterHttpException(e));
                    }

                    @Override
                    public void onBaseComplete() {

                    }
                });
//
//

//
//        ApiManager.getInstence()
//                .getZhihuService()
//                .getLatestZhihuDaily()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<ZhiHuDaily>() {
//                    @Override
//                    public void onBaseSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onBaseonNext(ZhiHuDaily daily) {
//                        date = daily.getDate();
//                        mZhiHuDailyFrag.refreshSuccessed(daily);
//                    }
//
//                    @Override
//                    public void onBaseError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onBaseComplete() {
//
//                    }
//                });


    }

    @Override
    public void loadMoreData() {
        Observable<ZhiHuDaily> zhiHuDaily = ApiManager.getInstence()
                .getZhihuService()
                .getZhihuDaily(date);

        RxcacheProvider.getUserCache().getLoadMoreDataAgain(zhiHuDaily, new DynamicKey(date), new EvictDynamicKey(false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ZhiHuDaily>() {
                    @Override
                    public void onBaseSubscribe(Disposable d) {

                    }

                    @Override
                    public void onBaseonNext(ZhiHuDaily daily) {
                        date = daily.getDate();
                        mZhiHuDailyFrag.refreshSuccessed(daily);
                    }

                    @Override
                    public void onBaseError(Throwable e) {
                        Log.e("ceshi",""+e.getMessage());
                        Log.e("ceshi",""+e.getMessage());
                    }

                    @Override
                    public void onBaseComplete() {

                    }
                });


//
//        ApiManager.getInstence()
//                .getZhihuService()
//                .getZhihuDaily(date)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<ZhiHuDaily>() {
//
//                    @Override
//                    public void onBaseSubscribe(Disposable disposable) {
//
//                    }
//
//                    @Override
//                    public void onBaseonNext(ZhiHuDaily data) {
//                        date = data.getDate();
//                        mZhiHuDailyFrag.loadSuccessed(data);
//                    }
//
//                    @Override
//                    public void onBaseError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onBaseComplete() {
//
//                    }
//                });
//
//
    }

    @Override
    public void loadCache() {

    }

    @Override
    public void bindView(ImpBaseView view) {
        mZhiHuDailyFrag = (ZhiHuHomeContract.View) view;
    }

    @Override
    public void unbindView() {
        dispose();
    }

    @Override
    public void onDestory() {
        mZhiHuDailyFrag = null;
    }
}
