package com.example.administrator.liereader.News.Model;

import com.example.administrator.liereader.Bean.NewsBean;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IOnLoadListener {
    void success(NewsBean newsBean);

    void loadMoreSuccess(NewsBean newsBean);

    void fail(String throwable);
}
