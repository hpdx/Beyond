package com.gank.day;

import android.support.v7.widget.RecyclerView;

import com.gank.R;
import com.gank.day.model.GanKDayCategory;
import com.gank.day.model.GanKDayRequest;
import com.gank.day.view.GanKDayView;
import com.gank.day.viewmodel.GanKDayListViewModel;
import com.trident.beyond.core.IModel;
import com.trident.beyond.fragment.BaseListFragment;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

public class GanKDayListFragment extends BaseListFragment<GanKDayRequest, GanKDayView, GanKDayListViewModel> implements GanKDayView, FlexibleDividerDecoration.SizeProvider {

    public static GanKDayListFragment newInstance() {
        GanKDayListFragment fragment = new GanKDayListFragment();
        return fragment;
    }

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        mPageFragmentHost.setActionBarTitle("多ViewHolder示例");
        mPageFragmentHost.displayActionBarBack(true);
    }

    @Override
    protected GanKDayRequest getList() {
        return new GanKDayRequest();
    }

    @Override
    protected GanKDayListViewModel createViewModel() {
        return new GanKDayListViewModel();
    }

    @Override
    protected void addItemDecoration() {
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.grey_background)
                .sizeProvider(this)
                .showLastDivider()
                .build());
    }

    @Override
    public int dividerSize(int position, RecyclerView parent) {
        if (position < mList.getItems().size()) {
            IModel model = mList.getItems().get(position);
            if (model instanceof GanKDayCategory) {
                return 1;
            }
        }

        return mContext.getResources().getDimensionPixelSize(R.dimen.horizontal_divider_height);
    }

    @Override
    protected void setRefreshEnabled() {
        // 禁用掉下拉刷新功能
        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.setEnabled(false);
        }
    }

}

