package com.geaosu.wanandroid.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.activity.BrowserActivity;
import com.geaosu.wanandroid.activity.MainActivity;
import com.geaosu.wanandroid.adapter.ArticleListAdapter;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
import com.geaosu.wanandroid.model.HomeArticleModel;
import com.geaosu.wanandroid.model.HomeBannerModel;
import com.geaosu.wanandroid.net.HomeArticleEngine;
import com.geaosu.wanandroid.net.HomeBannerEngine;
import com.geaosu.wanandroid.utils.GlideImageLoader;
import com.geaosu.wanandroid.utils.ToastUtils;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.noober.menu.FloatMenu;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private MainActivity mMainActivity;

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivMenu;

    private ListView lvContent;
    private ArticleListAdapter mArticleListAdapter;
    private PullToRefreshLayout rfLayout;


    private List<HomeArticleModel.DataBean.DatasBean> mArticleList = new ArrayList<>();          //article列表
    private List<String> mBannerImageUrlList = new ArrayList<>();   //banner对应的图片
    private List<String> mBannerTitleList = new ArrayList<>();      //banner对应的标题
    private List<String> mBannerUrlList = new ArrayList<>();        //banner对应的url
    private View mArticleListHeadView;                              //listview头布局
    private int mPager = 0;                                         //数据列表加载的页数
    private boolean mIsCanLoadMore;
    private Banner mBanner;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mMainActivity = (MainActivity) mActivity;
        Bundle bundle = mActivity.getIntent().getExtras();
        if (bundle != null) {

        }
    }

    @Override
    protected void initView(View fragmentView) {
        rfLayout = (PullToRefreshLayout) fragmentView.findViewById(R.id.rfLayout);
        lvContent = (ListView) fragmentView.findViewById(R.id.lvContent);
        setHeadView();
        lvContent.setAdapter(mArticleListAdapter = new ArticleListAdapter(mActivity));
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - 1;
                String artliceLink = mArticleList.get(position).getLink();
                String title = mArticleList.get(position).getTitle();
                if (artliceLink != null && artliceLink.length() > 0) {
                    Intent intent = new Intent(mActivity, BrowserActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("data", bundle);
                    bundle.putString("requestUrl", artliceLink);
                    if (BuildConfig.DEBUG)
                        Log.d(getDebugTag(), "position = " + position + "   title = " + title + "   link = " + artliceLink);
                    mActivity.startActivity(intent);
                } else {
                    ToastUtils.getInstance(mActivity).showToast("地址错误");
                }
            }
        });

        lvContent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String link = mArticleList.get(position - 1).getLink();
                final String title = mArticleList.get(position - 1).getTitle();

                FloatMenu floatMenu = new FloatMenu(mActivity);
                floatMenu.items("复制内容", "复制标题", "复制链接", "分享", "在系统浏览器中打开");
                floatMenu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Log.d(getDebugTag(), "position = " + position + "  url = " + mBannerUrlList.get(position));
                        switch (position) {
                            case 0:
//                                剪贴板相关→ClipboardUtils.java
//                                copyText  : 复制文本到剪贴板
//                                getText   : 获取剪贴板的文本
//                                copyUri   : 复制uri到剪贴板
//                                getUri    : 获取剪贴板的uri
//                                copyIntent: 复制意图到剪贴板
//                                getIntent : 获取剪贴板的意图
                                //从API11开始android推荐使用android.content.ClipboardManager
                                //为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                                ClipboardManager cmContent = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                cmContent.setText(title + ": " + link);// 将文本内容放到系统剪贴板里。
                                ToastUtils.getInstance(mActivity).showToast("复制成功");
                                break;
                            case 1:
                                ClipboardManager cmTitle = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                cmTitle.setText(title);// 将文本内容放到系统剪贴板里。
                                ToastUtils.getInstance(mActivity).showToast("复制成功");
                                break;
                            case 2:
                                ClipboardManager cmLink = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                cmLink.setText(link);// 将文本内容放到系统剪贴板里。
                                ToastUtils.getInstance(mActivity).showToast("复制成功");
                                break;
                            case 3:
                                ToastUtils.getInstance(mActivity).showToast("该功能即将上线");
                                break;
                            case 4:
                                openSystemBrowserWithUrl(link);
                                break;
                        }
                    }
                });
                floatMenu.show(mMainActivity.getPoint());
                return true;
            }
        });
    }

    /**
     * 打开系统自带浏览器, 并打开指定url
     */
    private void openSystemBrowserWithUrl(String path) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse(path);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    protected void initViewListener() {
        rfLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                mPager = 0;
                mArticleList.clear();
                loadData();
            }

            @Override
            public void loadMore() {
                if (mIsCanLoadMore) {
                    mPager++;
                    loadArticleData(mPager);
                } else {
                    rfLayout.finishLoadMore();
                    ToastUtils.getInstance(mActivity).showToast("没有更多数据了");
                }
            }
        });
    }

    @Override
    protected void initTitle(View fragmentView) {
        ivBack = (ImageView) fragmentView.findViewById(R.id.ivBack);
        tvTitle = (TextView) fragmentView.findViewById(R.id.tvTitle);
        ivMenu = (ImageView) fragmentView.findViewById(R.id.ivMenu);

        tvTitle.setText(getResources().getString(R.string.home));
        ivBack.setVisibility(View.GONE);
        ivMenu.setVisibility(View.GONE);
    }

    @Override
    protected void initTitleListener() {

    }

    @Override
    protected void loadData() {
        loadBannerData();
        loadArticleData(0);
    }

    /**
     * 加载banner数据
     */
    private void loadBannerData() {
        HomeBannerEngine bannerEngine = new HomeBannerEngine(getRequestTag());
        bannerEngine.sendRequest();
    }

    /**
     * 加载文章列表数据
     */
    private void loadArticleData(int page) {
        HomeArticleEngine articleEngine = new HomeArticleEngine(getRequestTag(), page);
        articleEngine.sendRequest();
    }

    @Override
    protected void onEventMainThread(LoginEvent event) {

    }

    @Override
    protected void onEventMainThread(DataEvent event) {
        switch (event.type) {
            case GET_HOME_BANNER_ERROR:
                rfLayout.finishRefresh();
                ToastUtils.getInstance(mActivity).showToast((String) event.data.toString());
                break;
            case GET_HOME_BANNER_SUCCESS:
                rfLayout.finishRefresh();

                HomeBannerModel bannerModel = (HomeBannerModel) event.data;
                if (bannerModel != null) {
                    List<HomeBannerModel.DataBean> data = bannerModel.getData();
                    if (data != null) {
                        mBannerImageUrlList.clear();
                        mBannerTitleList.clear();
                        mBannerUrlList.clear();
                        for (int i = 0; i < data.size(); i++) {
                            HomeBannerModel.DataBean dataBean = data.get(i);
                            if (data != null) {
                                mBannerImageUrlList.add(dataBean.getImagePath());
                                mBannerTitleList.add(dataBean.getTitle());
                                mBannerUrlList.add(dataBean.getUrl());
                            }
                        }
                        clearHeadView();
                        setHeadView();
                    }
                }

                break;
            case GET_HOME_ARTICLE_ERROR:
                rfLayout.finishRefresh();
                rfLayout.finishLoadMore();
                ToastUtils.getInstance(mActivity).showToast(event.data.toString());
                break;
            case GET_HOME_ARTICLE_SUCCESS:
                rfLayout.finishRefresh();
                rfLayout.finishLoadMore();
                HomeArticleModel articleModel = (HomeArticleModel) event.data;
                if (articleModel != null) {
                    HomeArticleModel.DataBean data = articleModel.getData();
                    if (data != null) {
                        List<HomeArticleModel.DataBean.DatasBean> datas = data.getDatas();
                        if (datas != null && datas.size() > 0) {
                            mIsCanLoadMore = true;
                            mArticleList.addAll(datas);
                            mArticleListAdapter.replaceAll(mArticleList);
                        } else {
                            mIsCanLoadMore = false;
                        }
                    }
                }
                break;
        }

    }

    @Override
    protected void onEventMainThread(ClickEvent event) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    /**
     * 设置头布局
     */
    private void setHeadView() {
        final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mArticleListHeadView = inflater.inflate(R.layout.home_article_list_head_view, null);
        //View headView = View.inflate(mActivity, R.layout.home_list_head_view, null);
        mBanner = (Banner) mArticleListHeadView.findViewById(R.id.brBanner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mBannerImageUrlList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mBannerUrlList.size() > 0) {
                    String requestUrl = mBannerUrlList.get(position);
                    if (requestUrl != null && requestUrl.length() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("requestUrl", requestUrl);

                        Intent intent = new Intent(mActivity, BrowserActivity.class);
                        intent.putExtra("data", bundle);
                        mActivity.startActivity(intent);

                        Log.d(getDebugTag(), "position = " + position + "  url = " + mBannerUrlList.get(position));
                    }
                }
            }
        });
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        lvContent.addHeaderView(mArticleListHeadView);
    }

    /**
     * 清除头布局
     */
    private void clearHeadView() {
        lvContent.removeHeaderView(mArticleListHeadView);
    }

}
