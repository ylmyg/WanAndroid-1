package com.geaosu.wanandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geaosu.wanandroid.R;
import com.geaosu.wanandroid.model.HomeArticleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页文章列表的数据适配器
 */
public class ArticleListAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<HomeArticleModel.DataBean.DatasBean> mData = new ArrayList<>();

    public ArticleListAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void replaceAll(List<HomeArticleModel.DataBean.DatasBean> list) {
        if (list != null && list.size() > 0) {
            mData.clear();
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_article_list_item_view, null);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.ivSetTop = (ImageView) convertView.findViewById(R.id.ivSetTop);
            holder.tvTagName = (TextView) convertView.findViewById(R.id.tvTagName);
            holder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            holder.tvChapter = (TextView) convertView.findViewById(R.id.tvChapter);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            holder.ivNew = (ImageView) convertView.findViewById(R.id.ivNew);
            holder.ivCollect = (ImageView) convertView.findViewById(R.id.ivCollect);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeArticleModel.DataBean.DatasBean bean = mData.get(position);
        if (bean != null) {
            holder.tvTitle.setText(bean.getTitle());
            //TODO 置顶
            holder.ivSetTop.setVisibility(View.GONE);

            List<HomeArticleModel.DataBean.DatasBean.TagsBean> tags = bean.getTags();
            if (tags != null && tags.size() > 0) {
                HomeArticleModel.DataBean.DatasBean.TagsBean tagsBean = tags.get(0);
                if (tagsBean != null) {
                    holder.tvTagName.setText(tagsBean.getName());
                }
            }

            holder.tvAuthor.setText(bean.getAuthor());
            holder.tvChapter.setText(bean.getSuperChapterName() + "/" + bean.getChapterName());
            holder.tvTime.setText(bean.getNiceDate());
            if (bean.isFresh()) {
                holder.ivNew.setVisibility(View.VISIBLE);
            } else {
                holder.ivNew.setVisibility(View.GONE);
            }
            if (bean.isCollect()) {
                holder.ivCollect.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.collect_red_001));
            } else {
                holder.ivCollect.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.collect_gray_001));
            }
            holder.ivCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "还没登录呢", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView tvTitle;       //标题
        private ImageView ivSetTop;     //置顶
        private TextView tvTagName;     //项目, 公众号
        private TextView tvAuthor;      //作者
        private TextView tvChapter;     //分类
        private TextView tvTime;        //时间
        private ImageView ivNew;        //最新
        private ImageView ivCollect;    //收藏
    }
}
