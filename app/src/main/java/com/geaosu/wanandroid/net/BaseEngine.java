package com.geaosu.wanandroid.net;

import com.geaosu.wanandroid.event.DataEvent;

public abstract class BaseEngine {

    public BaseEngine(String url) {

    }

    /**
     * 成功返回码
     *
     * @return DataEvent.Type
     */
    protected abstract DataEvent.Type getResultSuccessType();

    /**
     * 失败返回码
     *
     * @return DataEvent.Type
     */
    protected abstract DataEvent.Type getResultErrorType();

    /**
     * 解析JSON中data的值
     *
     * @return object
     */
    protected abstract Object parseResultData(String data);
}
