package com.an.better.netease.cloud.music.gank.child.everyday.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemEverydayGridBinding;
import com.an.better.netease.cloud.music.databinding.ItemEverydayOneBinding;
import com.an.better.netease.cloud.music.databinding.ItemEverydayTitleBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.EverydayListRequest;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayBanner;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayCategory;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.FocusBean;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.GridViewHolder;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.HomeBannerViewHolder;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.OneViewHolder;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.RestMovieViewHolder;
import com.an.better.netease.cloud.music.gank.child.everyday.viewholder.TitleViewHolder;
import com.trident.beyond.adapter.BaseListAdapter;

import java.util.ArrayList;

/**
 * Created by android_ls on 2018/2/11.
 */

public class EverydayAdapter extends BaseListAdapter<EverydayListRequest> {

    public EverydayAdapter(EverydayListRequest baseList) {
        super(baseList);
    }

    @Override
    public int getBLMItemViewType(int position) {
        return mList.getItems().get(position).first;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBLMViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EverydayListRequest.VIEW_TYPE_BANNER:
                return new HomeBannerViewHolder(mLayoutInflater, parent);
            case EverydayListRequest.VIEW_TYPE_BLOCK_TITLE:
                return new TitleViewHolder(ItemEverydayTitleBinding.inflate(mLayoutInflater, parent, false));
            case EverydayListRequest.VIEW_TYPE_MEIZI:
                return new OneViewHolder(ItemEverydayOneBinding.inflate(mLayoutInflater, parent, false));
            case EverydayListRequest.VIEW_TYPE_REST_MOVIE:
                return new RestMovieViewHolder(ItemEverydayOneBinding.inflate(mLayoutInflater, parent, false));
            case EverydayListRequest.VIEW_TYPE_ANDROID:
                return new GridViewHolder(ItemEverydayGridBinding.inflate(mLayoutInflater, parent, false), 2, 10);
            case EverydayListRequest.VIEW_TYPE_IOS_BLOCK:
                return new GridViewHolder(ItemEverydayGridBinding.inflate(mLayoutInflater, parent, false), 2, 10);
            case EverydayListRequest.VIEW_TYPE_RECOMMEND:
                return new GridViewHolder(ItemEverydayGridBinding.inflate(mLayoutInflater, parent, false), 3, 5);
            case EverydayListRequest.VIEW_TYPE_FRONT_WEB:
                return new GridViewHolder(ItemEverydayGridBinding.inflate(mLayoutInflater, parent, false), 3, 5);
            case EverydayListRequest.VIEW_TYPE_APP:
                return new GridViewHolder(ItemEverydayGridBinding.inflate(mLayoutInflater, parent, false), 1, 0);
            default:
                String unknown = "Unknown type for onCreateViewHolder" + viewType;
                throw new IllegalStateException(unknown);
        }
    }

    @Override
    public void onBindBLMViewHolder(RecyclerView.ViewHolder holder, int position, int viewType) {
        switch (viewType) {
            case EverydayListRequest.VIEW_TYPE_BANNER:
                ((HomeBannerViewHolder) holder).bind((FocusBean) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_BLOCK_TITLE:
                ((TitleViewHolder) holder).bind((GanKDayCategory) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_MEIZI:
                ((OneViewHolder) holder).bind((GanKDayBanner) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_REST_MOVIE:
                ((RestMovieViewHolder) holder).bind((ArrayList<GanKInfo>) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_IOS_BLOCK:
                ((GridViewHolder) holder).bind((ArrayList<GanKInfo>) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_FRONT_WEB:
                ((GridViewHolder) holder).bind((ArrayList<GanKInfo>) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_RECOMMEND:
                ((GridViewHolder) holder).bind((ArrayList<GanKInfo>) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_APP:
                ((GridViewHolder) holder).bind((ArrayList<GanKInfo>) mList.getItems().get(position).second, position);
                break;
            case EverydayListRequest.VIEW_TYPE_ANDROID:
                ((GridViewHolder) holder).bind((ArrayList<GanKInfo>) mList.getItems().get(position).second, position);
                break;
        }
    }

}
