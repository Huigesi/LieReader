package com.example.administrator.liereader.Movie;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.liereader.AcDetailActivity;
import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.R;

public class ItemMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ItemMovieAdapter";
    private List<MoviesBean.SubjectsBean> objects = new ArrayList<MoviesBean.SubjectsBean>();
    public static int TYPE_MORE = 1;

    private Context context;

    public ItemMovieAdapter( Context context) {

        this.context = context;
    }
    public void setData(List<MoviesBean.SubjectsBean> objects){
        this.objects = objects;
    }
    public void addData(List<MoviesBean.SubjectsBean> list){
        objects.addAll(list);
        Log.i(TAG, "addData: "+list.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return TYPE_MORE;
        }else {
            return 0;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_MORE){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer, parent, false);
            return new FooterHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_movie, parent, false);
            return new ItemsViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemsViewHolder){
            final MoviesBean.SubjectsBean bean=objects.get(position);
            if (bean==null){
                return;
            }
            Glide.with(context)
                    .load(bean.getImages().getSmall())
                    .into(((ItemsViewHolder)holder).ivMovieOn);
            ((ItemsViewHolder)holder).tvMovieOnTitle.setText(bean.getTitle());
            String directors="";
            for(int i=0;i<bean.getDirectors().size();i++){
                if (i==bean.getDirectors().size()-1){
                    directors+=bean.getDirectors().get(i).getName();
                }else{
                    directors+=bean.getDirectors().get(i).getName()+"/";
                }
            }
            ((ItemsViewHolder)holder).tvMovieOnDirectors.setText("导演："+directors);
            String casts="";

            if (bean.getCasts().size()!=0){
                for(int i=0;i<bean.getCasts().size();i++){
                    if (i==bean.getCasts().size()-1){
                        casts+=bean.getCasts().get(i).getName();
                    }else{
                        casts+=bean.getCasts().get(i).getName()+"/";
                    }
                }
                ((ItemsViewHolder)holder).tvMovieOnCasts.setText(casts);
            }else {
                ((ItemsViewHolder)holder).tvMovieOnCasts.setText("主演：佚名");
            }

            String gen="";
            for(int i=0;i<bean.getGenres().size();i++){
                if (i==bean.getGenres().size()-1){
                    gen+=bean.getGenres().get(i);
                }else{
                    gen+=bean.getGenres().get(i)+"/";
                }
            }
            ((ItemsViewHolder)holder).tvMovieOnGenres.setText("类型："+gen);
            ((ItemsViewHolder)holder).tvMovieOnRating.setText("评分："+bean.getRating().getAverage());
            ((ItemsViewHolder)holder).rvMovieOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AcDetailActivity.class);
                    intent.putExtra("url",bean.getAlt());
                    intent.putExtra("title", bean.getTitle());
                    context.startActivity(intent);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected class ItemsViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMovieOn;
        private TextView tvMovieOnTitle;
        private TextView tvMovieOnDirectors;
        private TextView tvMovieOnCasts;
        private TextView tvMovieOnGenres;
        private TextView tvMovieOnRating;
        private RelativeLayout rvMovieOn;

        public ItemsViewHolder(View view) {
            super(view);
            ivMovieOn = (ImageView) view.findViewById(R.id.iv_movie_on);
            tvMovieOnTitle = (TextView) view.findViewById(R.id.tv_movie_on_title);
            tvMovieOnDirectors = (TextView) view.findViewById(R.id.tv_movie_on_directors);
            tvMovieOnCasts = (TextView) view.findViewById(R.id.tv_movie_on_casts);
            tvMovieOnGenres = (TextView) view.findViewById(R.id.tv_movie_on_genres);
            tvMovieOnRating = (TextView) view.findViewById(R.id.tv_movie_on_rating);
            rvMovieOn = (RelativeLayout) view.findViewById(R.id.rl_movie_on);
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
