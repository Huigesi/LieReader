package com.example.administrator.liereader.Movie.Model;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IMoviesModel {
    void loadNews(String total,int start, IOnLoadListener iOnLoadListener);
}
