package com.example.administrator.liereader.Movie.View;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.IBaseView;
import com.example.administrator.liereader.Movie.Presenter.IMoviesPresenter;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesView extends IBaseView{
    void showMovie(MoviesBean moviesBean);
    void showMoreMovie (MoviesBean moviesBean);

}
