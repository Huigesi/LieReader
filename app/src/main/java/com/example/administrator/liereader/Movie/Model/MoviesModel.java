package com.example.administrator.liereader.Movie.Model;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Http.Api;
import com.example.administrator.liereader.Http.RetrofitHelper;
import com.example.administrator.liereader.IBaseOnLoadListener;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/19.
 */

public class MoviesModel implements IMoviesModel<MoviesBean> {
    @Override
    public void loadNews(String total, int start, final IBaseOnLoadListener<MoviesBean> iBaseOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);

        retrofitHelper.getMovies(total, start)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MoviesBean>() {
                    @Override
                    public void onStart() {
                        iBaseOnLoadListener.beforeRequest();
                    }

                    @Override
                    public void onCompleted() {
                        iBaseOnLoadListener.completeRequest();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iBaseOnLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {
                        iBaseOnLoadListener.success(moviesBean);
                    }
                });
    }

/*
    @Override
    public void loadNews(String total, final int start, final IBaseOnLoadListener iBaseOnLoadListener) {

    }*/
}
