package com.example.factorygeneral.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 描述：MVP 基础activity
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    /**
     * contentView
     */
    protected View root;
    /**
     * 代理类
     */
    protected LayoutInflater mInflate;
    private Unbinder mUnBinder = null;
    private ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove saved fragment, will new fragment in mPagerAdapter
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        MyApplication.mContext=this;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
        root = LayoutInflater.from(this).inflate(getLayoutId(), null);
        mInflate = LayoutInflater.from(this);
        this.setContentView(root);
        mUnBinder = ButterKnife.bind(this);
        initView();
        initDataAndEvent();
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                root.setFocusable(true);
                root.setFocusableInTouchMode(true);
                root.requestFocus();
                return false;
            }
        });
    }



    protected abstract void initView();

    /**
     * 获取contentViewId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 获取代理对象
     *
     * @return
     */

    /**
     * 初始化数据和事件
     */
    protected abstract void initDataAndEvent();


    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
        }catch (Exception o){

        }

        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
