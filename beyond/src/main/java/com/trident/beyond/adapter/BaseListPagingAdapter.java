package com.trident.beyond.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trident.beyond.R;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.model.BasePaginatedListRequest;
import com.trident.beyond.viewholder.ErrorFooterViewHolder;
import com.trident.beyond.viewholder.FooterNoneViewHolder;


/**
 *
 * Created by android_ls on 16/7/4.
 */
public abstract class BaseListPagingAdapter<M extends BasePaginatedListRequest<?, ?>> extends BaseListAdapter<M> {

    protected static final int FOOTER_VIEW_TYPE_LOADING = 10002;

    protected static final int FOOTER_VIEW_TYPE_ERROR = 10003;

    public BaseListPagingAdapter(M baseList) {
        super(baseList);
    }

    public BaseListPagingAdapter(M baseList, OnItemClickListener listener) {
        super(baseList, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        switch (viewType) {
            case FOOTER_VIEW_TYPE_ERROR:
                return getFooterErrorView(mLayoutInflater, parent);
            case FOOTER_VIEW_TYPE_LOADING:
                return getFooterLoadingView(mLayoutInflater, parent);
            case FOOTER_VIEW_TYPE_NONE:
                return getFooterNoneView(mLayoutInflater, parent);
        }
        return onCreateBLMViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case FOOTER_VIEW_TYPE_ERROR:
                ((ErrorFooterViewHolder)holder).bind(null, position);
                break;
            case FOOTER_VIEW_TYPE_LOADING:
                break;
            case FOOTER_VIEW_TYPE_NONE:
                ((FooterNoneViewHolder)holder).setVisibility(mList.showFooterNoneView());
                break;
            default:
                onBindBLMViewHolder(holder, position, viewType);
        }
    }

    @Override
    public void setFooterViewType() {
        if(mList.inErrorState()) {
            this.mFooterViewType = FOOTER_VIEW_TYPE_ERROR;
        } else if(mList.isMoreAvailable()) {
            this.mFooterViewType = FOOTER_VIEW_TYPE_LOADING;
        } else {
            this.mFooterViewType = FOOTER_VIEW_TYPE_NONE;
        }
    }

    protected RecyclerView.ViewHolder getFooterLoadingView(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.footer_loading, parent, false)) {};
    }

    protected RecyclerView.ViewHolder getFooterErrorView(LayoutInflater inflater, ViewGroup parent) {
        return new ErrorFooterViewHolder(inflater, parent,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        retryLoadingItems();
                    }
                });
    }

    protected void retryLoadingItems() {
        mList.retryLoadItems();
    }

}
