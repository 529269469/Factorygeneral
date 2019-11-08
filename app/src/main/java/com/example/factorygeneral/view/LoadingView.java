package com.example.factorygeneral.view;//package com.example.acceptance.view;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//
//import androidx.annotation.AttrRes;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.acceptance.R;
//
///**
// * @author :created by ${ WYW }
// * 时间：2019/10/17 18
// */
//public class LoadingView extends FrameLayout {
//    private int emptyLayoutId, errorLayoutId, loadingLayoutId;
//    private View contentView, emptyView, errorView, loadingView;
//    private LayoutInflater mInflater;
//    private SparseArray<View> views = new SparseArray<>();
//
//    public LoadingView(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadLayout);
//        emptyLayoutId = a.getResourceId(R.styleable.LoadLayout_emptyView, R.layout.loading_empty);
//        errorLayoutId = a.getResourceId(R.styleable.LoadLayout_errorView, R.layout.loading_error);
//        loadingLayoutId = a.getResourceId(R.styleable.LoadLayout_loadingView, R.layout.loading_load);
//        mInflater = LayoutInflater.from(getContext());
//        a.recycle();
//        loadingView = mInflater.inflate(loadingLayoutId, null);
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        if (getChildCount() < 1) {
//            throw new RuntimeException("content view can not be null");
//        }
//        contentView = getChildAt(0);
//
//        if (loadingView.getVisibility() != GONE)
//            loadingView.setVisibility(GONE);
//        addView(loadingView);
//        views.put(loadingLayoutId, loadingView);
//    }
//
//    public void showError() {
//        errorView = views.get(errorLayoutId);
//        if (errorView == null) {
//            errorView = mInflater.inflate(errorLayoutId, null);
//            addView(errorView);
//            views.put(errorLayoutId, errorView);
//            final ImageView errorRetry = (ImageView) errorView.findViewById(R.id.error_retry);
//            errorRetry.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mOnRetryClickListener != null)
//                        mOnRetryClickListener.onClick(errorRetry);
//                }
//            });
//        }
//        if (errorView.getVisibility() != VISIBLE)
//            errorView.setVisibility(VISIBLE);
//
//        for (int i = 0, len = views.size(); i < len; i++) {
//            int key = views.keyAt(i);
//            if (key != errorLayoutId) {
//                View view = views.valueAt(i);
//                if (view != null)
//                    if (view.getVisibility() != GONE)
//                        view.setVisibility(GONE);
//            }
//        }
//    }
//
//    public void showEmpty() {
//        emptyView = views.get(emptyLayoutId);
//        if (emptyView == null) {
//            emptyView = mInflater.inflate(emptyLayoutId, null);
//            addView(emptyView);
//            views.put(emptyLayoutId, emptyView);
//        }
//        if (emptyView.getVisibility() != VISIBLE)
//            emptyView.setVisibility(VISIBLE);
//
//        for (int i = 0, len = views.size(); i < len; i++) {
//            int key = views.keyAt(i);
//            if (key != emptyLayoutId) {
//                View view = views.valueAt(i);
//                if (view != null)
//                    if (view.getVisibility() != GONE)
//                        view.setVisibility(GONE);
//            }
//        }
//    }
//
//    public void showLoading() {
//        loadingView = views.get(loadingLayoutId);
//        if (loadingView.getVisibility() != VISIBLE)
//            loadingView.setVisibility(VISIBLE);
//
//        for (int i = 0, len = views.size(); i < len; i++) {
//            int key = views.keyAt(i);
//            if (key != loadingLayoutId) {
//                View view = views.valueAt(i);
//                if (view != null)
//                    if (view.getVisibility() != GONE)
//                        view.setVisibility(GONE);
//            }
//        }
//    }
//
//    public void showContent() {
//        for (int i = 0, len = views.size(); i < len; i++) {
//            View view = views.valueAt(i);
//            if (view != null)
//                if (view.getVisibility() != GONE)
//                    view.setVisibility(GONE);
//        }
//    }
//
//    private OnClickListener mOnRetryClickListener;
//
//    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
//        this.mOnRetryClickListener = onRetryClickListener;
//    }
//}
//
