package com.example.factorygeneral.net;


public interface MyCallBack<T> {
    void onSuccess(T t);
    void onFaile(String msg);
}
