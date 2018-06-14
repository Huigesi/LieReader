package com.example.administrator.liereader.Movie.Model;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IOnLoadListener {
    void success(MoviesBean moviesBean);

    void fail(String throwable);
}
