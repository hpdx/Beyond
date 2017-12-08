package com.trident.beyond.widgets.viewpagertab;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trident.beyond.R;


public class VPTabStrip extends LinearLayout {

	private final Paint mFullUnderlinePaint;

	private final int mFullUnderlineThickness;

	private int mIndexForSelection;

	private final Paint mSelectedIndicatorPaint;

	private int mSelectedIndicatorHeight;

	private int mNormalTextSize;

	private int mSelectedTextSize;

	private final int mSelectedUnderlineBottom;

	private final int mStripTitlePadding;

	private float mSelectionOffset;

	private final int mSideSeparatorHeight;

	private final Paint mSideSeparatorPaint;

	private int mSelectedIndicatorColor;

	private boolean mShowSeparateLine;

	private boolean mShowFullUnderline;

	public VPTabStrip(Context context) {
		this(context, null);
	}

	public VPTabStrip(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		Resources res = context.getResources();
		mSelectedTextSize = 14;
		mNormalTextSize = 14;
		mFullUnderlineThickness = res.getDimensionPixelSize(R.dimen.play_tab_strip_full_underline_height);
		mFullUnderlinePaint = new Paint();
		mFullUnderlinePaint.setColor(res.getColor(R.color.play_tab_strip_bottom));
		TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.VPTabStrip);
		mSelectedIndicatorHeight = res.getDimensionPixelSize(R.dimen.play_tab_strip_selected_underline_height);
		mStripTitlePadding = viewAttrs.getDimensionPixelSize(R.styleable.VPTabStrip_tab_strip_title_padding, 0);
		mSelectedIndicatorPaint = new Paint();
		mSideSeparatorPaint = new Paint();
		mSideSeparatorPaint.setColor(res.getColor(R.color.play_tab_strip_side));
		mSideSeparatorPaint.setStrokeWidth(res.getDimensionPixelSize(R.dimen.hairline_separator_thickness));
		mSideSeparatorHeight = res.getDimensionPixelSize(R.dimen.play_tab_strip_vertical_separator);
		mShowSeparateLine = viewAttrs.getBoolean(R.styleable.VPTabStrip_show_side_separator, false);
		mShowFullUnderline = viewAttrs.getBoolean(R.styleable.VPTabStrip_show_full_underline, true);
		mSelectedUnderlineBottom = viewAttrs.getDimensionPixelSize(R.styleable.VPTabStrip_selected_underline_bottom, 0);
		viewAttrs.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int height = getHeight();
		int childCount = getChildCount();
		if (childCount > 0) {
			TextView selectedTitle = (TextView) getChildAt(mIndexForSelection);
			int selectedTextWidth = (int) selectedTitle.getPaint().measureText(selectedTitle.getText().toString());
			int selectedLeft = selectedTitle.getLeft() + (selectedTitle.getWidth() - selectedTextWidth) / 2;
			int selectedRight = selectedLeft + selectedTextWidth;
			if (mSelectionOffset > 0 && mIndexForSelection < childCount - 1) {
				TextView nextTitle = (TextView) getChildAt(mIndexForSelection + 1);
				int nextTextWidth = (int) nextTitle.getPaint().measureText(nextTitle.getText().toString());
				int nextLeft = nextTitle.getLeft() + (nextTitle.getWidth() - nextTextWidth) / 2;
				int nextRight = nextLeft + nextTextWidth;
				selectedLeft = (int) (mSelectionOffset * nextLeft + (1.0f - mSelectionOffset) * selectedLeft);
				selectedRight = (int) (mSelectionOffset * nextRight + (1.0f - mSelectionOffset) * selectedRight);
			}
			if (mSelectedIndicatorColor != 0) {
				canvas.drawRect(selectedLeft - mStripTitlePadding, height - mSelectedIndicatorHeight - mSelectedUnderlineBottom,
						selectedRight + mStripTitlePadding, height - mSelectedUnderlineBottom, mSelectedIndicatorPaint);
			}
		}
		if (mShowFullUnderline) {
			canvas.drawRect(0, height - mFullUnderlineThickness, getWidth(), height, mFullUnderlinePaint);
		}
		if (mShowSeparateLine) {
			for (int i = 1; i < childCount; i++) {
				View child = getChildAt(i);
				int childPaddingTop = child.getPaddingTop();
				int childPaddingBottom = child.getPaddingBottom();
				int separatorCenter = ((child.getHeight() - childPaddingTop - childPaddingBottom) / 2)
						+ childPaddingTop;
				int separatorTop = separatorCenter - (mSideSeparatorHeight / 2);
				canvas.drawLine(child.getLeft(), separatorTop, child.getLeft(),
						mSideSeparatorHeight + separatorTop, mSideSeparatorPaint);
			}
		}
	}

	void onPageScrolled(int position, float positionOffset) {
		mIndexForSelection = position;
		mSelectionOffset = positionOffset;
		ViewCompat.postInvalidateOnAnimation(this);
	}

	void onPageSelected(int position) {
		mIndexForSelection = position;
		mSelectionOffset = 0;
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			TextView title = (TextView) getChildAt(i);
			title.setSelected(i == mIndexForSelection);
			title.setTextSize(i == mIndexForSelection ? mSelectedTextSize : mNormalTextSize);
		}
	}

	void setSelectedIndicatorColor(int color) {
		if (mSelectedIndicatorPaint.getColor() != color) {
			mSelectedIndicatorColor = color;
			mSelectedIndicatorPaint.setColor(color);
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}

	void setSelectedIndicatorHeight(int height) {
		if (mSelectedIndicatorHeight != height) {
			mSelectedIndicatorHeight = height;
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}

	void setTabTextSizes(int normalSize, int selectedSize) {
		mNormalTextSize = normalSize;
		mSelectedTextSize = selectedSize;
	}
}
