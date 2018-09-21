package com.example.guilingliu.mvpa.mvp.Presenter;

import com.example.guilingliu.mvpa.api.ApiManager;
import com.example.guilingliu.mvpa.bean.ZhiHuDaily;
import com.example.guilingliu.mvpa.mvp.contract.ZhiHuHomeContract;
import com.example.guilingliu.mvpa.mvp.observer.BaseObserver;
import com.example.guilingliu.mvpa.mvp.view.ImpBaseView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZhiHuPresenter extends BasePresenter implements ZhiHuHomeContract.Presenter {

    private ZhiHuHomeContract.View mZhiHuDailyFrag;
    private String date;

    /**
     * 刷新最新的一条
     */
    @Override
    public void refreshZhihuDaily() {

        ApiManager.getInstence()
                .getZhihuService()
                .getLatestZhihuDaily()
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

                    }

                    @Override
                    public void onBaseComplete() {

                    }
                });


    }

    @Override
    public void loadMoreData() {
        ApiManager.getInstence()
                .getZhihuService()
                .getZhihuDaily(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ZhiHuDaily>() {

                    @Override
                    public void onBaseSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onBaseonNext(ZhiHuDaily data) {
                        date = data.getDate();
                        mZhiHuDailyFrag.loadSuccessed(data);
                    }

                    @Override
                    public void onBaseError(Throwable e) {

                    }

                    @Override
                    public void onBaseComplete() {

                    }
                });
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
