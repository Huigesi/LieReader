package com.example.administrator.liereader;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/5/26.
 */

public class JsonUtil {
    public static TodayContentBean getTodayContentBean(String content){
        Gson gson=new Gson();
        TodayContentBean bean = gson.fromJson(content, TodayContentBean.class);
        return bean;
    }
}
