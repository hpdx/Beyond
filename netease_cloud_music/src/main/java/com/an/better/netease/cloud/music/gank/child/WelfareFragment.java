package com.an.better.netease.cloud.music.gank.child;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfareAdapter;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfareListView;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfarePagingListRequest;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfareViewModel;
import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.fragment.BasePagingListFragment;
import com.anbetter.beyond.listener.OnItemClickListener;
import com.anbetter.log.MLog;
import com.facebook.fresco.helper.photoview.PhotoX;

/**
 * 福利
 * <p>
 * Created by android_ls on 2018/2/11.
 */

public class WelfareFragment extends BasePagingListFragment<WelfarePagingListRequest,
        WelfareListView, WelfareViewModel> implements WelfareListView, OnItemClickListener<GanKInfo> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i("WelfareFragment--->onCreate");

    }

    @Override
    protected WelfareViewModel createViewModel() {
        return new WelfareViewModel();
    }

    @Override
    protected WelfarePagingListRequest getList() {
        return new WelfarePagingListRequest();
    }

    @Override
    protected BaseListAdapter createAdapter(WelfarePagingListRequest data) {
        return new WelfareAdapter(data, this);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRefreshEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLog.i("WelfareFragment--->onDestroy");
    }

    @Override
    public void onItemClick(View view, GanKInfo data, int position) {
        PhotoX.with(mContext)
                .setPhotoStringList(mList.getPhotos())
                .setCurrentPosition(position)
                .start();
    }

    @Override
    public void onItemClick(View view, GanKInfo data, int position, int code) {

    }

}
