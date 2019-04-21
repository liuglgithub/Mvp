package com.example.guilingliu.mvpa.handler;

import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guilingliu.mvpa.R;

public class HandlerThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);


        Messenger messenger = new Messenger();

    }

}
