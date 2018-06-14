package com.example.administrator.liereader.Video.Model;

import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.Bean.VideoBean;
import com.example.administrator.liereader.MainUrlBean;
import com.example.administrator.liereader.TodayContentBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IOnLoadListener {
    void mainUrlSuccess(List<MainUrlBean> mainUrlBeans, List<TodayContentBean> contentBeans,Boolean first);
    void fail(String throwable);
}
