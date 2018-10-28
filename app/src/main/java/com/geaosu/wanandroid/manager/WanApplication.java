package com.geaosu.wanandroid.manager;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.geaosu.wanandroid.utils.ToastUtils;

public class WanApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initBlanKJUtils();
    }

    /**
     * 初始化blankj大神的utils工具
     */
    private void initBlanKJUtils() {
        Utils.init(this);
    }
}
