package com.example.administrator.liereader.News.View;

import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.IBaseView;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface INewsView extends IBaseView{
    void showNews(NewsBean newsBean);

    void showMoreNews(NewsBean newsBean);
}
