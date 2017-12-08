package com.trident.beyond.widgets;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 滚动监听，
 *
 * Created by android_ls on 2017/3/21.
 */

public class BWebView extends WebView {

    public OnScrollChangeListener mOnScrollChangeListener;

    public BWebView(Context context) {
        super(context);
    }

    public BWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollChangeListener(OnScrollChangeListener scrollChangeListener) {
        this.mOnScrollChangeListener = scrollChangeListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangeListener != null) {
            mOnScrollChangeListener.onScrollChange(l, t, oldl, oldt);
        }
    }

    public interface OnScrollChangeListener {
        void onScrollChange(int l, int t, int oldl, int oldt);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

}
