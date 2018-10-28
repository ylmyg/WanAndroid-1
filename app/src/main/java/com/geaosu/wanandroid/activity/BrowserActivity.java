package com.geaosu.wanandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.utils.ToastUtils;

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
    private PopupWindow mPopupWindow;


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
                ToastUtils.getInstance(BrowserActivity.this).showToast("collect");
                break;
            case R.id.ivMenu:
                showPopupWindow(mRequestUrl);
                break;
        }
    }

    /**
     * 显示右上角的菜单列表
     */
    private void showPopupWindow(final String link) {
        // 用于PopupWindow的View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindowView = inflater.inflate(R.layout.popupwindow_layout, null);


        TextView tvUseSystemBrow = (TextView) popupWindowView.findViewById(R.id.tvUseSystemBrow);
        TextView tvShare = (TextView) popupWindowView.findViewById(R.id.tvShare);

        tvUseSystemBrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                openSystemBrowserWithUrl(link);
            }
        });

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
                ToastUtils.getInstance(BrowserActivity.this).showToast("该功能即将上线");
            }
        });


        //View contentView = LayoutInflater.from(context).inflate(layoutRes, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        mPopupWindow = new PopupWindow(popupWindowView, 400, WindowManager.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
        mPopupWindow.setBackgroundDrawable(colorDrawable);// 设置PopupWindow的背景
        mPopupWindow.setFocusable(true);//设置popwindow弹窗可点击 这句话必须添加，并且为true
        mPopupWindow.setOutsideTouchable(true);// 设置PopupWindow是否能响应外部点击事件
        mPopupWindow.setTouchable(true);// 设置PopupWindow是否能响应点击事件
        //mPopupWindow.setAnimationStyle(R.style.browserActivityPopupWindowAnim);//设置动画
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        //mPopupWindow.showAsDropDown(anchor, xoff, yoff);
        //在底部显示
        //mPopupWindow.showAtLocation(ivMenu, 0, 0, 49);
        mPopupWindow.showAsDropDown(ivMenu, -290, 10);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        // mPopupWindow.showAtLocation(parent, gravity, x, y);


        //显示监听
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //ToastUtils.getInstance(BrowserActivity.this).showToast("消失了");
            }
        });
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

    /**
     * 打开系统自带浏览器, 并打开指定url
     */
    private void openSystemBrowserWithUrl (String path){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse(path);
        intent.setData(uri);
        startActivity(intent);
    }
}
