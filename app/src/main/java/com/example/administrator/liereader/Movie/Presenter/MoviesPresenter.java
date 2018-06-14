package com.example.administrator.liereader.Movie.Presenter;

import com.example.administrator.liereader.Bean.MoviesBean;
import com.example.administrator.liereader.IBaseOnLoadListener;
import com.example.administrator.liereader.Movie.Model.IMoviesModel;
import com.example.administrator.liereader.Movie.Model.MoviesModel;
import com.example.administrator.liereader.Movie.View.IMoviesView;

/**
 * Created by Administrator on 2018/5/19.
 */

public class MoviesPresenter implements IMoviesPresenter,IBaseOnLoadListener<MoviesBean>{

    private IMoviesModel iMoviesModel;
    private IMoviesView iMoviesView;

    public MoviesPresenter(IMoviesView iMoviesView) {
        this.iMoviesModel = new MoviesModel();
        this.iMoviesView = iMoviesView;
    }

    @Override
    public void loadMovie(String total, int start) {
        if (start==0){
            iMoviesView.showDialog();
        }
        iMoviesModel.loadNews(total,start,this);
    }

    @Override
    public void success(MoviesBean data) {
        iMoviesView.hideDialog();
        if (data.getStart()==0){
            iMoviesView.showMovie(data);
        }else {
            if (data.getSubjects().size()==0){
                iMoviesView.showErrorMsg("没有更多了......");
            }else{
                iMoviesView.showMoreMovie(data);
            }
        }
    }

    @Override
    public void beforeRequest() {

    }

    @Override
    public void completeRequest() {

    }


    @Override
    public void fail(String error) {
        iMoviesView.hideDialog();
        iMoviesView.showErrorMsg(error);
    }



}
