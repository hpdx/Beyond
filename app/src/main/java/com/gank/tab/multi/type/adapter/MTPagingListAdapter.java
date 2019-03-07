package com.gank.tab.multi.type.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.anbetter.beyond.adapter.BaseListPagingAdapter;
import com.anbetter.beyond.model.IModel;
import com.gank.common.model.GanKCellModel;
import com.gank.databinding.VdbGankDayBannerItemBinding;
import com.gank.day.model.GanKDayBanner;
import com.gank.tab.multi.type.model.MTGanKPagingListRequest;
import com.gank.tab.multi.type.viewholder.MTGanKDayViewHolder;
import com.gank.tab.multi.type.viewholder.MTGanKViewHolder;


/**
 * Created by android_ls on 2017/1/3.
 */

public class MTPagingListAdapter extends BaseListPagingAdapter<MTGanKPagingListRequest> {

    private static int ITEM_VIEW_TYPE_BANNER = 1;

    private static int ITEM_VIEW_TYPE_CONTENT = 2;

    public MTPagingListAdapter(MTGanKPagingListRequest baseList) {
        super(baseList);
    }

    @Override
    public int getBLMItemViewType(int position) {
        if(position == 0) {
            return ITEM_VIEW_TYPE_BANNER;
        }
        return ITEM_VIEW_TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_VIEW_TYPE_CONTENT) {
            return new MTGanKViewHolder(mLayoutInflater, parent);
        }
        return new MTGanKDayViewHolder(VdbGankDayBannerItemBinding.inflate(mLayoutInflater, parent, false));
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        IModel cellModel = mList.getItem(position);
        if(viewType == ITEM_VIEW_TYPE_CONTENT) {
            if(cellModel instanceof GanKCellModel) {
                ((MTGanKViewHolder)holder).bind((GanKCellModel) cellModel, position);
            }
        } else {
            if(cellModel instanceof GanKDayBanner) {
                ((MTGanKDayViewHolder) holder).bind((GanKDayBanner) cellModel, position);
            }
        }
    }

}
