package com.an.better.netease.cloud.music.gank.child.everyday.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemEverydayGridItemBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.GridItemViewHolder;
import com.trident.beyond.adapter.BaseAdapter;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class GridAdapter extends BaseAdapter<GanKInfo> {

    public GridAdapter(Context context, List<GanKInfo> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridItemViewHolder(ItemEverydayGridItemBinding.inflate(mLayoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GridItemViewHolder)holder).bind(mData.get(position), position);
    }

}
