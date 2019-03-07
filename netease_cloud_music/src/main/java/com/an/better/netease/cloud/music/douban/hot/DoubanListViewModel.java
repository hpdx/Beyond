package com.an.better.netease.cloud.music.douban.hot;

import com.anbetter.beyond.viewmodel.BaseListViewModel;

/**
 * Created by android_ls on 2018/3/2.
 */

public class DoubanListViewModel extends BaseListViewModel<DoubanListRequest, DoubanListView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }

}
