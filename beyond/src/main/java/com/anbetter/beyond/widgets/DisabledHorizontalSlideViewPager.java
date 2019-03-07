package com.anbetter.beyond.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止水平滚动的ViewPager
 * <p>
 * Created by android_ls on 2018/11/14.
 *
 * @author 李松
 * @version 1.0
 */
public class DisabledHorizontalSlideViewPager extends ViewPager {

    private boolean isScrollable;

    public DisabledHorizontalSlideViewPager(Context context) {
        super(context);
    }

    public DisabledHorizontalSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            if (!isScrollable) {
                return false;
            } else {
                return super.onTouchEvent(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            if (!isScrollable) {
                return false;
            } else {
                return super.onInterceptTouchEvent(ev);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }

}
