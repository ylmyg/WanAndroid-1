package com.geaosu.wanandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.event.ClickEvent;
import com.geaosu.wanandroid.event.DataEvent;
import com.geaosu.wanandroid.event.LoginEvent;

public class MeFragment extends BaseFragment {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivMenu;

    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(View fragmentView) {

    }

    @Override
    protected void initViewListener() {

    }

    @Override
    protected void initTitle(View fragmentView) {
        ivBack = (ImageView) fragmentView.findViewById(R.id.ivBack);
        tvTitle = (TextView) fragmentView.findViewById(R.id.tvTitle);
        ivMenu = (ImageView) fragmentView.findViewById(R.id.ivMenu);

        tvTitle.setText(getResources().getString(R.string.me));
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
}
