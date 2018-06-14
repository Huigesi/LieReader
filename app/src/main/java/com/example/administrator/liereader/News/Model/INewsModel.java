package com.example.administrator.liereader.News.Model;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface INewsModel {
    void loadNews(String hostType,int startPage,String id,IOnLoadListener iOnLoadListener);
}
