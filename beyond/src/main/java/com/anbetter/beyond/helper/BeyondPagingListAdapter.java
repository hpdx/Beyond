package com.anbetter.beyond.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anbetter.beyond.R;
import com.anbetter.beyond.model.BasePaginatedListRequest;
import com.anbetter.beyond.viewholder.ErrorFooterViewHolder;
import com.anbetter.beyond.viewholder.FooterNoneViewHolder;


/**
 * Created by android_ls on 16/7/4.
 */
public class BeyondPagingListAdapter<M extends BasePaginatedListRequest<?, ?>> extends BeyondListAdapter<M> {

    protected static final int FOOTER_VIEW_TYPE_LOADING = 10002;

    protected static final int FOOTER_VIEW_TYPE_ERROR = 10003;

    public BeyondPagingListAdapter(M baseList) {
        super(baseList);
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        return super.onCreateBLMViewHolder(parent, viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
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
                super.onBindBLMViewHolder(holder, position, viewType);
        }
    }

    @Override
    public final void setFooterViewType() {
        if (mList.inErrorState()) {
            this.mFooterViewType = FOOTER_VIEW_TYPE_ERROR;
        } else if (mList.isMoreAvailable()) {
            this.mFooterViewType = FOOTER_VIEW_TYPE_LOADING;
        } else {
            this.mFooterViewType = FOOTER_VIEW_TYPE_NONE;
        }
    }

    protected void retryLoadingItems() {
        mList.retryLoadItems();
    }

    protected RecyclerView.ViewHolder getFooterLoadingView(LayoutInflater inflater, ViewGroup parent) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.footer_loading, parent, false)) {
        };
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

}
