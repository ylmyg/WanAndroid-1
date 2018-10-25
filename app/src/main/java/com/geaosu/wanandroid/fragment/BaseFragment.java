package com.geaosu.wanandroid.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
import com.geaosu.wanandroid.interfaces.IManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * by: geaosu
 * fragment基类
 */
public abstract class BaseFragment extends Fragment implements IManager {

    protected Context mContext;
    protected Activity mActivity;
    private String mTag = null;
    private long onlyTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getContentView(), null);
        EventBus.getDefault().register(this);
        onlyTime = System.currentTimeMillis();
        init(savedInstanceState);
        initTitle(fragmentView);
        initTitleListener();
        initView(fragmentView);
        initViewListener();
        loadData();
        return fragmentView;
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
    protected abstract void initView(View fragmentView);

    /**
     * 设置控件 的监听
     */
    protected abstract void initViewListener();

    /**
     * 初始化actionBar
     */
    protected abstract void initTitle(View fragmentView);

    /**
     * 设置actionBar 的监听
     */
    protected abstract void initTitleListener();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//卸载事件监听
    }

    /**
     * 登录事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onEventMainThread(LoginEvent event) {
    }

    /**
     * 请求返回事件
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
}
