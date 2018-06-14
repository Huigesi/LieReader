package com.example.administrator.liereader;

public interface IBaseOnLoadListener<T> {
    void success(T data);
    void beforeRequest();
    void completeRequest();
    void fail(String error);
}
