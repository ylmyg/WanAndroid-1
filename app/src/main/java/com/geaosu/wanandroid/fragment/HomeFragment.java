package com.geaosu.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.activity.BrowserActivity;
import com.geaosu.wanandroid.adapter.ArticleListAdapter;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
import com.geaosu.wanandroid.model.HomeArticleModel;
import com.geaosu.wanandroid.model.HomeBannerModel;
import com.geaosu.wanandroid.net.HomeArticleEngine;
import com.geaosu.wanandroid.net.HomeBannerEngine;
import com.geaosu.wanandroid.utils.GlideImageLoader;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

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
                    Toast.makeText(mActivity, "地址错误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvContent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
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
                    Toast.makeText(mActivity, "没有数据了", Toast.LENGTH_SHORT).show();
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
                //todo showToast
                Toast.makeText(mActivity, (String) event.data, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(mActivity, (String) event.data, Toast.LENGTH_SHORT).show();
                break;
            case GET_HOME_ARTICLE_SUCCESS:
                rfLayout.finishRefresh();
                rfLayout.finishLoadMore();
                HomeArticleModel articleModel = (HomeArticleModel) event.data;
                if (articleModel != null) {
                    HomeArticleModel.DataBean data = articleModel.getData();
                    if (data != null) {
                        int total = data.getTotal();
                        int curPage = data.getCurPage();
                        int size = data.getSize();
                        if (curPage * size >= total) {
                            mIsCanLoadMore = false;//没数据了, 不能加载更多了
                        } else {
                            mIsCanLoadMore = true;
                        }

                        List<HomeArticleModel.DataBean.DatasBean> datas = data.getDatas();
                        if (datas != null && datas.size() >= 0) {
                            mArticleList.addAll(datas);
                            mArticleListAdapter.replaceAll(mArticleList);
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
