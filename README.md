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

    20181024-周三: 第1次提交
        1. 新增EventBus开源库, 用于网络请求数据的分发和接收;

    20181024-周三: 第2次提交
        1. 新增okhttp网络请求框架, 用于网络请求

    20181024-周三: 第3次提交
        1. 完善README说明文档内容;

    20181024-周三: 第4次提交
        1. 新增Gson转换库;
        2. 更新README文档;

    20181025-周四: 第1次提交
        1. 完善数据分发系统;
        2. 完善EventBus数据处理;
        3. 完善首页banner的所有逻辑处理;
        4. 完善首页文章列表的展示和逻辑处理;
        5. 更新README文档;

    20181025-周四: 第2次提交
        1. 新增网页浏览器;
        2. 新增网页浏览器下拉浏览器页展示开发者信息;

    20181025-周四: 第3次提交
        1. 新增工具类开源库;

    20181025-周五: 第1次提交
            1. 新增搜索界面;

    20181025-周六: 第1次提交
            1. 新增首页文章列表长按菜单;
            2. 首页文章列表长安菜单部分功能实现;

    20181025-周日: 第1次提交
            1. 新增浏览器界面右菜单;
            2. 右菜单部分功能实现;
            3. 首页布局改版, 新版UI超级好看(自认为比以前的好看);



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

    4. gson
        des:
            * GSON是一个Java库，用于将Java对象转换为JSON表示形式。
            * 它还可以用来将JSON字符串转换成等效的Java对象。
            * GSON可以使用任意Java对象，包括没有源代码的预先存在的对象。
        GitHub: https://github.com/google/gson
        依赖: implementation 'com.google.code.gson:gson:2.8.5'
        jar包下载地址: https://search.maven.org/artifact/com.google.code.gson/gson/2.8.5/jar
        混淆:

    5.utilcode
        des:
        GitHub: https://github.com/hoangkien0705/Android-UtilCode
        依赖:
            implementation 'com.blankj:utilcode:1.11.1'
            implementation 'com.blankj:utilcode:1.5.1'
        混淆:
            -keep class com.blankj.utilcode.** { *; }
            -keepclassmembers class com.blankj.utilcode.** { *; }
            -dontwarn com.blankj.utilcode.**

        使用:
            1. 初始化: 在application的onCreate方法中调用如下方法
                /**
                 * 初始化blankj大神的utils工具
                 */
                private void initBlanKJUtils() {
                    Utils.init(this);
                }

            2. 用自己想用的功能:
                * 判断网络状态










   AndroidViewAnimations
        des: view动画收集项目
        GitHub: https://github.com/daimajia/AndroidViewAnimations
        依赖:
            compile 'com.android.support:support-compat:25.1.1'
            compile 'com.daimajia.easing:library:2.0@aar'
            compile 'com.daimajia.androidanimations:library:2.3@aar'
        jar包下载:
        混淆:
        使用:
            YoYo.with(Techniques.Tada)
                .duration(700)
                .repeat(5)
                .playOn(findViewById(R.id.edit_area));



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
            # glide 的混淆代码
            -keep public class * implements com.bumptech.glide.module.GlideModule
            -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
                **[] $VALUES;
                public *;
            }

            # banner 的混淆代码
            -keep class com.youth.banner.** {
                *;
            }
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

                Banner banner = (Banner) headView.findViewById(R.id.brBanner);
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);   //设置banner样式
                banner.setImageLoader(new GlideImageLoader());                //设置图片加载器
                banner.setImages(mImageUrlList);                              //设置图片集合
                banner.setBannerAnimation(Transformer.DepthPage);             //设置banner动画效果
                banner.setBannerTitles(mTitleList);                           //设置标题集合（当banner样式有显示title时）
                banner.isAutoPlay(true);                                      //设置自动轮播，默认为true
                banner.setDelayTime(1500);                                    //设置轮播时间
                banner.setIndicatorGravity(BannerConfig.CENTER);              //设置指示器位置（当banner模式中有指示器时）
                banner.start();                                               //banner设置方法全部调用完毕时最后调用

                /**
                 * 设置头布局
                 */
                private void setHeadView() {
                    final LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    mArticleListHeadView = inflater.inflate(R.layout.home_article_list_head_view, null);
                    //View headView = View.inflate(mActivity, R.layout.home_list_head_view, null);
                    Banner banner = (Banner) mArticleListHeadView.findViewById(R.id.brBanner);
                    //设置banner样式
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置图片集合
                    banner.setImages(mBannerImageUrlList);
                    //设置banner动画效果
                    banner.setBannerAnimation(Transformer.DepthPage);
                    //设置标题集合（当banner样式有显示title时）
                    banner.setBannerTitles(mBannerTitleList);
                    //设置自动轮播，默认为true
                    banner.isAutoPlay(true);
                    banner.setOnBannerListener(new OnBannerListener() {
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
                    banner.setDelayTime(2000);
                    //设置指示器位置（当banner模式中有指示器时）
                    banner.setIndicatorGravity(BannerConfig.CENTER);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();

                    lvContent.addHeaderView(mArticleListHeadView);
                }

                /**
                 * 清除头布局
                 * 该方法用于刷新列表时使用的, 当请求到了新的数据时, 先调用clearHeadView方法移除头布局, 然后调用setHeadView新增头布局, 这样才能替换新数据成功;
                 */
                private void clearHeadView() {
                    lvContent.removeHeaderView(mArticleListHeadView);
                }


    3. sldinglayout
       des: 顶部下拉展示开发者信息
       GitHub: https://github.com/HomHomLin/SlidingLayout
       依赖:
           implementation 'homhomlin.lib:sldinglayout:0.9.0'
           //如果你的项目需要支持API V9，你需要添加以下依赖
           implementation 'com.nineoldandroids:library:2.4.0'

       使用:
           布局:
               //author_info_layout_bg.xml  拉下来能看到的那部分开发者信息的布局
               <?xml version="1.0" encoding="utf-8"?>
               <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                   android:layout_width="match_parent"
                   android:layout_height="match_parent"
                   android:background="#EBEBEB"
                   android:orientation="vertical">

                   <TextView
                       android:layout_width="match_parent"
                       android:layout_height="wrap_content"
                       android:gravity="center"
                       android:padding="16dp"
                       android:singleLine="true"
                       android:text="数据由 www.wanandroid.com 提供"
                       android:textAllCaps="false"
                       android:textColor="#008577"
                       android:textSize="12sp"
                       android:textStyle="bold" />

                   <TextView
                       android:layout_width="match_parent"
                       android:layout_height="wrap_content"
                       android:gravity="center"
                       android:singleLine="true"
                       android:text="developed by geaosu"
                       android:textAllCaps="false"
                       android:textColor="#008577"
                       android:textSize="14sp"
                       android:textStyle="bold" />
               </LinearLayout>

               //activity的布局
               <?xml version="1.0" encoding="utf-8"?>
               <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                   xmlns:app="http://schemas.android.com/apk/res-auto"
                   xmlns:tools="http://schemas.android.com/tools"
                   android:layout_width="match_parent"
                   android:layout_height="match_parent"
                   android:background="@color/colorPrimaryDark"
                   android:orientation="vertical"
                   tools:context=".activity.BrowserActivity">

                   <include layout="@layout/action_bar_browser" />

                   <!--显示开发者信息 不需要java代码-->
                   <lib.homhomlib.design.SlidingLayout
                       android:id="@+id/slidingLayout"
                       android:layout_width="match_parent"
                       android:layout_height="match_parent"
                       app:background_view="@layout/author_info_layout_bg"
                       app:sliding_mode="top"
                       app:top_max="140dp">

                       <WebView
                           android:id="@+id/wbContent"
                           android:layout_width="match_parent"
                           android:layout_height="match_parent" />

                   </lib.homhomlib.design.SlidingLayout>
               </LinearLayout>

    4. FloatMenu
            des: 仿微信的长按菜单
            GitHub: https://github.com/JavaNoober/FloatMenu
            依赖: implementation 'com.noober.floatmenu:common:1.0.4'
            bbs: https://blog.csdn.net/qq_25412055/article/details/78866496
            混淆:
            使用:
                1. 在MAinActivity中
                    public Point mPoint = new Point();
                    @Override
                    public boolean dispatchTouchEvent(MotionEvent ev) {
                        if(ev.getAction() == MotionEvent.ACTION_DOWN){
                            mPoint.x = (int) ev.getRawX();
                            mPoint.y = (int) ev.getRawY();
                        }
                        return super.dispatchTouchEvent(ev);
                    }

                    /**
                     * 获取point
                     * @return
                     */
                    public Point getPoint(){
                        return mPoint;
                    }

                2. 在fragment中
                    MainActivity mMainActivity = (MainActivity)getActivity();

                    FloatMenu floatMenu = new FloatMenu(mMainActivity);
                    floatMenu.items("复制标题", "复制链接", "分享", "在系统浏览器中打开");
                    floatMenu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
                        @Override
                        public void onClick(View v, int position) \
                            Log.d(getDebugTag(), "position = " + position + "  url = " + mBannerUrlList.get(position));
                        }
                    });
                    floatMenu.show(mMainActivity.getPoint());






