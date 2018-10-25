package com.geaosu.wanandroid.net;

import android.util.Log;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.event.DataEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class BaseEngine {


    private String mRequestUrl;                                     //请求地址
    private String mRequestTag;                                     //请求tag
    private RequestMethod mRequestMethod = RequestMethod.GET;       //请求方式: 默认GET请求


    public BaseEngine(String tag, String url) {
        this.mRequestTag = tag;
        this.mRequestUrl = url;
    }

    /**
     * 设置请求方式
     *
     * @return
     */
    protected abstract RequestMethod getRequestMethod();

    /**
     * 发送网络请求
     */
    public void sendRequest() {
        if (getRequestMethod() != null) {
            mRequestMethod = getRequestMethod();
        }
        if (mRequestUrl == null || mRequestUrl.length() <= 0) {
            dispatchEvent(false, "请求地址错误");
            return;
        }
        switch (mRequestMethod) {
            case GET:
                //todo 检测网络连接是否正常
                sendGetRequest();
                break;
            case POST:
                //todo 检测网络连接是否正常
                sendPostRequest();
                break;
        }
    }

    public enum RequestMethod {
        /**
         * GET请求
         */
        GET,

        /**
         * POST请求
         */
        POST
    }

    /**
     * GET请求
     */
    protected void sendGetRequest() {
        if (BuildConfig.DEBUG) Log.d(mRequestTag, "requestUrl = " + mRequestUrl);

        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        final Request request = new Request.Builder()
                .url(mRequestUrl)    //请求地址
                .get()              //设置请求方式: get()方式和post()方式, 默认请求方式为GET
                .build();           //构建一个请求Request对象

        //创建Call
        Call call = okHttpClient.newCall(request);

        //加入队列 异步操作
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的监听: 异步请求(非主线程)
                if (BuildConfig.DEBUG) Log.d(mRequestTag, "requestBody = 连接失败");
                dispatchEvent(false, "连接失败, 请重试");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功的监听: 异步请求(非主线程)
                if (response.code() == 200) {
                    String data = response.body().string();
                    if (BuildConfig.DEBUG) Log.d(mRequestTag, "requestBody = " + data);
                    dispatchEvent(true, data);
                }
            }
        });


    }

    /**
     * POST请求
     */
    protected void sendPostRequest() {

        // TODO: 2018/10/24 0024 有问题, 需要改
        if (BuildConfig.DEBUG) Log.d(mRequestTag, "requestUrl = " + mRequestUrl);

        //构造okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        //配置参数
        HashMap<String, String> map = new HashMap<>();
        map.put("userQQ", "819813992");
        map.put("userName", "geaosu");
        map.put("userMail", "geaosu@163.com");
        map.put("userWeChat", "geaosu");

        //将map转成json字符串
        Gson gson = new Gson();
        String json = gson.toJson(map);
        //转换结果: "{"userWeChat":"geaosu","userQQ":"819813992","userName":"geaosu","userMail":"geaosu@163.com"}"

        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        //请求地址
        String url = "http://www.geaosu.com/app/user/updatainfo";

        //构造请求
        Request request = new Request.Builder()
                .url(url)               //请求地址
                .post(requestBody)      //请求方式: POST
                .build();

        //创建Call
        Call call = okHttpClient.newCall(request);

        //加入请求队列 ----->> 异步操作
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败监听: 非ui线程-->>异步操作
                System.out.println("连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功监听: 非ui线程-->>异步操作
                System.out.println(response.body().string());
            }
        });

    }

    /**
     * 分发事件, 携带数据
     *
     * @param data
     */
    private void dispatchEvent(boolean isSuccess, String data) {
        DataEvent eventData = new DataEvent(isSuccess ? getSuccessType() : getErrorType(), mRequestTag, parseData(isSuccess, data));
        EventBus.getDefault().post(eventData);
    }

    /**
     * 成功返回码
     *
     * @return DataEvent.Type
     */
    protected abstract DataEvent.Type getSuccessType();

    /**
     * 失败返回码
     *
     * @return DataEvent.Type
     */
    protected abstract DataEvent.Type getErrorType();

    /**
     * 解析JSON中data的值
     *
     * @return object
     */
    protected abstract Object parseData(boolean isSuccess, String data);
}
