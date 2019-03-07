package com.anbetter.beyond.widgets.viewpagertab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anbetter.beyond.R;
import com.anbetter.beyond.model.TabData;

import java.util.List;

public class NavTabContainer extends HorizontalScrollView {

    private int mLastScrollTo;
    private int mScrollState;

    private NavTabStrip mTabStrip;
    private final int mTitleOffset;
    private int mTabTextLeftPadding;
    private int mTabTextRightPadding;
    private int mTabTextTopPadding;

    private ColorStateList mTabTextColors;

    private int mSelectedPosition;
    private int mTabGravity;

    private OnNavTabItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnNavTabItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private LinearLayout.LayoutParams mExpandedTabLayoutParams;

    public NavTabContainer(Context context) {
        this(context, null);
    }

    public NavTabContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        mTitleOffset = context.getResources().getDimensionPixelSize(
                R.dimen.play_tab_strip_title_offset);
        mTabTextLeftPadding = context.getResources().getDimensionPixelSize(
                R.dimen.play_tab_strip_title_padding);
        mTabTextRightPadding = context.getResources().getDimensionPixelSize(
                R.dimen.play_tab_strip_title_padding);
        mTabTextTopPadding = 0;
        mTabGravity = Gravity.CENTER;
        mExpandedTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, 1.0f);
    }

    private void scrollToChild(int childIndex, int extraOffset) {
        if (mTabStrip == null || mTabStrip.getChildCount() == 0) {
            return;
        }

        View leftTab =  mTabStrip.getChildAt(childIndex);
        int selectedLeft = 0;
        if(leftTab != null){
            selectedLeft = leftTab.getLeft();
        }
        int targetScrollX = selectedLeft + extraOffset;
        if (childIndex > 0 || extraOffset > 0) {
            targetScrollX = targetScrollX - mTitleOffset;
        }

        if (targetScrollX != mLastScrollTo) {
            mLastScrollTo = targetScrollX;
            scrollTo(targetScrollX, 0);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTabStrip = (NavTabStrip) findViewById(R.id.pager_tab_strip);
    }

    /**
     * 滚动状态改变调用该方法
     * @param state
     */
    public void onPageScrollStateChanged(int state) {
        mScrollState = state;
        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
            mTabStrip.onPageSelected(mSelectedPosition);
        }
    }

    /**
     * 滚动中调用该方法
     * @param position
     * @param positionOffset
     */
    public void onPageScrolled(int position, float positionOffset) {
        if (mTabStrip.getChildCount() == 0) {
            return;
        }
        mTabStrip.onPageScrolled(position, positionOffset);
        View selectedTitle = mTabStrip.getChildAt(position);
        if (selectedTitle != null) {
            int extraOffset = (int) (selectedTitle.getWidth() * positionOffset);
            scrollToChild(position, extraOffset);
        }
    }

    /**
     * 滚动结束后调用该方法
     * @param position
     */
    public void onPageSelected(int position) {
        mSelectedPosition = position;
        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
            mTabStrip.onPageSelected(position);
            scrollToChild(position, 0);
        }
    }

    public void setSelectedIndicatorColor(int color) {
        mTabStrip.setSelectedIndicatorColor(color);
    }

    public void setSelectedTabIndicatorHeight(int height) {
        mTabStrip.setSelectedIndicatorHeight(height);
    }

    public void setTabTextLeftPadding(int tabTextLeftPadding) {
        this.mTabTextLeftPadding = tabTextLeftPadding;
    }

    public void setTabTextRightPadding(int tabTextRightPadding) {
        this.mTabTextRightPadding = tabTextRightPadding;
    }

    public void setTabTextTopPadding(int topPadding) {
        mTabTextTopPadding = topPadding;
    }

    /**
     * Sets the text colors for the different states (normal, selected) used for the tabs.
     */
    public void setTabTextColors(@Nullable ColorStateList textColor) {
        if (mTabTextColors != textColor) {
            mTabTextColors = textColor;
        }
    }

    /**
     * Sets the text colors for the different states (normal, selected) used for the tabs.
     */
    public void setTabTextColors(int normalColor, int selectedColor) {
        setTabTextColors(createColorStateList(normalColor, selectedColor));
    }

    /**
     * Sets the text sizes for the different states (normal, selected) used for the tabs.
     */
    public void setTabTextSizes(int normalSize, int selectedSize) {
        mTabStrip.setTabTextSizes(normalSize, selectedSize);
    }

    private static ColorStateList createColorStateList(int defaultColor, int selectedColor) {
        final int[][] states = new int[2][];
        final int[] colors = new int[2];
        int i = 0;

        states[i] = SELECTED_STATE_SET;
        colors[i] = selectedColor;
        i++;

        // Default enabled state
        states[i] = EMPTY_STATE_SET;
        colors[i] = defaultColor;
        i++;

        return new ColorStateList(states, colors);
    }

    /**
     * Set the gravity to use when laying out the tabs.
     *
     * @param gravity one of {Gravity}.
     */
    public void setTabGravity(int gravity) {
        if (mTabGravity != gravity) {
            mTabGravity = gravity;
        }
    }


    public void setData(List<TabData> tabDataList, final int currentSelectedPosition) {
        mTabStrip.removeAllViews();
        for (int i = 0; i < tabDataList.size(); i++) {
            TextView title = new TextView(getContext());
            title.setTextSize(14);
            title.setGravity(mTabGravity);
            title.setSingleLine();
            title.setFocusable(true);
            title.setText(tabDataList.get(i).title);
            title.setTextColor(mTabTextColors);
            title.setPadding(mTabTextLeftPadding, mTabTextTopPadding, mTabTextRightPadding, 0);

            final int indexToSelect = i;
            title.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.OnNavTabItemClick(v, indexToSelect);
                    }
                }
            });

            if (mTabGravity != 0) {
                title.setGravity(mTabGravity);
            }

            mTabStrip.addView(title, i, mExpandedTabLayoutParams);
        }

        mTabStrip.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mTabStrip.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            scrollToChild(currentSelectedPosition, 0);
                    }
                });
    }

    public void recycler() {

    }

}
