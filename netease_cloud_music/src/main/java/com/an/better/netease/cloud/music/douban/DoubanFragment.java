package com.an.better.netease.cloud.music.douban;

import android.view.View;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.douban.model.HotMovieTitle;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.anbetter.log.MLog;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.fragment.BaseListFragment;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.model.IModel;

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

}
