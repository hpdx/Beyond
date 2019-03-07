package com.an.better.netease.cloud.music.gank.child.welfare;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemWelfareBinding;
import com.anbetter.beyond.adapter.BaseListPagingAdapter;
import com.anbetter.beyond.listener.OnItemClickListener;

/**
 *
 *
 * Created by android_ls on 2018/2/23.
 */

public class WelfareAdapter extends BaseListPagingAdapter<WelfarePagingListRequest> {

    public WelfareAdapter(WelfarePagingListRequest baseList, OnItemClickListener listener) {
        super(baseList, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        return new WelfareViewHolder(ItemWelfareBinding.inflate(mLayoutInflater, parent, false), mOnItemClickListener);
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        ((WelfareViewHolder)holder).bind(mList.getItem(position), position);
    }

}
