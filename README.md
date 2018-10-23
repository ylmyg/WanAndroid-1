# WanAndroid

#### 项目介绍
对网站 (www.wanandroid.com) 做的第三方android客户端

#### 版本更新
1. xxxx
2. xxxx
3. xxxx

#### 软件架构
1. xxxx
2. xxxx
3. xxxx

#### 安装说明
1. xxxx
2. xxxx
3. xxxx

#### 使用说明
1. xxxx
2. xxxx
3. xxxx

#### 申请的所有权限
1. xxxx
2. xxxx
3. xxxx

#### 开源库
1. xxxx
2. xxxx
3. xxxx

#### 开源控件
1. xxxx
2. xxxx
3. xxxx


说明: 
	1. 这是对 玩android (http://wanandroid.com/) 网站进行自主开发的android客户端;
	2. 仅供学习使用;
	3. 玩android 开放API地址: http://www.wanandroid.com/blog/show/2
	4. 最低支持android6.0
	
效果动图: 
效果截图:

icon来源: 阿里icon
    http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=12650
    http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=12614
    http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=12594
    http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=12554
    http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=12552
    http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=12553

模块和功能介绍
	1. 侧滑菜单栏内容
		1. 用户相关信息
		2. 收藏文章模块
		3. 收藏网站模块
		4. 赞助模块:
			1. 赞助 玩android网站 开发者(张鸿洋)
				* 支付宝
				* 微信
				* 张鸿洋相关信息
			2. 赞助 GplayAndroid 客户端 开发者
				* 支付宝
				* 微信
				* geaosu相关信息
		5. 设置模块
		6. 退出登录模块 


	2. 首页
	3. 导航
	4. 体系
	5. 项目
	6. 收藏

首页
功能 Function
    * 体系
    * 导航
    * 项目
    *
搜索
todo
个人
    * 收藏
    *




玩android项目 原型设计稿
	* 说明
		1. 图片都保存在我的个人QQ空间里, 下面所用到的图片访问连接是QQ空间和GitHub上的图片连接;
		2. 
		2. 设计图为本人亲自手绘设计;

	1. 第1张设计图
		* 名称: 2018-03-03 设计稿0001 - 首页和菜单栏.png
		* QQ url: http://m.qpic.cn/psb?/V13QLE8t19nXTD/9Ghz1xILVsRKkrnJ3n7w89PzT2mXLyMb0B1dtApQ010!/b/dCIBAAAAAAAA&bo=kwOAAgAAAAADBzA!&rf=viewer_4
		* GitHub url: https://github.com/geaosu/GWanAndroid/blob/master/images/2018-03-03%20%E8%AE%BE%E8%AE%A1%E7%A8%BF0001%20-%20%E9%A6%96%E9%A1%B5%E5%92%8C%E8%8F%9C%E5%8D%95%E6%A0%8F.png?raw=true
	2. 第2张设计图
		* 名称: 
		* QQ url: 
		* GitHub url: 

	3. 第张设计图
	4. 第张设计图
	5. 第张设计图
	6. 第张设计图
	7. 第张设计图
	8. 第张设计图
	9. 第张设计图
	10. 第张设计图
	11. 第张设计图
	12. 第张设计图
	13. 第张设计图
	14. 第张设计图
	15. 第张设计图
	16. 第张设计图
	17. 第张设计图
	18. 第张设计图
	19.



第三方控件
pulltorefresh: 下拉刷新上拉加载更多
    * GitHub: https://github.com/823546371/PullToRefresh
    *         https://github.com/823546371/PullToRefresh


    依赖 <<------------------------------------------------
        implementation 'com.jwenfeng.pulltorefresh:library:1.2.7'

    布局 <<------------------------------------------------
        * 注意：内容控件 有且只能有一个，目前支持：ScrollView ，ListView，WebView，RecyclerView。
            <?xml version="1.0" encoding="utf-8"?>
            <com.jwenfeng.library.pulltorefresh.PullToRefreshLayout
                xmlns:android="http://schemas.android.com/apk/res/android"
                android:id="@+id/activity_main"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

                <!-- 内容控件 有且只能有一个 -->
                 <ScrollView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">

                 </ScrollView>

            </com.jwenfeng.library.pulltorefresh.PullToRefreshLayout>

    java代码 <<------------------------------------------------
            pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
                    @Override
                    public void refresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // 结束刷新
                                pullToRefreshLayout.finishRefresh();
                            }
                        }, 2000);
                    }

                    @Override
                    public void loadMore() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // 结束加载更多
                                pullToRefreshLayout.finishLoadMore();
                            }
                        }, 2000);
                    }
                });



banner: 轮播控件

GitHub: https://github.com/youth5201314/banner

依赖: implementation 'com.youth.banner:banner:1.4.10'

布局
    <?xml version="1.0" encoding="utf-8"?>
    <com.youth.banner.Banner xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/brBanner"
        android:layout_width="match_parent"
        android:layout_height="200dp" />

java代码

    //图片Url集合
    private List<String> mImageUrlList = new ArrayList<>();
    //图片标题集合
    private List<String> mTitleList = new ArrayList<>();


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



glide: 图片加载框架

GitHub: https://github.com/bumptech/glide

依赖:
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
























BigView系列开源控件
    - 刷新控件: https://github.com/TellH/Android-Simple-Common-PullToRefreshLayout

#### 关于我
1. xxxx
2. xxxx
3. xxxx



