package com.geaosu.wanandroid.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.adapter.ArticleListAdapter;
import com.geaosu.wanandroid.utils.GlideImageLoader;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

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
    private List<String> mImageUrlList = new ArrayList<>();
    //图片标题集合
    private List<String> mTitleList = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

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

    /**
     * 设置头布局
     */
    private void setHeadView() {
        View headView = View.inflate(mActivity, R.layout.home_list_head_view, null);
        Banner brBanner = (Banner) headView.findViewById(R.id.brBanner);
        //设置banner样式
        brBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        brBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        brBanner.setImages(mImageUrlList);
        //设置banner动画效果
        brBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        brBanner.setBannerTitles(mTitleList);
        //设置自动轮播，默认为true
        brBanner.isAutoPlay(true);
        //设置轮播时间
        brBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        brBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        brBanner.start();

        lvContent.addHeaderView(headView);
    }
}
