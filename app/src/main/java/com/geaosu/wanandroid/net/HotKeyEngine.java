package com.geaosu.wanandroid.net;

import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.manager.Constant;
import com.geaosu.wanandroid.model.HotKeyModel;
import com.google.gson.Gson;

public class HotKeyEngine extends BaseEngine {
    public HotKeyEngine(String tag) {
        super(tag, Constant.RequestUrl.HOT_KEY_URL);
    }

    @Override
    protected RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected DataEvent.Type getSuccessType() {
        return DataEvent.Type.GET_HOT_KEY_SUCCESS;
    }

    @Override
    protected DataEvent.Type getErrorType() {
        return DataEvent.Type.GET_HOT_KEY_ERROR;
    }

    @Override
    protected Object parseData(boolean isSuccess, String data) {
        if (isSuccess) {
            return new Gson().fromJson(data, HotKeyModel.class);
        } else {
            return data;
        }
    }
}
