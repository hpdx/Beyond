package com.an.better.netease.cloud.music.gank.child.music;

import com.anbetter.beyond.viewmodel.BaseListViewModel;

/**
 * Created by android_ls on 2018/2/11.
 */

public class MusicViewModel extends BaseListViewModel<MusicListRequest, MusicListView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }

}