#### 系统自带控件
    1. PopupWindow
        1. 创建PopupWindow对象实例;
        2. 设置背景、注册事件监听器和添加动画;
        3. 显示PopupWindow;

        // 用于PopupWindow的View
        View contentView=LayoutInflater.from(context).inflate(layoutRes, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window=new PopupWindow(contentView, 100, 100, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAsDropDown(anchor, xoff, yoff);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        // window.showAtLocation(parent, gravity, x, y);


007
#### shape图形
    https://www.cnblogs.com/popfisher/p/6238119.html

#### 部分调用内容介绍
    1. getRequestTag()方法
        * 该方法位于IManager接口, BaseFragment类和BaseActivity类都实现了IManager接口, 并重写了getRequestTag()方法;
        * 后面的BaseFragment类和BaseActivity类的子类直接调用getRequestTag()方法即可;



#### 项目中的动画: https://blog.csdn.net/huachao1001/article/details/51659963
    1. 共享元素动画: 第一个界面的某个View与第二个界面的某个View之间的过渡效果动画;
        * 效果图: https://img-blog.csdn.net/20160615130307559
        * 使用
            1. 在界面布局中的某个View属性中加上android:transitionName属性: android:transitionName="名称"
                * 这里命名为: geaosuAnim, 两个transitionName属性的名称要一致;

                    //第一个界面布局里的view中加上共享动画属性
                    <?xml version="1.0" encoding="utf-8"?>
                    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent">

                        <View
                            android:id="@+id/firstSharedView"
                            android:layout_width="100dp"
                            android:layout_height="100dp"
                            android:background="#00cc00"
                            android:onClick="onClick"
                            android:transitionName="geaosuAnim" />
                    </RelativeLayout>

                    //第二个界面布局里的view中加上共享动画属性
                    <?xml version="1.0" encoding="utf-8"?>
                    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent">

                        <View
                            android:layout_width="match_parent"
                            android:layout_height="300dp"
                            android:layout_alignParentBottom="true"
                            android:background="#00cc00"
                            android:onClick="onClick"
                            android:transitionName="geaosuAnim" />

                    </RelativeLayout>

            2. 调用startActivity方法:
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, firstSharedView, "sharedView").toBundle());
                    * public static ActivityOptions makeSceneTransitionAnimation(Activity activity, View sharedElement, String sharedElementName)方法:
                        第一个参数Activity没啥解释的;
                        第二个参数就是第一个Activity中的View对象;
                        第三个参数就是两个Activity的View的 android:transitionName属性的值;

                        注意: 该方法是api21中提供的, 需要将minSdkVersion的版本提升到21;


    2. https://blog.csdn.net/djun100/article/details/14053653




