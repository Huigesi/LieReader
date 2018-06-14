package com.example.administrator.liereader.Movie.View;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesView {
    void showMovie(MoviesBean moviesBean);
    void showMore(MoviesBean moviesBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String throwable);
}
