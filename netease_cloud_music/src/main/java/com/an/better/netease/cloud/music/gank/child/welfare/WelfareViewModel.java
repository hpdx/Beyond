package com.an.better.netease.cloud.music.gank.child.welfare;

import com.trident.beyond.viewmodel.BasePagingListViewModel;

/**
 * Created by android_ls on 2018/2/23.
 */

public class WelfareViewModel extends BasePagingListViewModel<WelfarePagingListRequest, WelfareListView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }

}
