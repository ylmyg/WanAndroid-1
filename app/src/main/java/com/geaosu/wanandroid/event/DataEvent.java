package com.geaosu.wanandroid.event;

public class DataEvent {

    public Type type;       //返回类型, 请求成功或者请求失败
    public String tag;      //请求的tag
    public Object data;     //如果请求成功, 返回的是model, 如果请求失败, 返回的是String类型的提示内容

    public DataEvent(Type type, String tag, Object data) {
        this.type = type;
        this.tag = tag;
        this.data = data;
    }

    public enum Type {
        /**
         * 获取首页的banner - 成功
         */
        GET_HOME_BANNER_SUCCESS,

        /**
         * 获取首页的banner - 失败
         */
        GET_HOME_BANNER_ERROR,

        /**
         * 获取首页的文章列表 - 成功
         */
        GET_HOME_ARTICLE_SUCCESS,

        /**
         * 获取首页的文章列表 - 失败
         */
        GET_HOME_ARTICLE_ERROR


    }


}
