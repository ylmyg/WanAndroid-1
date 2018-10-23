# WanAndroid

#### 项目介绍
    对网站 (www.wanandroid.com) 做的第三方android客户端
	1. 该项目是对 "玩android" 网站开放api进行自主开发的第三方android客户端, 仅供学习使用
	2. 玩android官网网址: http://wanandroid.com/
	3. 玩android开放API地址: http://www.wanandroid.com/blog/show/2
	4. 该项目的apk最低支持的系统版本号为android4.1



#### 资源说明:
    1. 所有icon资源来自阿里矢量图库, 网址: http://www.iconfont.cn
    2. 所有接口数据来自玩android开放api, 网址: http://www.wanandroid.com/blog/show/2
    3. 除此以外的部分内容均来自网络;
    4. 所有内容的版权, 所有权和知识产权均归原作者所有, 如有侵权, 请联系删除(如出现侵权行为, 本人十分抱歉, 在这里真诚的向您致歉);



#### 模块和功能介绍
    首页
        * 轮播图
        * 文章列表
    功能
        * 体系
        * 导航
        * 项目
    搜索
        * 热门搜索
        * 历史搜索
        * 搜索结果
    todo
        * 新建todo
        * 全部todo列表
        * 未完成todo列表
        * 已完成tudo列表
    个人
        * 用户相关信息
        * 收藏
        * 设置
        * 赞助: 赞助该项目客户端开发者, 原作者(geaosu)
                * 支付宝
                * 微信
        * 关于作者: geaosu相关信息
        * 反馈:
            * 联系方式;
            * 反馈内容描述;
        * 关于程序:
            * 版本信息;
            * 检测更新;
            * 更新内容介绍;
        * 退出登录模块



#### apk版本更新说明
    版本号: 1.0
        1. 更新首页文章列表的显示和轮播图的显示;



#### GitHub项目提交日志更新说明
    注意:
        1. 这里的日志说明并不代表真实的提交次数和提交内容, 提交内容与文档所说内容不一致时属于正常现象;
        2. 该日志的主要作用: 明确更新的内容以及重大更新的内容;
        3. 并不是每次提交都更新该日志, 只有提交了重要或者变更内容过大时才会更新该日志内容;



#### GitHub项目提交日志

    重要的事情说三遍: 关于该日志的更新和说明请查看 "GitHub项目提交日志更新说明", 下一个标题内容;
    重要的事情说三遍: 关于该日志的更新和说明请查看 "GitHub项目提交日志更新说明", 下一个标题内容;
    重要的事情说三遍: 关于该日志的更新和说明请查看 "GitHub项目提交日志更新说明", 下一个标题内容;

    第1次提交
        1. 创建GitHub项目, 并初始化README.md;

    第2次提交
        1. 导入android studio项目;
        2. 完成UI框架搭建;
        3. design库, 用于底部导航栏的实现;
        4. 完善首页文章列表布局搭建;
        5. 新增pulltorefresh开源控件, 用于实现下拉刷新和上拉加载更多;
        6. 新增banner开源控件, 用于展示首页轮播图;
        7. 新增glide图片加载开源库, 用于apk内所有的图片加载操作;

    第3次提交
        1. 新增EventBus开源库, 用于网络请求数据的分发和接收;

    第4次更新
        1. 新增okhttp网络请求框架, 用于网络请求

    第5次更新
        1. 完善README说明文档内容;





#### 申请的所有权限

    <!--网络权限-->
    <uses-permission android:name="android.permission.INTERNET" />
    <!--SD卡的读写权限-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />


