package com.example.administrator.liereader.Video.View;

import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.Bean.VideoBean;
import com.example.administrator.liereader.TodayContentBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans,List<String> videoList);

    void showMore(List<TodayContentBean> todayContentBeans, List<String> videoList);

    void hideDialog();
    void showDialog();
    void showErrorMsg(String throwable);
}
