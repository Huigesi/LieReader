package com.example.administrator.liereader.Video;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.liereader.R;
import com.example.administrator.liereader.TodayContentBean;
import com.example.administrator.liereader.Video.Presenter.IVideoPresenter;
import com.example.administrator.liereader.Video.Presenter.VideoPresenter;
import com.example.administrator.liereader.Video.View.IVideoView;

import java.util.List;


public class FgVideoFragment extends Fragment implements IVideoView {


    private IVideoPresenter iVideoPresenter;
    private RecyclerView rv_video;
    private ItemVideoAdapter itemVideoAdapter;
    private SwipeRefreshLayout srl_video;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_video, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iVideoPresenter = new VideoPresenter(this);
        rv_video = view.findViewById(R.id.rv_video);
        srl_video = view.findViewById(R.id.srl_video);
        srl_video.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        iVideoPresenter.loadVideo(true);
        srl_video.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iVideoPresenter.loadVideo(true);
            }
        });
        itemVideoAdapter = new ItemVideoAdapter(getActivity());
        rv_video.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE&&
                        (layoutManager.findLastVisibleItemPosition()+1)==layoutManager.getItemCount()) {
                    iVideoPresenter.loadVideo(false);
                }
            }
        });
    }


    @Override
    public void showVideo(List<TodayContentBean> todayContentBeans, List<String> videoList) {
        itemVideoAdapter.setData(todayContentBeans, videoList);
         layoutManager=new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_video.setLayoutManager(layoutManager);
        rv_video.setAdapter(itemVideoAdapter);
    }

    @Override
    public void showMore(List<TodayContentBean> todayContentBeans, List<String> videoList) {
        itemVideoAdapter.addData(todayContentBeans,videoList);
        itemVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideDialog() {
        srl_video.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_video.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String throwable) {
        Toast.makeText(getContext(), throwable, Toast.LENGTH_SHORT).show();
    }

}
