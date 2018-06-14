package com.example.administrator.liereader.Video.Presenter;

import android.util.Base64;
import android.util.Log;

import com.example.administrator.liereader.MainUrlBean;
import com.example.administrator.liereader.TodayContentBean;
import com.example.administrator.liereader.Video.Model.IOnLoadListener;
import com.example.administrator.liereader.Video.Model.IVideoModel;
import com.example.administrator.liereader.Video.Model.VideoModel;
import com.example.administrator.liereader.Video.View.IVideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.CRC32;

/**
 * Created by Administrator on 2018/5/19.
 */

public class VideoPresenter implements IVideoPresenter, IOnLoadListener {
    private static final String TAG = "VideoPresenter";
    private IVideoModel iVideoModel;
    private IVideoView iVideoView;
    //private List<TodayContentBean> todayContentBeans = new ArrayList<>();


    public VideoPresenter(IVideoView iVideoView) {
        this.iVideoView = iVideoView;
        this.iVideoModel = new VideoModel();
    }


    @Override
    public void mainUrlSuccess(List<MainUrlBean> mainUrlBeans,List<TodayContentBean> contentBeans,Boolean first) {
        List<String> videoList = new ArrayList<>();
        iVideoView.hideDialog();
        for (int i=0;i<mainUrlBeans.size();i++) {
            Log.i(TAG, "mainUrlSuccess: "+mainUrlBeans.get(i).getData().getVideo_list().getVideo_1().getMain_url());
            String mainUrl =mainUrlBeans.get(i).getData().getVideo_list().getVideo_1().getMain_url();
            final String url1 = (new String(Base64.decode(mainUrl.getBytes(), Base64.DEFAULT)));
            videoList.add(url1);
        }
        if (first){
            iVideoView.showVideo(contentBeans,videoList);
        }else {
            iVideoView.showMore(contentBeans,videoList);
        }

    }

    @Override
    public void fail(String throwable) {
        iVideoView.hideDialog();
        iVideoView.showErrorMsg(throwable);
    }


    @Override
    public void loadVideo(Boolean first) {
        if (first==true){
            iVideoView.showDialog();
        }

        iVideoModel.loadVideo("video",first, this);
    }

    public static String getVideoContentApi(String videoid) {
        String VIDEO_HOST = "http://ib.365yg.com";
        String VIDEO_URL = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
        String r = getRandom();
        String s = String.format(VIDEO_URL, videoid, r);
        CRC32 crc32 = new CRC32();
        crc32.update(s.getBytes());
        String crcString = crc32.getValue() + "";
        String url = VIDEO_HOST + s + "&s=" + crcString;
        return url;
    }

    public static String getRandom() {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
