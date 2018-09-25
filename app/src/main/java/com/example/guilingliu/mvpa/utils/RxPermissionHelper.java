package com.example.guilingliu.mvpa.utils;

import android.app.Activity;
import android.os.Build;

import com.example.guilingliu.mvpa.GloableApp;
import com.example.guilingliu.mvpa.config.Config;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class RxPermissionHelper {
    private static volatile RxPermissionHelper instance = null;

    private RxPermissionHelper() {
    }

    public static RxPermissionHelper getInstance() {
        if (instance == null) {
            synchronized (RxPermissionHelper.class) {
                if (instance == null) {
                    instance = new RxPermissionHelper();
                }
            }
        }
        return instance;

    }

    public void requestPermission(Activity activity, final OnPermissionListener OnPermissionListener, String... per) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            OnPermissionListener.onGrant(true);
        } else {
            RxPermissions rxPermissions = new RxPermissions(activity);
            rxPermissions.requestEach(per)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted) {
                                StorageUtils.getExternalCacheDir(GloableApp.context, Config.ZHIHU_CACHE);
                                OnPermissionListener.onGrant(permission.granted);
                            }
                        }
                    });
        }
    }

    public interface OnPermissionListener {
        public void onGrant(boolean grant);
    }

}

