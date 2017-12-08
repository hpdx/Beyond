package com.trident.beyond.widgets.viewpagertab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anbetter.log.MLog;
import com.trident.beyond.R;

public class VPTabContainer extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private int mLastScrollTo;
    private int mScrollState;

    private VPTabStrip mTabStrip;
    private final int mTitleOffset;
    private int mTabTextPadding;

    private int mTabTextTopPadding;

    private ColorStateList mTabTextColors;

    private int mSelectedPosition;

    private ViewPager mViewPager;

    private int mTabGravity;

    private LinearLayout.LayoutParams mExpandedTabLayoutParams;

    public VPTabContainer(Context context) {
        this(context, null);
    }

    public VPTabContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        mTitleOffset = context.getResources().getDimensionPixelSize(
                R.dimen.play_tab_strip_title_offset);
        mTabTextPadding = context.getResources().getDimensionPixelSize(
                R.dimen.play_tab_strip_title_padding);
        mTabTextTopPadding = 0;
        mTabGravity = Gravity.CENTER;
        mExpandedTabLayoutParams = new LinearLayout.LayoutParams(0,
                LayoutParams.MATCH_PARENT, 1.0f);
    }

    private void scrollToChild(int childIndex, int extraOffset) {
        if (mTabStrip.getChildCount() == 0) {
            return;
        }

        int selectedLeft = mTabStrip.getChildAt(childIndex).getLeft();
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
        mTabStrip = (VPTabStrip) findViewById(R.id.pager_tab_strip);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mScrollState = state;
        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
            mTabStrip.onPageSelected(mSelectedPosition);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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

    @Override
    public void onPageSelected(int position) {
        MLog.i("onPageSelected position = " + position);

        mSelectedPosition = position;
        if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
            mTabStrip.onPageSelected(position);
            scrollToChild(position, 0);
        }
    }

    public void setSelectedIndicatorColor(int color) {
        mTabStrip.setSelectedIndicatorColor(color);
    }

    /**
     * Sets the tab indicator's height for the currently selected tab.
     *
     * @param height height to use for the indicator in pixels
     * @attr ref android.support.design.R.styleable#TabLayout_tabIndicatorHeight
     */
    public void setSelectedTabIndicatorHeight(int height) {
        mTabStrip.setSelectedIndicatorHeight(height);
    }

    public void setTabTextPadding(int padding) {
        mTabTextPadding = padding;
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

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);

        PagerAdapter adapter = mViewPager.getAdapter();
        mTabStrip.removeAllViews();
        for (int i = 0; i < adapter.getCount(); i++) {
            TextView title = new TextView(getContext());
            title.setTextSize(14);
            title.setGravity(mTabGravity);
            title.setSingleLine();
            title.setFocusable(true);
            title.setText(adapter.getPageTitle(i));
            title.setTextColor(mTabTextColors);
            title.setPadding(mTabTextPadding, mTabTextTopPadding, mTabTextPadding, 0);

            final int indexToSelect = i;
            title.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(indexToSelect);
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
                        if(mViewPager != null) {
                            scrollToChild(mViewPager.getCurrentItem(), 0);
                        }
                    }
                });
    }

    public void recycler() {
        if(mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
            mViewPager = null;
        }
    }

}
