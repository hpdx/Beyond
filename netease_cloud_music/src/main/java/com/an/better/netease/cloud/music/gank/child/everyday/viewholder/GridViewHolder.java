package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.an.better.netease.cloud.music.databinding.ItemEverydayGridBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.adapter.GridAdapter;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.anbetter.log.MLog;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

import java.util.ArrayList;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class GridViewHolder extends BaseVdbViewHolder<ArrayList<GanKInfo>, ItemEverydayGridBinding> {

    private GridAdapter mAdapter;
    private int dividerHeight;

    public GridViewHolder(ItemEverydayGridBinding viewDataBinding) {
        super(viewDataBinding);
        dividerHeight = DensityUtil.dipToPixels(mContext, 5);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));
        binding.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                int childCount = parent.getAdapter().getItemCount();
                int itemPosition = parent.getChildAdapterPosition(view);
                MLog.i("spanCount = " + spanCount);
                MLog.i("childCount = " + childCount);
                MLog.i("itemPosition = " + itemPosition);

                // 如果是最后一列，则不需要绘制右边
                if (!((itemPosition + 1) % spanCount == 0)) {
                    outRect.right = dividerHeight;
                }

                // 如果是最后一行，则不需要绘制底部
                int itemCount = childCount - childCount % spanCount;
                MLog.i("itemCount = " + itemCount);
                if ((itemPosition + 1) < itemCount) {
                    outRect.bottom = dividerHeight;
                }
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);

            }

        });

    }

    @Override
    public void bind(ArrayList<GanKInfo> cellModel, int position) {
        super.bind(cellModel, position);

        if(mAdapter == null) {
            mAdapter = new GridAdapter(mContext, cellModel);
        }
        binding.recyclerView.setAdapter(mAdapter);
    }

}
