package com.geaosu.wanandroid.net;

import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.manager.Constant;
import com.geaosu.wanandroid.model.HomeBannerModel;
import com.google.gson.Gson;

/**
 * 首页轮播图数据接口
 */
public class HomeBannerEngine extends BaseEngine {

    public HomeBannerEngine(String tag) {
        super(tag, Constant.RequestUrl.HOME_BANNER_URL);
    }

    @Override
    protected RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected DataEvent.Type getSuccessType() {
        return DataEvent.Type.GET_HOME_BANNER_SUCCESS;
    }

    @Override
    protected DataEvent.Type getErrorType() {
        return DataEvent.Type.GET_HOME_BANNER_ERROR;
    }

    @Override
    protected Object parseData(boolean isSuccess, String data) {
        if (isSuccess) {
            return new Gson().fromJson(data, HomeBannerModel.class);
        } else {
            return data;
        }
    }
}
