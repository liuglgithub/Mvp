package com.example.guilingliu.mvpa.mvp.observer;

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
