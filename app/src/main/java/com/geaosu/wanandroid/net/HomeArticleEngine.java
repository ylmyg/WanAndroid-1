package com.geaosu.wanandroid.net;

import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.manager.Constant;
import com.geaosu.wanandroid.model.HomeArticleModel;
import com.google.gson.Gson;

/**
 * 首页文章列表接口
 */
public class HomeArticleEngine extends BaseEngine {

    public HomeArticleEngine(String tag, int page) {
        super(tag, Constant.RequestUrl.HOME_ARTICLE_URL + page + "/json");
    }

    @Override
    protected RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    protected DataEvent.Type getSuccessType() {
        return DataEvent.Type.GET_HOME_ARTICLE_SUCCESS;
    }

    @Override
    protected DataEvent.Type getErrorType() {
        return DataEvent.Type.GET_HOME_ARTICLE_ERROR;
    }

    @Override
    protected Object parseData(boolean isSuccess, String data) {
        if (isSuccess) {
            return new Gson().fromJson(data, HomeArticleModel.class);
        } else {
            return data;
        }
    }


}
