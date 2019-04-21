
package com.example.guilingliu.mvpa;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class MainJavaActivity extends AppCompatActivity {

Service s;
Handler h = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);
        Looper.prepare();

        new Thread(new Runnable() {
            @Override
            public void run() {
                h.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                h.sendEmptyMessage(1);
            }
        }).start();


        h.removeCallbacksAndMessages("");

    }

}
