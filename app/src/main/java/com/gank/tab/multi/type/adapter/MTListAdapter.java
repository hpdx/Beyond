package com.gank.tab.multi.type.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gank.common.model.GanKCellModel;
import com.gank.list.model.GanKListRequest;
import com.gank.tab.multi.type.viewholder.MTGanKViewHolder;
import com.trident.beyond.adapter.BaseListAdapter;


/**
 * Created by android_ls on 2017/1/3.
 */

public class MTListAdapter extends BaseListAdapter<GanKListRequest> {

    public MTListAdapter(GanKListRequest baseList) {
        super(baseList);
    }

    @Override
    public int getBLMItemViewType(int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        return new MTGanKViewHolder(mLayoutInflater, parent);
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        GanKCellModel cellModel = mList.getItems().get(position);
        ((MTGanKViewHolder)holder).bind(cellModel, position);
    }
}
