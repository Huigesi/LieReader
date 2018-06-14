package com.example.administrator.liereader.Movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Movie.Presenter.MoviesPresenter;
import com.example.administrator.liereader.Movie.View.IMoviesView;
import com.example.administrator.liereader.R;


public class FgMovieFragment extends Fragment implements IMoviesView {
    private static final String TAG = "FgMovieFragment";
    private MoviesPresenter moviesPresenter;
    private SwipeRefreshLayout srl_news;
    private RecyclerView rv_movie_hot;
    private ItemMovieAdapter adapter;
    private ItemMovieTop250Adapter adapter2;
    private RecyclerView rv_movie_top250;
    private LinearLayoutManager layoutManager_top250,layoutManager_on;
    private int start=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter = new MoviesPresenter(this);
        rv_movie_hot = view.findViewById(R.id.rv_movie_hot);
        rv_movie_top250 = view.findViewById(R.id.rv_movie_top250);
        srl_news = view.findViewById(R.id.srl_news);
        adapter = new ItemMovieAdapter(getActivity());
        adapter2 = new ItemMovieTop250Adapter(getActivity());
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        moviesPresenter.loadMovie("top250",0);
        moviesPresenter.loadMovie("in_theaters",0);
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovie("top250",0);
                moviesPresenter.loadMovie("in_theaters",0);
            }
        });
        rv_movie_hot.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState==RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager_on.findLastVisibleItemPosition() + 1)==layoutManager_on.getItemCount()) {
                    start+=20;
                    moviesPresenter.loadMovie("in_theaters",start);
                }
            }
        });
    }

    @Override
    public void showMovie(MoviesBean moviesBean) {
        if (moviesBean.getTotal()==250){
            adapter2.setData(moviesBean.getSubjects());
            layoutManager_top250=new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,false);
            rv_movie_top250.setHorizontalScrollBarEnabled(true);
            rv_movie_top250.setLayoutManager(layoutManager_top250);
            rv_movie_top250.setAdapter(adapter2);
        }else{
            adapter.setData(moviesBean.getSubjects());
            layoutManager_on=new LinearLayoutManager(getActivity());
            rv_movie_hot.setLayoutManager(layoutManager_on);
            rv_movie_hot.setAdapter(adapter);
        }


    }

    @Override
    public void showMore(MoviesBean moviesBean) {
            adapter.addData(moviesBean.getSubjects());
            adapter.notifyDataSetChanged();
    }

    @Override
    public void hideDialog() {
        srl_news.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_news.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String throwable) {
        adapter.notifyItemRemoved(adapter.getItemCount());
        Toast.makeText(getContext(), throwable, Toast.LENGTH_LONG).show();
    }

}
