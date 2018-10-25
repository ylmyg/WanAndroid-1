package com.geaosu.wanandroid.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.R;

/**
 * 浏览器
 */
public class BrowserActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivCollect;
    private ImageView ivMenu;


    private String mRequestUrl;
    private boolean mIsCollect = false;


    private WebView wbContent;


    @Override
    protected int getContentView() {
        return R.layout.activity_browser;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getBundleExtra("data");
        if (bundle != null) {
            mRequestUrl = bundle.getString("requestUrl", "");
            if (BuildConfig.DEBUG) Log.d(getDebugTag(), "requestUrl = " + mRequestUrl);
        }
    }

    @Override
    protected void initView() {
        wbContent = (WebView) findViewById(R.id.wbContent);
        wbContent.setDownloadListener(new MyDownloadListenter());//下载文件使用系统下载模块
        wbContent.loadUrl(mRequestUrl);
    }

    @Override
    protected void initViewListener() {
        wbContent.setWebViewClient(new WebViewClient() {
            //禁止跳转系统自带浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //加载错误网页 - 本地
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                switch (errorCode) {
                    case 404:
                        view.loadUrl("file:///android_assets/error_handle.html");
                        break;
                }
            }
        });

        //获取网页加载进度
        wbContent.setWebChromeClient(new WebChromeClient() {
            //newProgress: 网页加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

            //title: 正在加载的网页的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tvTitle.setText(title);
            }
        });

    }

    @Override
    protected void initTitle() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        ivCollect = (ImageView) findViewById(R.id.ivCollect);

        if (mIsCollect) {
            ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.collect_red_001));
        } else {
            ivCollect.setImageDrawable(getResources().getDrawable(R.drawable.collect_gray_001));
        }

    }

    @Override
    protected void initTitleListener() {
        ivBack.setOnClickListener(this);
        ivCollect.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivCollect:
                Toast.makeText(this, "点了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivMenu:
                Toast.makeText(this, "点了", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 调用系统download的模块下载webview中包含的下载的内容
     */
    class MyDownloadListenter implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            System.out.println("url ==== >" + url);
            //new HttpThread(url).start();

            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }
}
