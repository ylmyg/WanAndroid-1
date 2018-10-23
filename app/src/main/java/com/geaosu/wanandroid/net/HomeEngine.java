package com.geaosu.wanandroid.net;

import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.manager.Constant;

/**
 * 首页文章列表接口
 */
public class HomeEngine extends BaseEngine {

    public HomeEngine() {
        super(Constant.HOME_LIST_URL);
    }

    @Override
    protected DataEvent.Type getResultSuccessType() {
        return DataEvent.Type.GET_HOME_LIST_SUCCESS;
    }

    @Override
    protected DataEvent.Type getResultErrorType() {
        return DataEvent.Type.GET_HOME_LIST_ERROR;
    }

    @Override
    protected Object parseResultData(String data) {
        return data;
    }
}
