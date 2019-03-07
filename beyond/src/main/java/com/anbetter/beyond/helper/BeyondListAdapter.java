package com.anbetter.beyond.helper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.model.BaseListRequest;
import com.anbetter.beyond.model.IModel;
import com.anbetter.beyond.viewholder.BaseViewHolder;

/**
 * Created by android_ls on 16/8/23.
 */
public class BeyondListAdapter<M extends BaseListRequest<?, ?>> extends BaseListAdapter<M> {

    public BeyondListAdapter(M baseList) {
        super(baseList);
    }

    @Override
    public final int getBLMItemViewType(int position) {
        IModel cellModel = mList.getItems().get(position);
        return ViewHolderProviderPool.getItemViewType(cellModel.getClass());
    }

    @SuppressWarnings("unchecked")
    @Override
    public final RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        ViewHolderProvider viewHolderProvider = ViewHolderProviderPool.getCellProvider(viewType);
        if(viewHolderProvider.isChildViewCallback()) {
            if(mOnItemClickListener == null) {
                throw new ViewHolderNotFoundException("isChildViewCallback() is true, but mOnItemClickListener is null");
            }
            return viewHolderProvider.onCreateViewHolder(mLayoutInflater, parent, mOnItemClickListener);
        }
        return viewHolderProvider.onCreateViewHolder(mLayoutInflater, parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        IModel cellModel = mList.getItem(position);
        ViewHolderProvider viewHolderProvider = ViewHolderProviderPool.getCellProvider(viewType);
        viewHolderProvider.onBindViewHolder((BaseViewHolder) holder, cellModel, position);
    }

}
