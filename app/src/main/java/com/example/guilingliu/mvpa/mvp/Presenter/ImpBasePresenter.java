package com.example.guilingliu.mvpa.mvp.Presenter;

import com.example.guilingliu.mvpa.mvp.view.ImpBaseView;

/**
 * 初始化view和销毁view ，避免view引起的泄露
 */
public interface ImpBasePresenter {
    public void bindView(ImpBaseView view);
    public void unbindView();
    public void onDestory();
}
