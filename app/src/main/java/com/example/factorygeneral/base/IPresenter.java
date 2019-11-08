package com.example.factorygeneral.base;


/**
 * 描述：
 */

public interface IPresenter<V extends IView> {
    void detachView();
}
