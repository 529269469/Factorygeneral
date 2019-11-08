package com.example.factorygeneral.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/15 12
 */
public class ContentViewPager extends ViewPager {
    public ContentViewPager(Context context) {
        super(context);
    }
    public ContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                MeasureSpec.EXACTLY);

        if (heightMeasureSpec==0){
            heightMeasureSpec=300;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

}
