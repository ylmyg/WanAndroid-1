package com.geaosu.wanandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.adapter.ArticleListAdapter;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
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


    //图片Url集合
    private List<String> mBannerImageUrlList = new ArrayList<>();
    //图片标题集合
    private List<String> mBannerTitleList = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mBannerImageUrlList.add("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=01ec1dc417d5ad6ebef46cb8e0a252be/83025aafa40f4bfb281ab8540f4f78f0f63618e3.jpg");
        mBannerImageUrlList.add("https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C2%2C786%2C519%3Bc0%3Dbaike92%2C5%2C5%2C92%2C30/sign=ecbf967fb4389b502cb0ba12b805c9ef/5fdf8db1cb134954d34d64055b4e9258d1094a04.jpg");
        mBannerImageUrlList.add("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=8d92cde951afa40f28cbc68fca0d682a/023b5bb5c9ea15cec8b19ad2bd003af33b87b28a.jpg");
        mBannerImageUrlList.add("https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=45483f2430c79f3d9becec62dbc8a674/e7cd7b899e510fb383510d4cd233c895d0430ca4.jpg");
        mBannerImageUrlList.add("https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=7c2c3fed16950a7b613846966bb809bc/e61190ef76c6a7effe3fccfdf6faaf51f2de66cb.jpg");
        mBannerImageUrlList.add("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=08ea591c9b529822113e3191b6a310ae/b2de9c82d158ccbf2248d0e112d8bc3eb0354170.jpg");
        mBannerImageUrlList.add("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=c25f58d1682762d09433acedc185639f/bf096b63f6246b6067805586e0f81a4c500fa2b2.jpg");
        mBannerTitleList.add("geaosu 1");
        mBannerTitleList.add("geaosu 2");
        mBannerTitleList.add("geaosu 3");
        mBannerTitleList.add("geaosu 4");
        mBannerTitleList.add("geaosu 5");
        mBannerTitleList.add("geaosu 6");
        mBannerTitleList.add("geaosu 7");
    }

    @Override
    protected void initView(View fragmentView) {
        rfLayout = (PullToRefreshLayout) fragmentView.findViewById(R.id.rfLayout);
        lvContent = (ListView) fragmentView.findViewById(R.id.lvContent);
        setHeadView();
        lvContent.setAdapter(mArticleListAdapter = new ArticleListAdapter(mActivity));
    }

    @Override
    protected void initViewListener() {
        rfLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
                        rfLayout.finishRefresh();
                    }
                }, 1000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束加载更多
                        rfLayout.finishLoadMore();
                    }
                }, 1000);
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

    }

    @Override
    protected void onEventMainThread(LoginEvent event) {

    }

    @Override
    protected void onEventMainThread(DataEvent event) {

    }

    @Override
    protected void onEventMainThread(ClickEvent event) {

    }

    /**
     * 设置头布局
     */
    private void setHeadView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headView = inflater.inflate(R.layout.home_list_head_view, null);
        //View headView = View.inflate(mActivity, R.layout.home_list_head_view, null);
        Banner brBanner = (Banner) headView.findViewById(R.id.brBanner);
        //设置banner样式
        brBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        brBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        brBanner.setImages(mBannerImageUrlList);
        //设置banner动画效果
        brBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        brBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        brBanner.isAutoPlay(true);
        brBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.d("ggg", "position = "+position);
            }
        });
        //设置轮播时间
        brBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        brBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        brBanner.start();

        lvContent.addHeaderView(headView);
    }
}
