package com.gank.paginglist;

import android.support.v7.widget.RecyclerView;

import com.gank.R;
import com.gank.paginglist.model.GanKPagingListRequest;
import com.gank.paginglist.view.GankPagingListView;
import com.gank.paginglist.viewmodel.GankPagingListViewModel;
import com.trident.beyond.fragment.BasePagingListFragment;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GanKPagingListFragment extends BasePagingListFragment<GanKPagingListRequest, GankPagingListView, GankPagingListViewModel>
        implements FlexibleDividerDecoration.SizeProvider {

    private String mTitle;
    public static GanKPagingListFragment newInstance(String title) {
        GanKPagingListFragment fragment = new GanKPagingListFragment();
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        mPageFragmentHost.setActionBarTitle(mTitle);
        mPageFragmentHost.displayActionBarBack(true);
    }

    @Override
    protected GanKPagingListRequest getList() {
        return new GanKPagingListRequest();
    }

    @Override
    protected GankPagingListViewModel createViewModel() {
        return new GankPagingListViewModel();
    }

//    @Override
//    protected void addItemDecoration() {
//        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
//                .colorResId(R.color.grey_background)
//                .sizeProvider(this)
//                .showLastDivider()
//                .build());
//    }

    @Override
    public int dividerSize(int position, RecyclerView parent) {
        return mContext.getResources().getDimensionPixelSize(R.dimen.horizontal_divider_height);
    }

}
