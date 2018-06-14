package com.example.administrator.liereader.News;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.liereader.AcDetailActivity;
import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.R;

public class ItemNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ItemNewsAdapter";
    private List<NewsBean.Bean> objects = new ArrayList<NewsBean.Bean>();

    private Context context;
    private int type;
    public static int TYPE_MORE = 1;


    public ItemNewsAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setData(List<NewsBean.Bean> objects) {
        this.objects = objects;

    }

    public void addData(List<NewsBean.Bean> newObjects) {
        objects.addAll(newObjects);
        Log.i("ItemNewsAdapter", "addData: " + objects.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_MORE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer, parent, false);

            return new FooterHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new ItemsViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemsViewHolder) {
            final NewsBean.Bean bean = objects.get(position);
            if (bean == null) {
                return;
            }
            Glide.with(context)
                    .load(bean.getImgsrc())
                    .into(((ItemsViewHolder) holder).ivNewsImg);
            if (position == 0) {
                ((ItemsViewHolder) holder).tvNewsDigest.setVisibility(View.GONE);
                ((ItemsViewHolder) holder).tvNewsTitle.setText("图片：" + bean.getTitle());
            } else {
                ((ItemsViewHolder) holder).tvNewsTitle.setText(bean.getTitle());
                ((ItemsViewHolder) holder).tvNewsDigest.setText(bean.getMtime() + " : " + bean.getDigest());
                ((ItemsViewHolder) holder).cvNews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, AcDetailActivity.class);
                        intent.putExtra("url", bean.getUrl());
                        intent.putExtra("title", bean.getTitle());
                        Log.i("url", "onClick: " + bean.getUrl());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_MORE;
        } else {
            return 0;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    protected class ItemsViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivNewsImg;
        private TextView tvNewsTitle;
        private TextView tvNewsDigest;
        private CardView cvNews;

        public ItemsViewHolder(View view) {
            super(view);
            ivNewsImg = (ImageView) view.findViewById(R.id.iv_news_img);
            tvNewsTitle = (TextView) view.findViewById(R.id.tv_news_title);
            tvNewsDigest = (TextView) view.findViewById(R.id.tv_news_digest);
            cvNews = view.findViewById(R.id.cv_news);
        }
    }

    protected class FooterHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_footer;

        public FooterHolder(View itemView) {
            super(itemView);
            ll_footer = itemView.findViewById(R.id.ll_footer);
        }
    }

}
