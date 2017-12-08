package com.trident.beyond.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trident.beyond.R;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.listener.OnItemClickListener2;
import com.trident.beyond.listener.OnViewCallback;
import com.trident.beyond.model.BaseListRequest;
import com.trident.beyond.viewholder.FooterNoneViewHolder;

/**
 *
 * Created by android_ls on 16/7/22.
 */
public abstract class BaseListAdapter<M extends BaseListRequest<?, ?>> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int FOOTER_VIEW_TYPE_NONE = 10001;

    protected LayoutInflater mLayoutInflater;
    protected M mList;
    protected int mFooterViewType;

    protected OnItemClickListener mOnItemClickListener;
    protected OnItemClickListener2 mOnItemClickListener2;
    protected OnViewCallback mOnViewCallback;

    public BaseListAdapter(M baseList) {
        this.mList = baseList;
        setFooterViewType();
    }

    public void updateAdapterData(M baseList) {
        this.mList = baseList;
        setFooterViewType();
        notifyDataSetChanged();
    }

    protected void setFooterViewType() {
        mFooterViewType = FOOTER_VIEW_TYPE_NONE;
    }

    public abstract int getBLMItemViewType(int position);

    public abstract RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType);

    @Override
    public int getItemCount() {
        return mList.getCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int lastRow = getItemCount() - 1;
        if (position == lastRow) {
            return mFooterViewType;
        }
        return getBLMItemViewType(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemClickListener2(OnItemClickListener2 listener) {
        mOnItemClickListener2 = listener;
    }

    public void setOnViewCallback(OnViewCallback callback) {
        mOnViewCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        if (FOOTER_VIEW_TYPE_NONE == viewType) {
            return getFooterNoneView(mLayoutInflater, parent);
        }

        final RecyclerView.ViewHolder viewHolder = onCreateBLMViewHolder(parent, viewType);
        setOnItemClickListener(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (FOOTER_VIEW_TYPE_NONE != viewType) {
            onBindBLMViewHolder(holder, position, viewType);
        } else {
            ((FooterNoneViewHolder) holder).setVisibility(mList.showFooterNoneView());
        }
    }

    protected FooterNoneViewHolder getFooterNoneView(LayoutInflater inflater, ViewGroup parent) {
        return new FooterNoneViewHolder(inflater.inflate(R.layout.footer_none, parent, false)) {
        };
    }

    public void recycler() {
        mLayoutInflater = null;
    }

    protected void setOnItemClickListener(final RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (mList == null || position < 0 || position >= mList.getCount()) {
                    return;
                }

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, mList.getItems().get(position), position);
                }

                if (mOnItemClickListener2 != null) {
                    mOnItemClickListener2.onItemClick(v, mList.getItems(), position);
                }
            }
        });
    }

}
