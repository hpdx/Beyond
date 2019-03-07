package com.an.better.netease.cloud.music.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.an.better.netease.cloud.music.gank.child.music.MusicHomeAdapter;
import com.an.better.netease.cloud.music.gank.child.music.MusicListRequest;
import com.an.better.netease.cloud.music.gank.child.music.MusicListView;
import com.an.better.netease.cloud.music.gank.child.music.MusicViewModel;
import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.fragment.BaseListFragment;
import com.anbetter.log.MLog;

/**
 * 百度音乐首页
 * <p>
 * Created by android_ls on 2018/2/11.
 */

public class MusicHomeFragment extends BaseListFragment<MusicListRequest, MusicListView, MusicViewModel>
        implements MusicListView {

    @Override
    protected MusicViewModel createViewModel() {
        return new MusicViewModel();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i("CustomFragment--->onCreate");
    }

    @Override
    protected MusicListRequest getList() {
        return new MusicListRequest();
    }

    @Override
    protected BaseListAdapter createAdapter(MusicListRequest data) {
        return new MusicHomeAdapter(data, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 禁用下拉刷新功能
        setRefreshEnabled(false);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLog.i("CustomFragment--->onDestroy");
    }

}
