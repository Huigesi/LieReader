package com.example.administrator.liereader.Movie.Model;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.IBaseOnLoadListener;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesModel<T> {
    void loadNews(String total,int start, IBaseOnLoadListener<T> iBaseOnLoadListener);
}
