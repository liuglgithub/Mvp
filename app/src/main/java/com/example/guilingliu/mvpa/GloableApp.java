package com.example.guilingliu.mvpa;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class GloableApp extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
