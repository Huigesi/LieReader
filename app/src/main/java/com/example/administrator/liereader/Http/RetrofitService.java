package com.example.administrator.liereader.Http;

import com.example.administrator.liereader.Bean.LiBean;
import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.Bean.VideoBean;
import com.example.administrator.liereader.MainUrlBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface RetrofitService {
    /*
    * http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    * */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<NewsBean> getNews(@Path("type") String type,
                                 @Path("id") String id,
                                 @Path("startPage") int startPage);
    /*
    * https://api.douban.com/v2/movie/top250
    * */
    @GET("/v2/movie/{total}")
    Observable<MoviesBean> getMovie(@Path("total") String total,
                                    @Query("start") int start);


    /*
    * http://is.snssdk.com/api/news/feed/v51/?category=video
    * */
    @GET("news/feed/v51/")
    Observable<VideoBean> getToday(@Query("category") String category);

    /*
    *http://app.pearvideo.com/clt/jsp/v2/home.jsp?lastLikeIds=1063871%2C1063985%2C1064069%2C1064123%2C1064078%2C1064186%2C1062372%2C1064164%2C1064081%2C1064176%2C1064070%2C1064019
     */
    @GET("clt/jsp/v2/home.jsp")
    Observable<LiBean> getLi(@Query("lastLikeIds") String lastLikeIds);

    /*
    * http://ib.365yg.com/video/urls/v/1/toutiao/mp4/v02004f00000bbpbk3l2v325q7lmkds0?r=6781281688452415&s=2734808831
    * */
    @GET
    Observable<MainUrlBean> getVideoMainUrl(@Url String url);

}
