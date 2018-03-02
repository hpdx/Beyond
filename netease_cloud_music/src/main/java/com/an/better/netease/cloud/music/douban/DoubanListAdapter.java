package com.an.better.netease.cloud.music.douban;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.databinding.ItemListDoubanBinding;
import com.an.better.netease.cloud.music.douban.model.HotMovieTitle;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.an.better.netease.cloud.music.douban.viewholder.HeaderViewHolder;
import com.an.better.netease.cloud.music.douban.viewholder.MovieViewHolder;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.model.IModel;

/**
 *
 * Created by android_ls on 2018/3/2.
 */

public class DoubanListAdapter extends BaseListAdapter<DoubanListRequest> {

    private static final int VIEW_TYPE_HEADER = 1;
    private static final int VIEW_TYPE_DEFAULT = 2;

    public DoubanListAdapter(DoubanListRequest baseList, OnItemClickListener listener) {
        super(baseList, listener);
    }

    @Override
    public int getBLMItemViewType(int position) {
        IModel model = mList.getItems().get(position);
        if(model instanceof HotMovieTitle) {
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_DEFAULT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        if(VIEW_TYPE_HEADER == viewType) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_douban_header, parent,
                    false), mOnItemClickListener);
        }
        return new MovieViewHolder(ItemListDoubanBinding.inflate(mLayoutInflater, parent, false),
                mOnItemClickListener);
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        if(VIEW_TYPE_HEADER == viewType) {
            ((HeaderViewHolder)holder).bind((HotMovieTitle)mList.getItems().get(position), position);
        } else  if(VIEW_TYPE_DEFAULT == viewType) {
            ((MovieViewHolder)holder).bind((SubjectsBean)mList.getItems().get(position), position);
        }
    }

}
