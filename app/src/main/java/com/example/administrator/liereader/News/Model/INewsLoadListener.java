package com.example.administrator.liereader.News.Model;

import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.IBaseOnLoadListener;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface INewsLoadListener extends IBaseOnLoadListener<NewsBean>{
    void loadMoreSuccess(NewsBean newsBean);
}
