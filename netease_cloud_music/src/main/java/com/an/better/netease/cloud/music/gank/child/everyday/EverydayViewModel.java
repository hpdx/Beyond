package com.an.better.netease.cloud.music.gank.child.everyday;

import com.anbetter.beyond.viewmodel.BaseListViewModel;

/**
 * Created by android_ls on 2018/2/11.
 */

public class EverydayViewModel extends BaseListViewModel<EverydayListRequest, EverydayListView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }

}
