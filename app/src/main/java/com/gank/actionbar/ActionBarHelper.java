package com.gank.actionbar;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.fresco.helper.utils.DensityUtil;
import com.gank.R;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by android_ls on 17/1/4.
 */
public class ActionBarHelper {
    private static Map<Integer, SoftReference<Drawable>> sBackgroundCache = new HashMap<>();
    private ActionBar mActionBar;
    private Context mContext;

    private int mCurrentBackgroundAlpha;
    private Drawable mCurrentBackgroundDrawable;

    private TextView actionBarLeftText;
    private TextView actionBarTitle;
    private TextView actionBarRightText;
    private FrameLayout toolbarRightIcon;
    private ImageView ivRightIcon;
    private ImageView ivToolbarTitle;

    public ImageView getIvRightIcon(){
        return ivRightIcon;
    }

    public ActionBarHelper(Context context, ActionBar actionBar, Toolbar toolbar) {
        this.mContext = context;
        this.mActionBar = actionBar;

        mCurrentBackgroundAlpha = 0xff;
        mCurrentBackgroundDrawable = getBackgroundDrawable(context, R.drawable.actionbar_bg);
        mCurrentBackgroundDrawable.setAlpha(mCurrentBackgroundAlpha);
        mActionBar.setBackgroundDrawable(mCurrentBackgroundDrawable);

        mActionBar.setHomeAsUpIndicator(R.drawable.actionbar_back_selector);
        mActionBar.setDisplayShowTitleEnabled(false);

        actionBarLeftText = (TextView) toolbar.findViewById(R.id.toolbar_left);
        actionBarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        actionBarRightText = (TextView) toolbar.findViewById(R.id.toolbar_right);

        toolbarRightIcon = (FrameLayout) toolbar.findViewById(R.id.toolbar_right_icon);
        ivRightIcon = (ImageView) toolbar.findViewById(R.id.iv_right_icon);
        ivToolbarTitle = (ImageView) toolbar.findViewById(R.id.iv_toolbar_title);
    }

    private static Drawable getBackgroundDrawable(Context context, int drawableId) {
        SoftReference<Drawable> ref = sBackgroundCache.get(drawableId);
        if (ref == null || ref.get() == null) {
            Drawable drawable = ContextCompat.getDrawable(context, drawableId);
            LayerDrawable fullBackground = new LayerDrawable(new Drawable[]{drawable}) {

                @Override
                public boolean getPadding(Rect padding) {
                    padding.top = 0;
                    padding.bottom = 0;
                    padding.left = 0;
                    padding.right = 0;
                    return false;
                }
            };
            ref = new SoftReference<Drawable>(fullBackground);
            sBackgroundCache.put(drawableId, ref);
        }
        return ref.get();
    }

    public void toggleActionBar(boolean visible) {
        if (visible) {
            mActionBar.show();
            actionBarRightText.setVisibility(View.GONE);
            toolbarRightIcon.setVisibility(View.GONE);
            actionBarLeftText.setVisibility(View.GONE);
        } else {
            mActionBar.hide();
        }
    }

    public void setTitleOnClickListener(View.OnClickListener onClickListener) {
        actionBarTitle.setOnClickListener(onClickListener);
    }

    public void setActionBarTitle(CharSequence title) {
        ivToolbarTitle.setVisibility(View.GONE);
        actionBarTitle.setVisibility(View.VISIBLE);
        actionBarTitle.setText(title);
        setTitleOnClickListener(null);
    }

    public void setActionBarImageTitle(int resId) {
        actionBarTitle.setVisibility(View.GONE);
        ivToolbarTitle.setVisibility(View.VISIBLE);
        ivToolbarTitle.setImageResource(resId);
        setTitleOnClickListener(null);
    }

    public void setHomeAsUpIndicator(int resId) {
        mActionBar.setHomeAsUpIndicator(resId);
    }

    public void displayActionBarBack(boolean displayBack) {
        mActionBar.setDisplayHomeAsUpEnabled(displayBack);
        mActionBar.setHomeButtonEnabled(displayBack);
        mActionBar.setHomeAsUpIndicator(R.drawable.actionbar_back_selector);
    }

    public void displayActionBarLeftText(CharSequence title, final View.OnClickListener listener) {
        displayActionBarLeftText(title, R.color.blue_4C76FF, listener);
    }

    public void displayActionBarLeftText(CharSequence title, int resId, final View.OnClickListener listener) {
        actionBarLeftText.setVisibility(View.VISIBLE);
        actionBarLeftText.setText(title);
        actionBarLeftText.setTextColor(ContextCompat.getColor(mContext, resId));
        actionBarLeftText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(actionBarLeftText);
                }
            }
        });
    }

    public void displayActionBarRightText(CharSequence title, final View.OnClickListener listener) {
        displayActionBarRightText(title, R.color.blue_4C76FF, listener);
    }

    public void displayActionBarRightText(CharSequence title, int resId, final View.OnClickListener listener) {
        actionBarRightText.setVisibility(View.VISIBLE);
        actionBarRightText.setText(title);
        actionBarRightText.setTextColor(ContextCompat.getColor(mContext, resId));
        actionBarRightText.setCompoundDrawablePadding(0);
        actionBarRightText.setCompoundDrawables(null, null, null, null);
        actionBarRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(actionBarRightText);
                }
            }
        });
    }

    public void displayActionBarRightIconText(CharSequence title, int resId, final View.OnClickListener listener) {
        actionBarRightText.setVisibility(View.VISIBLE);
        actionBarRightText.setText(title);
        actionBarRightText.setTextColor(ContextCompat.getColor(mContext, R.color.blue_4C76FF));
        actionBarRightText.setCompoundDrawablePadding(DensityUtil.dipToPixels(mContext, 5));

        Drawable drawable = ContextCompat.getDrawable(mContext, resId);
        final int width = DensityUtil.dipToPixels(mContext, 18);
        final int height = DensityUtil.dipToPixels(mContext, 18);
        drawable.setBounds(0, 0, width, height);
        actionBarRightText.setCompoundDrawables(drawable, null, null, null);

        actionBarRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(actionBarRightText);
                }
            }
        });
    }

    public void setActionBarLeftTextColor(int resId) {
        actionBarLeftText.setTextColor(ContextCompat.getColor(mContext, resId));
    }

    public void setActionBarRightTextColor(int resId) {
        actionBarRightText.setTextColor(ContextCompat.getColor(mContext, resId));
    }

    public void displayActionBarRightIcon(int resId, final View.OnClickListener listener) {
        toolbarRightIcon.setVisibility(View.VISIBLE);
        ivRightIcon.setImageResource(resId);
        toolbarRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(null);
                }
            }
        });
    }

}
