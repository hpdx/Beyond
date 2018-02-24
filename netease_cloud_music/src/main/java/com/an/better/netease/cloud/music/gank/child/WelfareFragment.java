package com.an.better.netease.cloud.music.gank.child;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.an.better.netease.cloud.music.gank.child.welfare.WelfareAdapter;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfareListView;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfarePagingListRequest;
import com.an.better.netease.cloud.music.gank.child.welfare.WelfareViewModel;
import com.anbetter.log.MLog;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.fragment.BasePagingListFragment;

/**
 * 福利
 * <p>
 * Created by android_ls on 2018/2/11.
 */

public class WelfareFragment extends BasePagingListFragment<WelfarePagingListRequest,
        WelfareListView, WelfareViewModel> implements WelfareListView {

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
        return new WelfareAdapter(data);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLog.i("WelfareFragment--->onDestroy");
    }

}
