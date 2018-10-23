package com.geaosu.wanandroid.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * by: geaosu
 * fragment基类
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(getContentView(), null);
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
}