package com.geaosu.wanandroid.event;

public class DataEvent {

    private Type mType;
    private String mTag;
    private Object mData;

    public DataEvent(Type type, String tag, Object data) {
        this.mType = type;
        this.mTag = tag;
        this.mData = data;
    }

    public enum Type {
        /**
         * 获取home的文章列表成功
         */
        GET_HOME_LIST_SUCCESS,

        /**
         * 获取home的文章列表失败
         */
        GET_HOME_LIST_ERROR


    }


}
