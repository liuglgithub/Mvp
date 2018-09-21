package com.example.guilingliu.mvpa.utils;


import java.net.SocketTimeoutException;

import retrofit2.HttpException;


/**
 * date: 2016/12/12 09:34
 * email:
 */
public class HttpExceptionUtils {
    public static String FilterHttpException(Object e) {
        String str = "";
        if (e != null && e instanceof HttpException) { // 1、处理HTTP相关异常
            int code = ((HttpException) e).code();
            switch (code) {
                case 404:
                    str = "请求出错,网络数据异常";
                    break;
                case 400:
                    str = "请求出错";
                    break;
                case 500:
                    str = "解析错误";
                    //DNS解析失败
                    break;
                default:
                    str = "网络不给力, 请检查网络连接";
                    break;
            }
        } else if (e != null && e instanceof SocketTimeoutException) { //2、Socket超时相关异常
            str = "请求超时，请重试";
        } else {
            str = "网络请求失败，请检查您的网络";
        }

//        // 3、没有网络相关异常
//        if (!AppContext.isNetWork) {
//            str = Constant.ERROR_FORNET;
//            LogUtil.e("nettag",""+Constant.ERROR_FORNET);
//        }
        return str;
    }
}
