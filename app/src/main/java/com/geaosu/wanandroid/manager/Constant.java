package com.geaosu.wanandroid.manager;

/**
 * 常量类
 */
public class Constant {

    /**
     * 接口地址
     */
    public interface RequestUrl {


        /**
         * 首页banner
         * http://www.wanandroid.com/banner/json
         * 方法：GET
         * 参数：无
         */
        String HOME_BANNER_URL = "http://www.wanandroid.com/banner/json";

        /**
         * 首页文章列表:
         * http://www.wanandroid.com/article/list/0/json
         * 方法：GET
         * 参数：页码，拼接在连接中，从0开始。
         */
        String HOME_ARTICLE_URL ="http://www.wanandroid.com/article/list/";

    }

}
