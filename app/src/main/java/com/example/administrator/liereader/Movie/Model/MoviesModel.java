package com.example.administrator.liereader.Movie.Model;

import android.util.Log;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.Http.Api;
import com.example.administrator.liereader.Http.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/19.
 */

public class MoviesModel implements IMoviesModel {


    @Override
    public void loadNews(String total, final int start, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIE_HOST);

        retrofitHelper.getMovies(total, start)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MoviesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iOnLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {
                        iOnLoadListener.success(moviesBean);
                    }
                });
    }
}
