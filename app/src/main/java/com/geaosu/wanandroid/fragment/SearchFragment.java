package com.geaosu.wanandroid.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geaosu.wanandroid.BuildConfig;
import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;
import com.geaosu.wanandroid.model.HotKeyModel;
import com.geaosu.wanandroid.net.HotKeyEngine;
import com.geaosu.wanandroid.view.AutoWrapLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchFragment extends BaseFragment {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivMenu;


    private List<HotKeyModel.DataBean> mHotkeyList = new ArrayList<>();     //热词
    private List<String> mHistoryList = new ArrayList<>();                  //历史记录
    private HotkeyClickListener mHotkeyClickListener;                       //
    private HistoryKeyClickListener mHistoryKeyClickListener;               //
    private AutoWrapLayout.WrapAdapter mHistoryListAdapter;
    private AutoWrapLayout.WrapAdapter mHotkeyListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_search;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View fragmentView) {


    }

    @Override
    protected void initViewListener() {
        mHotkeyClickListener = new HotkeyClickListener();
        mHistoryKeyClickListener = new HistoryKeyClickListener();
    }

    @Override
    protected void initTitle(View fragmentView) {
        ivBack = (ImageView) fragmentView.findViewById(R.id.ivBack);
        tvTitle = (TextView) fragmentView.findViewById(R.id.tvTitle);
        ivMenu = (ImageView) fragmentView.findViewById(R.id.ivMenu);

        tvTitle.setText(getResources().getString(R.string.search));
        ivBack.setVisibility(View.GONE);
        ivMenu.setVisibility(View.GONE);
    }

    @Override
    protected void initTitleListener() {

    }

    @Override
    protected void loadData() {
        HotKeyEngine hotKeyEngine = new HotKeyEngine(getRequestTag());
        hotKeyEngine.sendRequest();
    }

    @Override
    protected void onEventMainThread(LoginEvent event) {

    }

    @Override
    protected void onEventMainThread(DataEvent event) {
        switch (event.type) {
            case GET_HOT_KEY_ERROR:

                break;
            case GET_HOT_KEY_SUCCESS:
                try {
                    HotKeyModel hotKeyModel = (HotKeyModel) event.data;
                    if (hotKeyModel != null) {
                        List<HotKeyModel.DataBean> hotKeyList = hotKeyModel.getData();
                        if (hotKeyList != null && hotKeyList.size() > 0) {


                            if (hotKeyList != null && hotKeyList.size() > 0) {
                                mHotkeyList.addAll(hotKeyList);
                                tvNoHotkey.setVisibility(View.GONE);
                                awlHotkeyList.setVisibility(View.VISIBLE);
                            } else {
                                tvNoHotkey.setVisibility(View.VISIBLE);
                                awlHotkeyList.setVisibility(View.GONE);
                            }
                            awlHotkeyList.setAdapter(mHotkeyListAdapter = new AutoWrapLayout.WrapAdapter() {
                                @Override
                                public int getItemCount() {
                                    return mHotkeyList.size();
                                }

                                @Override
                                public TextView onCreateTextView(int index) {
                                    //随机产生颜色值
                                    int red = getNum255();
                                    int green = getNum255();
                                    int blue = getNum255();
                                    while (red == 255 && green == 255 && blue == 255) {//白色看不见, 所以过滤掉
                                        //当条件为true的时候, 里面的代码才会执行
                                        red = getNum255();
                                        green = getNum255();
                                        blue = getNum255();
                                    }
                                    TextView itemTv = (TextView) View.inflate(getActivity(), R.layout.item_wrap_tv, null);
                                    itemTv.setText(mHotkeyList.get(index).getName());
                                    itemTv.setTag(index);
                                    itemTv.setTextColor(Color.rgb(red, green, blue));
                                    itemTv.setOnClickListener(mHotkeyClickListener);
                                    return itemTv;
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    awlHotkeyList.setVisibility(View.GONE);
                    tvNoHotkey.setVisibility(View.VISIBLE);
                    if (BuildConfig.DEBUG) Log.d(getDebugTag(), "err: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            case GET_SEARCH_HOT_KEY_ERROR:

                break;
            case GET_SEARCH_HOT_KEY_SUCCESS:

                break;
        }
    }

    @Override
    protected void onEventMainThread(ClickEvent event) {

    }


    /**
     * 点击事件监听类
     */
    class HotkeyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String key = mHotkeyList.get((Integer) v.getTag()).getName();
            mHistoryList.add(key);
            notifyHistoryListData();
            mPage = 1;
            requestData(key, 1);
        }
    }

    /**
     * 点击事件监听类
     */
    class HistoryKeyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String key = mHistoryList.get((Integer) v.getTag());
            mPage = 1;
            requestData(key, 1);
        }
    }

    /**
     * 刷新历史记录中的数据
     */
    private void notifyHistoryListData() {
        if (mHistoryList == null || mHistoryList.size() <= 0) {
            tvNoHistory.setVisibility(View.VISIBLE);
            awlHistoryList.setVisibility(View.GONE);
        } else {
            //mHistoryListAdapter.notifyAll();
            tvNoHistory.setVisibility(View.GONE);
            awlHistoryList.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 从1到255随机取出一个数
     *
     * @return
     */
    public int getNum255() {
        Random r = new Random();
        int n = r.nextInt(255) + 1;// 范围是[0,255)
        return n;
    }
}
