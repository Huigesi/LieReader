package com.example.administrator.liereader.Video;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.liereader.News.ItemNewsAdapter;
import com.example.administrator.liereader.R;
import com.example.administrator.liereader.TodayContentBean;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class ItemVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TodayContentBean> objects = new ArrayList<TodayContentBean>();
    private List<String> videoList = new ArrayList<>();

    private Context context;

    public ItemVideoAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<TodayContentBean> objects,List<String> videoList) {
        this.objects = objects;
        this.videoList = videoList;
    }
    public void addData(List<TodayContentBean> newObjects,List<String> newVideoList) {
        this.objects.addAll(newObjects);
        this.videoList.addAll(newVideoList);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer, parent, false);
            return new FooterHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_video, parent, false);
            return new ItemsViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemsViewHolder){
            TodayContentBean bean=objects.get(position);
            ((ItemsViewHolder)holder).videoPlayer.setUp(videoList.get(position),
                    JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                    bean.getTitle());

            Glide.with(context)
                    .load(bean.getVideo_detail_info().getDetail_video_large_image().getUrl())
                    .into( ((ItemsViewHolder)holder).videoPlayer.thumbImageView);
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


    protected class ItemsViewHolder extends RecyclerView.ViewHolder{
        private JZVideoPlayerStandard videoPlayer;

        public ItemsViewHolder(View view) {
            super(view);
            videoPlayer = (JZVideoPlayerStandard) view.findViewById(R.id.video_player);
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
