package com.example.guilingliu.mvpa;

import android.app.Application;
import android.content.Context;

public class GloableApp extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
