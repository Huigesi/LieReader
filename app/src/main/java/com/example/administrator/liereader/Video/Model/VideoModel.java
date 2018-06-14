package com.example.administrator.liereader.Video.Model;

import android.util.Log;

import com.example.administrator.liereader.Bean.VideoBean;
import com.example.administrator.liereader.Http.Api;
import com.example.administrator.liereader.Http.RetrofitHelper;
import com.example.administrator.liereader.JsonUtil;
import com.example.administrator.liereader.MainUrlBean;
import com.example.administrator.liereader.TodayContentBean;
import com.example.administrator.liereader.Video.Presenter.VideoPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/19.
 */

public class VideoModel implements IVideoModel {
    private String TAG = "VideoModel";

    @Override
    public void loadVideo(String category, final Boolean first, final IVideoLoadListener iVideoLoadListener) {
        final List<MainUrlBean> videoList = new ArrayList<>();
        final List<TodayContentBean> contentBeans = new ArrayList<>();
        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.TODAY_HOST);

        retrofitHelper.getToday(category)
                .flatMap(new Func1<VideoBean, Observable<MainUrlBean>>() {
                    @Override
                    public Observable<MainUrlBean> call(VideoBean videoBean) {
                        return Observable.from(videoBean.getData())
                                .flatMap(new Func1<VideoBean.DataBean, Observable<MainUrlBean>>() {
                                    @Override
                                    public Observable<MainUrlBean> call(VideoBean.DataBean dataBean) {
                                        String content = dataBean.getContent();
                                        TodayContentBean contentBean = JsonUtil.getTodayContentBean(content);
                                        contentBeans.add(contentBean);
                                        String api = VideoPresenter.getVideoContentApi(contentBean.getVideo_id());
                                        return retrofitHelper.getMainUrl(api);
                                    }
                                });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MainUrlBean>() {
                    @Override
                    public void onCompleted() {
                        iVideoLoadListener.mainUrlSuccess(videoList, contentBeans,first);
                        Log.i(TAG, "onCompleted: " + videoList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iVideoLoadListener.fail(e.getMessage());
                    }

                    @Override
                    public void onNext(MainUrlBean mainUrlBean) {
                        videoList.add(mainUrlBean);
                    }
                });
    }
}
