package com.example.administrator.liereader.Video.View;

import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.Bean.VideoBean;
import com.example.administrator.liereader.IBaseView;
import com.example.administrator.liereader.TodayContentBean;
import com.example.administrator.liereader.Video.Presenter.IVideoPresenter;
import com.example.administrator.liereader.Video.Presenter.VideoPresenter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IVideoView extends IBaseView{

    void showData(List<TodayContentBean> todayContentBeans,List<String> videoList);

    void showMoreData(List<TodayContentBean> todayContentBeans, List<String> videoList);
}