#### 开源库

    1. okhttp
        des: 网络请求库
        GitHub: https://github.com/square/okhttp
        依赖:
            implementation 'com.squareup.okhttp3:okhttp:3.11.0'                 //okhttp3
            testImplementation 'com.squareup.okhttp3:mockwebserver:3.11.0'      //webserver
        混淆:
            #Okhttp3
            # for DexGuard only
            -keepresourcexmlelements manifest/application/meta-data@value=GlideModule
            # JSR 305 annotations are for embedding nullability information.
            -dontwarn javax.annotation.**
            # A resource is loaded with a relative path so the package of this class must be preserved.
            -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
            # Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
            -dontwarn org.codehaus.mojo.animal_sniffer.*
            # OkHttp platform used only on JVM and when Conscrypt dependency is available.
            -dontwarn okhttp3.internal.platform.ConscryptPlatform



    2. eventbus
        des: 事件分发库
        GitHub: https://github.com/greenrobot/EventBus
        依赖: implementation 'org.greenrobot:eventbus:3.1.1'
        混淆:



    3. glide
        des: 图片加载库
        GitHub: https://github.com/bumptech/glide
        依赖:
            implementation 'com.github.bumptech.glide:glide:4.8.0'
            annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
        混淆:
            #glide
            -keep public class * implements com.bumptech.glide.module.GlideModule
            -keep public class * extends com.bumptech.glide.module.AppGlideModule
            -keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
              **[] $VALUES;
              public *;
            }



#### 开源控件
    1. pulltorefresh
        des: 下拉刷新上拉加载更多
        GitHub: https://github.com/823546371/PullToRefresh
        依赖: implementation 'com.jwenfeng.pulltorefresh:library:1.2.7'
        混淆:
        使用:
            布局
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

            java代码
                pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
                    @Override
                    public void refresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshLayout.finishRefresh();    //结束刷新
                            }
                        }, 1000);
                    }

                    @Override
                    public void loadMore() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshLayout.finishLoadMore();   //结束加载更多
                            }
                        }, 1000);
                    }
                });



    2. banner
        des: 轮播控件
        GitHub: https://github.com/youth5201314/banner
        依赖: implementation 'com.youth.banner:banner:1.4.10'
        混淆:
        使用:
            布局
                <?xml version="1.0" encoding="utf-8"?>
                <com.youth.banner.Banner xmlns:android="http://schemas.android.com/apk/res/android"
                android:id="@+id/brBanner"
                android:layout_width="match_parent"
                android:layout_height="200dp" />

            java代码
                private List<String> mImageUrlList = new ArrayList<>();         //图片Url集合
                private List<String> mTitleList = new ArrayList<>();            //图片标题集合

                Banner brBanner = (Banner) headView.findViewById(R.id.brBanner);
                brBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);   //设置banner样式
                brBanner.setImageLoader(new GlideImageLoader());                //设置图片加载器
                brBanner.setImages(mImageUrlList);                              //设置图片集合
                brBanner.setBannerAnimation(Transformer.DepthPage);             //设置banner动画效果
                brBanner.setBannerTitles(mTitleList);                           //设置标题集合（当banner样式有显示title时）
                brBanner.isAutoPlay(true);                                      //设置自动轮播，默认为true
                brBanner.setDelayTime(1500);                                    //设置轮播时间
                brBanner.setIndicatorGravity(BannerConfig.CENTER);              //设置指示器位置（当banner模式中有指示器时）
                brBanner.start();                                               //banner设置方法全部调用完毕时最后调用



#### 关于我
    一名普通的android工程师, 就职北京睿动体育服务有限公司;
    处女座, 有强迫症, 恐高症, 脸盲症, 社交恐惧症等等;
    一身毛病的我, 还怕冷, 怕换季(换季时偶尔起疹子很难受);
    喜欢科技行业, 锤子科技是我第一个疯狂喜欢的科技公司, 估计也是最后一个, 一路用着锤子手机过来的人;
    90后, 家在农村, 长相一般, 没车没房没存款, 北漂1年多, 估计后面还会有很多年;
    有正义感, 浑身正能量, 有上进心(有上进心就是一句废话, 谁不知道努力挣钱买车买房的, 呵呵);



#### 特别鸣谢
    1. 感谢 玩android CEO 张鸿洋先生(本项目基于玩android开发);
    2. 感谢 阿里旗下的阿里矢量图库网及所有贡献icon的设计师们;
    3. 感谢 所有在网上写博客的博主们;
    4. 感谢 所有开源库和开源控件的公司, 组织, 个人开发者们;
    5. 感谢 自己;



#### 最后
    有句话想说: 我觉得, 这个世界上的每一个人, 都应该有开源精神, 懂得分享, 尊重原创;



#### 最后的最后
    奉上我自创的至理名言: 努力让世界变得更好;



----------------------------- 完 ----------------------------->> 华丽的分割线