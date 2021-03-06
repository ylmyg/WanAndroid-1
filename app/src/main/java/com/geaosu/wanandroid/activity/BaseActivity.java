package com.geaosu.wanandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
import com.geaosu.wanandroid.interfaces.IManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * by: geaosu
 */
public abstract class BaseActivity extends AppCompatActivity implements IManager {

    private String mTag = null;
    private long onlyTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        EventBus.getDefault().register(this);
        onlyTime = System.currentTimeMillis();
        init(savedInstanceState);
        initTitle();
        initTitleListener();
        initView();
        initViewListener();
        loadData();
    }

    /**
     * 布局
     */
    protected abstract int getContentView();

    /**
     * 初始化, 恢复数据
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 加载控件
     */
    protected abstract void initView();

    /**
     * 设置控件 的监听
     */
    protected abstract void initViewListener();

    /**
     * 初始化actionBar
     */
    protected abstract void initTitle();

    /**
     * 设置actionBar 的监听
     */
    protected abstract void initTitleListener();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 登录事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onEventMainThread(LoginEvent event) {
    }

    /**
     * 请求事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onEventMainThread(DataEvent event) {
    }

    /**
     * 点击事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onEventMainThread(ClickEvent event) {
    }

    @Override
    public String getRequestTag() {
        if (mTag == null) {
            mTag = getClass().getName() + "-" + getClass().hashCode() + onlyTime;
        }
        return mTag;
    }

    @Override
    public String getDebugTag() {
        return this.getClass().getName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//卸载事件监听
    }
}
