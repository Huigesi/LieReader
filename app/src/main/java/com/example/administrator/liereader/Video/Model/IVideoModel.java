package com.example.administrator.liereader.Video.Model;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19.
 */

public interface IVideoModel {
    void loadVideo(String category, Boolean first,IOnLoadListener iOnLoadListener);

}
