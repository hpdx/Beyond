package com.an.better.netease.cloud.music.douban.hot;

import android.view.View;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.douban.HotMovieTop250Activity;
import com.an.better.netease.cloud.music.douban.MovieDetailActivity;
import com.an.better.netease.cloud.music.douban.adapter.DoubanListAdapter;
import com.an.better.netease.cloud.music.douban.model.HotMovieTitle;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.fragment.BaseListFragment;
import com.anbetter.beyond.listener.OnItemClickListener;
import com.anbetter.beyond.model.IModel;
import com.anbetter.log.MLog;

/**
 * Created by android_ls on 2018/1/26.
 */

public class DoubanFragment extends BaseListFragment<DoubanListRequest, DoubanListView,
        DoubanListViewModel> implements DoubanListView, OnItemClickListener<IModel> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_douban;
    }

    @Override
    protected DoubanListRequest getList() {
        return new DoubanListRequest();
    }

    @Override
    protected DoubanListViewModel createViewModel() {
        return new DoubanListViewModel();
    }

    @Override
    protected BaseListAdapter createAdapter(DoubanListRequest data) {
        return new DoubanListAdapter(data, this);
    }

    @Override
    public void onItemClick(View view, IModel data, int position) {
        MLog.i("onItemClick position = " + position);
        if(data instanceof HotMovieTitle) {
            HotMovieTop250Activity.start(getActivity());
        } else if(data instanceof SubjectsBean && view instanceof ImageView) {
            MovieDetailActivity.start(getActivity(), (SubjectsBean)data, (ImageView)view);
        }
    }

    @Override
    public void onItemClick(View view, IModel data, int position, int code) {

    }

}
