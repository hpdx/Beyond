package com.trident.beyond.widgets.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 * Created by android_ls on 2018/2/22.
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int right;
    private int bottom;

    public GridItemDecoration(int right, int bottom) {
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        int childCount = parent.getAdapter().getItemCount();
        int itemPosition = parent.getChildAdapterPosition(view);
//        MLog.i("spanCount = " + spanCount);
//        MLog.i("childCount = " + childCount);
//        MLog.i("itemPosition = " + itemPosition);

        int outRectRight = 0;
        int outRectBottom = 0;

        // 如果是最后一列，则不需要绘制右边
        if ((itemPosition + 1) % spanCount != 0) {
            outRectRight = right;
        }

        // 如果是最后一行，则不需要绘制底部
        int itemCount = childCount - childCount % spanCount;
//        MLog.i("itemCount = " + itemCount);
        if ((itemPosition + 1) < itemCount) {
            outRectBottom = bottom;
        }

        outRect.set(0, 0, outRectRight, outRectBottom);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

}
