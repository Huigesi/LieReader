package com.example.administrator.liereader.Movie.Presenter;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.Bean.NewsBean;
import com.example.administrator.liereader.Http.Api;
import com.example.administrator.liereader.Movie.Model.IMoviesModel;
import com.example.administrator.liereader.Movie.Model.IOnLoadListener;
import com.example.administrator.liereader.Movie.Model.MoviesModel;
import com.example.administrator.liereader.Movie.View.IMoviesView;
import com.example.administrator.liereader.News.FgNewsFragment;

/**
 * Created by Administrator on 2018/5/19.
 */

public class MoviesPresenter implements IMoviesPresenter,IOnLoadListener {

    private IMoviesModel iMoviesModel;
    private IMoviesView iMoviesView;

    public MoviesPresenter(IMoviesView iMoviesView) {
        this.iMoviesView = iMoviesView;
        this.iMoviesModel =new MoviesModel();
    }


    @Override
    public void success(MoviesBean moviesBean) {
        iMoviesView.hideDialog();
        if (moviesBean.getStart()==0){
            iMoviesView.showMovie(moviesBean);
        }else {
            if (moviesBean.getSubjects().size()==0){
                iMoviesView.showErrorMsg("没有更多了......");
            }else{
                iMoviesView.showMore(moviesBean);
            }
        }


    }

    @Override
    public void fail(String throwable) {
        iMoviesView.hideDialog();
        iMoviesView.showErrorMsg(throwable);
    }

    @Override
    public void loadMovie(String total, int start) {
        if (start==0){
            iMoviesView.showDialog();
        }
        iMoviesModel.loadNews(total,start,this);
    }

}
