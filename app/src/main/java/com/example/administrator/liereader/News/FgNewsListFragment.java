package com.example.administrator.liereader.News;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.InCallService;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.News.Presenter.NewsPresenter;
import com.example.administrator.liereader.News.View.INewsView;
import com.example.administrator.liereader.R;

import java.util.List;
import java.util.TimerTask;


public class FgNewsListFragment extends Fragment implements INewsView {
    private static final String TAG = "FgNewsListFragment";
    private NewsPresenter presenter;
    private int type;
    private SwipeRefreshLayout srl_news;
    private RecyclerView rv_news;
    private ItemNewsAdapter adapter;
    private List<NewsBean.Bean> newsBeanList;
    private LinearLayoutManager layoutManager;
    private  int startPage=0;

    public static FgNewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_news_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");
        presenter = new NewsPresenter(this);
        rv_news = view.findViewById(R.id.rv_news);
        adapter = new ItemNewsAdapter(getActivity(), type);
        srl_news = view.findViewById(R.id.srl_news);
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(type, 0);

            }
        });
        rv_news.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager.findLastVisibleItemPosition() + 1)==layoutManager.getItemCount()) {
                    loadMore();
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        presenter.loadNews(type, 0);
    }

    private void loadMore() {
        startPage+=20;
        presenter.loadNews(type,startPage);
    }


    @Override
    public void showNews(final NewsBean newsBean) {
        switch (type) {
            case FgNewsFragment.NEWS_TYPE_TOP:
                newsBeanList = newsBean.getTop();
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                newsBeanList = newsBean.getNba();
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                newsBeanList = newsBean.getJoke();
                break;
        }
        adapter.setData(newsBeanList);
        layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_news.setLayoutManager(layoutManager);
        rv_news.setAdapter(adapter);

    }

    @Override
    public void showMoreNews(NewsBean newsBean) {
        switch (type){
            case FgNewsFragment.NEWS_TYPE_TOP:
                adapter.addData(newsBean.getTop());
                break;
            case FgNewsFragment.NEWS_TYPE_NBA:
                adapter.addData(newsBean.getNba());
                break;
            case FgNewsFragment.NEWS_TYPE_JOKES:
                adapter.addData(newsBean.getJoke());
                break;
        }

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
        Toast.makeText(getContext(), "加载出错:"+throwable, Toast.LENGTH_SHORT).show();

    }
}
