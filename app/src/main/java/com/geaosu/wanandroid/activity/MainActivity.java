package com.geaosu.wanandroid.activity;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
import com.geaosu.wanandroid.fragment.FunctionFragment;
import com.geaosu.wanandroid.fragment.HomeFragment;
import com.geaosu.wanandroid.fragment.MeFragment;
import com.geaosu.wanandroid.fragment.SearchFragment;
import com.geaosu.wanandroid.fragment.TodoFragment;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity {

    private RelativeLayout rlContent;
    private BottomNavigationView bvBottomActionBar;

    private FragmentTransaction mFragmentTransaction;       //fragment事务
    private FragmentManager mFragmentManager;               //fragment管理者

    private HomeFragment mHomeFragment;
    private FunctionFragment mFunctionFragment;
    private SearchFragment mSearchFragment;
    private TodoFragment mTodoFragment;
    private MeFragment mMeFragment;
    private int mCurrentFragment = 0;                       //当前显示的fragment, 启动时需要显示的fragment


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //获取启动时需要显示的fragment的下标
            mCurrentFragment = bundle.getInt("showFragment", 0);
        }
    }

    @Override
    protected void initView() {
        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        bvBottomActionBar = (BottomNavigationView) findViewById(R.id.bvBottomActionBar);

        mFragmentManager = getSupportFragmentManager();//获取到fragment的管理对象

        disableShiftingMode(bvBottomActionBar);  //禁止偏移
        //disableItemScale(bnvBottomBar);   //禁止放大


    }

    @Override
    protected void initViewListener() {
        bvBottomActionBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        showFragment(0);
                        break;
                    case R.id.navigation_function:
                        showFragment(1);
                        break;
                    case R.id.navigation_search:
                        showFragment(2);
                        break;
                    case R.id.navigation_todo:
                        showFragment(3);
                        break;
                    case R.id.navigation_me:
                        showFragment(4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initTitleListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onEventMainThread(LoginEvent event) {
        super.onEventMainThread(event);
    }

    @Override
    protected void onEventMainThread(DataEvent event) {
        super.onEventMainThread(event);
    }

    @Override
    protected void onEventMainThread(ClickEvent event) {
        super.onEventMainThread(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //选中某个item: app启动默认打开me界面
        switch (mCurrentFragment) {
            case 0:
                bvBottomActionBar.setSelectedItemId(R.id.navigation_home);
                break;
            case 1:
                bvBottomActionBar.setSelectedItemId(R.id.navigation_function);
                break;
            case 2:
                bvBottomActionBar.setSelectedItemId(R.id.navigation_search);
                break;
            case 3:
                bvBottomActionBar.setSelectedItemId(R.id.navigation_todo);
                break;
            case 4:
                bvBottomActionBar.setSelectedItemId(R.id.navigation_me);
                break;
        }

    }

    /**
     * 禁用item>=3时的平移及缩放(没有平移效果了, 但是图标和文字还有放大效果)
     * 出处: https://blog.csdn.net/zzyawei/article/details/81082257
     */
    @SuppressLint("RestrictedApi")
    public static void disableShiftingMode(BottomNavigationView view) {
        try {
            BottomNavigationMenuView mMenuView = (BottomNavigationMenuView) view.getChildAt(0);
            Field mShiftingModeField = BottomNavigationMenuView.class.getDeclaredField("mShiftingMode");
            mShiftingModeField.setAccessible(true);
            mShiftingModeField.set(mMenuView, false);
            for (int i = 0; i < mMenuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) mMenuView.getChildAt(i);
                //itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 出处: https://blog.csdn.net/dl10210950/article/details/54889376/
     */
    private void showFragment(int index) {
        mCurrentFragment = index;
        //开启事务
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //显示之前将所有的fragment都隐藏起来,在去显示我们想要显示的fragment
        hideFragment(mFragmentTransaction);
        switch (index) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    //加入事务
                    mFragmentTransaction.add(R.id.rlContent, mHomeFragment);
                } else {
                    mFragmentTransaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (mFunctionFragment == null) {
                    mFunctionFragment = new FunctionFragment();
                    mFragmentTransaction.add(R.id.rlContent, mFunctionFragment);
                } else {
                    mFragmentTransaction.show(mFunctionFragment);
                }
                break;
            case 2:
                if (mSearchFragment == null) {
                    mSearchFragment = new SearchFragment();
                    mFragmentTransaction.add(R.id.rlContent, mSearchFragment);
                } else {
                    mFragmentTransaction.show(mSearchFragment);
                }
                break;
            case 3:
                if (mTodoFragment == null) {
                    mTodoFragment = new TodoFragment();
                    mFragmentTransaction.add(R.id.rlContent, mTodoFragment);
                } else {
                    mFragmentTransaction.show(mTodoFragment);
                }
                break;
            case 4:
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    mFragmentTransaction.add(R.id.rlContent, mMeFragment);
                } else {
                    mFragmentTransaction.show(mMeFragment);
                }
                break;
        }
        //提交事务
        mFragmentTransaction.commit();
    }


    /**
     * 用来隐藏fragment的方法
     *
     * @param fragmentTransaction
     */
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        //如果此fragment不为空的话就隐藏起来
        if (mHomeFragment != null) {
            fragmentTransaction.hide(mHomeFragment);
        }
        if (mFunctionFragment != null) {
            fragmentTransaction.hide(mFunctionFragment);
        }
        if (mTodoFragment != null) {
            fragmentTransaction.hide(mTodoFragment);
        }
        if (mSearchFragment != null) {
            fragmentTransaction.hide(mSearchFragment);
        }
        if (mMeFragment != null) {
            fragmentTransaction.hide(mMeFragment);
        }
    }

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

}
