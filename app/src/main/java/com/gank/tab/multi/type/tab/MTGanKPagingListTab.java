package com.gank.tab.multi.type.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.gank.R;
import com.gank.tab.multi.type.adapter.MTPagingListAdapter;
import com.gank.tab.multi.type.model.MTGanKPagingListRequest;
import com.gank.tab.multi.type.viewmodel.MTGankPagingListViewModel;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.model.TabData;
import com.trident.beyond.tab.BasePagingListTab;
import com.trident.beyond.view.BasePagingListView;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

/**
 * Created by android_ls on 2017/1/3.
 */

public class MTGanKPagingListTab extends BasePagingListTab<MTGanKPagingListRequest, BasePagingListView<MTGanKPagingListRequest>, MTGankPagingListViewModel>
        implements FlexibleDividerDecoration.SizeProvider {

    public MTGanKPagingListTab(Context context, TabData tabData) {
        super(context, tabData);
    }

    @Override
    protected MTGankPagingListViewModel createViewModel() {
        return new MTGankPagingListViewModel();
    }

    @Override
    protected BaseListAdapter createAdapter(MTGanKPagingListRequest data) {
        return new MTPagingListAdapter(data);
    }

    @Override
    protected void addItemDecoration() {
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .colorResId(R.color.grey_background)
                .sizeProvider(this)
                .showLastDivider()
                .build());
    }

    @Override
    public int dividerSize(int position, RecyclerView parent) {
        return mContext.getResources().getDimensionPixelSize(R.dimen.horizontal_divider_height);
    }

}
