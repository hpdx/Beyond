package com.an.better.netease.cloud.music.gank.child.welfare;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemWelfareBinding;
import com.trident.beyond.adapter.BaseListPagingAdapter;

/**
 *
 *
 * Created by android_ls on 2018/2/23.
 */

public class WelfareAdapter extends BaseListPagingAdapter<WelfarePagingListRequest> {

    public WelfareAdapter(WelfarePagingListRequest baseList) {
        super(baseList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        return new WelfareViewHolder(ItemWelfareBinding.inflate(mLayoutInflater, parent, false));
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        ((WelfareViewHolder)holder).bind(mList.getItem(position), position);
    }

}
