package com.example.factorygeneral.base;


/**
 * 描述：
 */
public class BasePresenter<V extends IView> implements IPresenter<V>{

    public V mView;


    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public IView getView(){
        return mView;
    }




}
