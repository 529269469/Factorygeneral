package com.example.factorygeneral.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 */
public abstract class LazyFragment extends Fragment {
    // 标志位，标志Fragment已经初始化完成。
    protected boolean isPrepared = false;
    protected boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        isPrepared = false;
        try {
            super.onDestroyView();
        }catch (Exception o){

        }

    }

    protected abstract void lazyLoad();

    protected void onInvisible() {

    }


}
