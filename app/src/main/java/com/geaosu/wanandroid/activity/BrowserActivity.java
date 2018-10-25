package com.geaosu.wanandroid.activity;

import android.os.Bundle;
import android.util.Log;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;

/**
 * 浏览器页
 */
public class BrowserActivity extends BaseActivity {

    private String mRequestUrl;

    @Override
    protected int getContentView() {
        return R.layout.activity_browser;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getBundleExtra("data");
        if (bundle != null) {
            mRequestUrl = bundle.getString("requestUrl", "");
            if (BuildConfig.DEBUG) Log.d(getDebugTag(), "requestUrl = " + mRequestUrl);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initViewListener() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initTitleListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onEventMainThread(LoginEvent event) {

    }

    @Override
    protected void onEventMainThread(DataEvent event) {

    }

    @Override
    protected void onEventMainThread(ClickEvent event) {

    }
}
