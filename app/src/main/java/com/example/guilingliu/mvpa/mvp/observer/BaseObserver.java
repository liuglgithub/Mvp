package com.example.guilingliu.mvpa.mvp.observer;

import android.util.Log;
import android.widget.Toast;

import com.example.guilingliu.mvpa.GloableApp;
import com.example.guilingliu.mvpa.utils.HttpExceptionUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T>{
    @Override
    public void onSubscribe(Disposable d) {
        onBaseSubscribe(d);
    }

    @Override
    public void onNext(T o) {
        onBaseonNext(o);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("ceshi",""+ HttpExceptionUtils.FilterHttpException(e));
        Toast.makeText(GloableApp.context,HttpExceptionUtils.FilterHttpException(e),Toast.LENGTH_LONG).show();
        onBaseError(e);
    }

    @Override
    public void onComplete() {
        onBaseComplete();
    }

    public abstract void onBaseSubscribe(Disposable disposable);
    public abstract void onBaseonNext(T data);
    public abstract void onBaseError(Throwable e);
    public abstract void onBaseComplete();

}
