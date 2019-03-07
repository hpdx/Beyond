package com.anbetter.beyond.widgets.viewpagertab;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <p>
 * Created by android_ls on 2018/10/29.
 *
 * @author 李松
 * @version 1.0
 */
public class ScaleNavBarTabStrip extends NavTabStrip {

    private static final int ANIM_DURATION = 120;

    public ScaleNavBarTabStrip(Context context) {
        super(context);
    }

    public ScaleNavBarTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPageScrolled(int position, float positionOffset) {
        mSelectionOffset = positionOffset;
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override
    public void onPageSelected(int position) {
        final TextView oldTvTitle = (TextView) getChildAt(mIndexForSelection);
        if (oldTvTitle != null) {
            ValueAnimator scaleValueAnimator = ValueAnimator.ofFloat(mSelectedTextSize, mNormalTextSize);
            scaleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatorValue = Float.valueOf(String.valueOf(animation.getAnimatedValue()));
                    oldTvTitle.setTextSize(animatorValue);
                    oldTvTitle.setSelected(false);
                    oldTvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                }
            });
            scaleValueAnimator.setTarget(oldTvTitle);
            scaleValueAnimator.setDuration(ANIM_DURATION);
            scaleValueAnimator.start();
        }

        mIndexForSelection = position;
        mSelectionOffset = 0;

        final TextView selectTvTitle = (TextView) getChildAt(mIndexForSelection);
        if (selectTvTitle != null) {
            ValueAnimator scaleValueAnimator = ValueAnimator.ofFloat(mNormalTextSize, mSelectedTextSize);
            scaleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatorValue = Float.valueOf(String.valueOf(animation.getAnimatedValue()));
                    selectTvTitle.setTextSize(animatorValue);
                    selectTvTitle.setSelected(true);
                    selectTvTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            });
            scaleValueAnimator.setTarget(selectTvTitle);
            scaleValueAnimator.setDuration(ANIM_DURATION);
            scaleValueAnimator.start();
        }
    }


}
