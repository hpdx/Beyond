package com.an.better.netease.cloud.music.gank.child.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.FirstPublishItemBinding;
import com.an.better.netease.cloud.music.gank.child.music.model.ResultBean;
import com.an.better.netease.cloud.music.gank.child.music.viewholder.FirstItemViewHolder;
import com.anbetter.beyond.adapter.BaseAdapter;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class FirstPublishAdapter extends BaseAdapter<ResultBean> {

    public FirstPublishAdapter(Context context, List<ResultBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FirstItemViewHolder(FirstPublishItemBinding.inflate(mLayoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FirstItemViewHolder)holder).bind(mData.get(position), position);
    }

}
