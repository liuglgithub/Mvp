package com.example.guilingliu.mvpa.mvp.contract;

import com.example.guilingliu.mvpa.bean.ZhiHuDaily;
import com.example.guilingliu.mvpa.mvp.Presenter.ImpBasePresenter;
import com.example.guilingliu.mvpa.mvp.view.ImpBaseView;


public interface ZhiHuHomeContract {

    interface View extends ImpBaseView {
        void refreshSuccessed(ZhiHuDaily stories);

        void refreshFail(String errMsg);

        void loadMoreData();

        void loadSuccessed(ZhiHuDaily stories);

        void loadFail(String errMsg);
    }

    interface Presenter extends ImpBasePresenter {
        void refreshZhihuDaily();

        void loadMoreData();

        void loadCache();
    }

}
