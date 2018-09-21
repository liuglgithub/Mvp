package com.example.guilingliu.mvpa.rxcache.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.guilingliu.mvpa.R;
import com.example.guilingliu.mvpa.bean.ZhiHuDaily;
import com.example.guilingliu.mvpa.bean.ZhiHuStory;
import com.example.guilingliu.mvpa.mvp.Presenter.ZhiHuPresenter;
import com.example.guilingliu.mvpa.mvp.contract.ZhiHuHomeContract;

import java.util.ArrayList;

public class MainRxCacheActivity extends AppCompatActivity implements View.OnClickListener, ZhiHuHomeContract.View {


    TextView rxcacheZhihuDateTv;
    TextView rxcacheResultTv;
    Button rxcacheRefreshBtn;
    Button rxcacheLoadMoreDataBtn;
    ZhiHuHomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx_cache);
        initViews();
        bindPresenter();
    }

    private void initViews() {
        rxcacheZhihuDateTv = findViewById(R.id.rxcacheZhihuDateTv);
        rxcacheResultTv = findViewById(R.id.rxcacheResultTv);
        rxcacheRefreshBtn = findViewById(R.id.rxcacheRefreshBtn);
        rxcacheLoadMoreDataBtn = findViewById(R.id.rxcacheLoadMoreDataBtn);

        rxcacheRefreshBtn.setOnClickListener(this);
        rxcacheLoadMoreDataBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rxcacheRefreshBtn:

                mPresenter.refreshZhihuDaily();

                break;
            case R.id.rxcacheLoadMoreDataBtn:

                mPresenter.loadMoreData();
                break;
        }
    }

    @Override
    public void refreshSuccessed(ZhiHuDaily stories) {
        rxcacheZhihuDateTv.setText(stories.getDate() + "");
        ArrayList<ZhiHuStory> storiesList = stories.getStories();
        StringBuilder sb = new StringBuilder();
        for (ZhiHuStory item : storiesList) {
            sb.append(item.getTitle() + "===" + "\n");
        }
        rxcacheResultTv.setText(sb.toString());
    }

    @Override
    public void refreshFail(String errMsg) {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void loadSuccessed(ZhiHuDaily stories) {
        ArrayList<ZhiHuStory> storiesList = stories.getStories();
        StringBuilder sb = new StringBuilder();
        for (ZhiHuStory item : storiesList) {
            sb.append(item.getTitle() + "===" + "\n");
        }
        rxcacheResultTv.setText(sb.toString());
        rxcacheZhihuDateTv.setText(stories.getDate() + " ：  " + storiesList.size() + "条数据");

    }

    @Override
    public void loadFail(String errMsg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryPresenter();
    }

    @Override
    public void bindPresenter() {
        if (mPresenter == null) {
            mPresenter = new ZhiHuPresenter();
        }
        mPresenter.bindView(this);
    }

    @Override
    public void unBindPresenter() {

    }

    @Override
    public void destoryPresenter() {
        mPresenter.unbindView();
        mPresenter.onDestory();
    }
}