#### 总结
    https://www.cnblogs.com/mrszhou/p/7101270.html

    activity之间的数据传递
        1. Intent
            //传递数据
            Intent intent = new Intent(this, XXX.class);
            intent.putExtra("date", "geaosu");
            startActivity(intent);

            //接收数据
            Intent i = getIntent();
            String data = i.getStringExtra("date"));


        2. Intent + Bundle
            //传递数据
            Intent intent = new Intent(this, XXX.class);

            Bundle bundle = new Bundle();
            bundle.putString("name", "geaosu");
            bundle.putInt("mail", "geaosu@163.com");

            intent.putExtras(bundle);
            startActivity(intent);

            //接受数据
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String name = bundle.getString("name");
            String mail = bundle.getString("mail");


    Fragment之间的数据传递



    Fragment和activity之间的数据传递
        1. activity传递数据给fragment:
            //传递数据
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", "geaosu");
            homeFragment.setArguments(bundle);

            //接收数据
            https://www.cnblogs.com/mrszhou/p/7101270.html


            //宿主activity中的getTitles()方法
            public String getTitles(){
                return "hello";
            }

            //Fragment中的onAttach方法
                @Override
                public void onAttach(Activity activity) {
                    super.onAttach(activity);
                    titles = ((MainActivity) activity).getTitles();
                }
            //通过强转成宿主activity，就可以获取到传递过来的数据


        2. fragment传递数据给activity:





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