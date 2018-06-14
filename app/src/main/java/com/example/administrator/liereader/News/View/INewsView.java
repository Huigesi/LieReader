package com.example.administrator.liereader.News.View;

import com.example.administrator.liereader.Bean.NewsBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface INewsView {
    void showNews(NewsBean newsBean);

    void showMoreNews(NewsBean newsBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String throwable);
}
