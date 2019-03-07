package com.anbetter.beyond.actionbar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.anbetter.beyond.R;

/**
 * Created by android_ls on 2017/3/22.
 */

public class NavActionBar extends FrameLayout {

    private FrameLayout mActionbarBack;
    private FrameLayout mActionbarRightImage;
    private TextView mActionbarLeft;
    private TextView mActionbarTitle;
    private TextView mActionbarRight;
    private View actionbar;
    private ImageView iv_more;

    public NavActionBar(@NonNull Context context) {
        super(context);
        setupViews(context);
    }

    public NavActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupViews(context);
    }

    public void setupViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.actionbar_layout, this, true);
        mActionbarBack = (FrameLayout) findViewById(R.id.actionbar_back);
        mActionbarRightImage = (FrameLayout) findViewById(R.id.actionbar_right_image);
        mActionbarLeft = (TextView) findViewById(R.id.actionbar_left);
        mActionbarTitle = (TextView) findViewById(R.id.actionbar_title);
        mActionbarRight = (TextView) findViewById(R.id.actionbar_right);
        actionbar = findViewById(R.id.ll_actionbar);
        iv_more = findViewById(R.id.iv_more);

        mActionbarLeft.setVisibility(View.GONE);
        mActionbarRight.setVisibility(View.GONE);
    }

    public void setBackgroundColor(int color) {
        if (actionbar != null) {
            actionbar.setBackgroundResource(R.drawable.transparent);
            actionbar.setBackgroundColor(color);
        }
    }

    public void setBackgroundResource(int resId) {
        if (actionbar != null) {
            actionbar.setBackgroundResource(resId);
        }
    }

    public void setTextColor(String color) {
        if (mActionbarTitle != null) {
            mActionbarTitle.setTextColor(Color.parseColor(color));
        }
    }

    /**
     * 导航栏上要显示的Title
     *
     * @param title
     */
    public void setActionBarTitle(CharSequence title) {
        mActionbarTitle.setText(title);
    }

    /**
     * 导航栏上是否要显示返回图标
     *
     * @param displayBack
     */
    public void displayActionBarBack(boolean displayBack) {
        mActionbarBack.setVisibility(displayBack ? View.VISIBLE : View.GONE);
        mActionbarLeft.setVisibility(displayBack ? View.GONE : View.VISIBLE);
        mActionbarBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigator.goBack();
            }
        });
    }

    public void displayActionBarBack(OnClickListener onClickListener) {
        mActionbarBack.setVisibility(View.VISIBLE);
        mActionbarLeft.setVisibility(View.GONE);
        mActionbarBack.setOnClickListener(onClickListener);
    }

    public void displayActionBarRightImage(OnClickListener onClickListener) {
        mActionbarRightImage.setVisibility(View.VISIBLE);
        mActionbarRight.setVisibility(View.GONE);
        mActionbarRightImage.setOnClickListener(onClickListener);
    }

    public void displayActionBarRightImage(int resId, OnClickListener onClickListener) {
        iv_more.setImageResource(resId);
        mActionbarRightImage.setVisibility(View.VISIBLE);
        mActionbarRight.setVisibility(View.GONE);
        mActionbarRightImage.setOnClickListener(onClickListener);
    }

    /**
     * 显示左侧的文字
     *
     * @param title
     * @param listener
     */
    public void displayActionBarLeftText(CharSequence title, OnClickListener listener) {
        mActionbarBack.setVisibility(View.GONE);
        mActionbarLeft.setVisibility(View.VISIBLE);
        mActionbarLeft.setText(title);
        if (listener != null) {
            mActionbarLeft.setOnClickListener(listener);
        }
    }

    /**
     * 显示右侧文字
     *
     * @param title
     * @param listener
     */
    public void displayActionBarRightText(CharSequence title, OnClickListener listener) {
        mActionbarRight.setVisibility(View.VISIBLE);
        mActionbarRight.setText(title);
        if (listener != null) {
            mActionbarRight.setOnClickListener(listener);
        }
    }

    public void showActionBarRightText(boolean display) {
        mActionbarRight.setVisibility(display ? View.VISIBLE : View.GONE);
    }

}
