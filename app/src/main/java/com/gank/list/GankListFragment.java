package com.gank.list;

import android.support.v7.widget.RecyclerView;

import com.gank.R;
import com.gank.list.model.GanKListRequest;
import com.gank.list.view.GankListView;
import com.gank.list.viewmodel.GankListViewModel;
import com.trident.beyond.fragment.BaseListFragment;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GankListFragment extends BaseListFragment<GanKListRequest, GankListView, GankListViewModel>
        implements GankListView, FlexibleDividerDecoration.SizeProvider {

    private String mTitle;
    public static GankListFragment newInstance(String title) {
        GankListFragment fragment = new GankListFragment();
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
    protected GanKListRequest getList() {
        return new GanKListRequest();
    }

    @Override
    protected GankListViewModel createViewModel() {
        return new GankListViewModel();
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
