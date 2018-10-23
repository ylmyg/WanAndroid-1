package com.geaosu.wanandroid.adapter;

import android.app.Activity;
import android.support.v7.view.menu.ActionMenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArticleListAdapter extends BaseAdapter {

    private List<String> mData = new ArrayList<>();
    private Activity mActivity;

    public ArticleListAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void replaceAll(List<String> list) {
        if (list != null && list.size() > 0) {
            mData.clear();
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
