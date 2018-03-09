package com.an.better.netease.cloud.music.douban;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.douban.model.HotMovieBlock;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.dating.libcommon.listener.ResponseListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2018/3/6.
 */

public class HotMovieTop250Activity extends AppCompatActivity
        implements ResponseListener<HotMovieBlock>, OnItemClickListener<SubjectsBean>,
        XRecyclerView.LoadingListener, CustomFooterViewCallBack {

    private int mStart = 0;
    private List<SubjectsBean> subjects;
    private boolean mPullRefresh;

    private Toolbar mToolbar;
    private XRecyclerView mRecyclerView;
    private LinearLayout ll_progress_bar;
    private DoubanTopListAdapter mAdapter;

    private AnimationDrawable mAnimationDrawable;
    private LoadingFooterView mFooterView;

    public static void start(Activity context) {
        context.startActivity(new Intent(context, HotMovieTop250Activity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_movie_top);

        // 设置透明状态栏
//        StatusBarUtils.setColor(this, CommonUtils.getColor(R.color.colorTheme), 0);

        mToolbar = findViewById(R.id.toolbar);
        ll_progress_bar = findViewById(R.id.ll_progress_bar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLimitNumberToCallLoadMore(3);
        mRecyclerView.setLoadingListener(this);
        mFooterView = new LoadingFooterView(this);
        mRecyclerView.setFootView(mFooterView, this);

        setToolBar();
        mToolbar.setTitle("豆瓣电影Top250");

        subjects = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DoubanTopListAdapter(this, subjects, this);
        mRecyclerView.setAdapter(mAdapter);

        showLoading();
        mStart = 0;
        loadData();
    }

    public void loadData() {
        Apis.getMovieTop250(mStart, this);
    }

    @Override
    public void onResponse(HotMovieBlock response) {
        if (response.subjects != null && response.subjects.size() > 0) {
            showContentView();

            if (mPullRefresh) {
                mPullRefresh = false;
                subjects.clear();
            }

            subjects.addAll(response.subjects);
            mStart = subjects.size();

            if (mRecyclerView != null) {
                mRecyclerView.refreshComplete();
            }

            if (mAdapter != null) {
                mAdapter.updateAdapterData(subjects);
            }
        }
    }

    @Override
    public void onErrorResponse(Exception error) {
        mRecyclerView.refreshComplete();
    }

    @Override
    public void onItemClick(View view, SubjectsBean data, int position) {
        if (view instanceof ImageView) {
            MovieDetailActivity.start(this, data, (ImageView) view);
        }
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onRefresh() {
        mPullRefresh = true;
        mStart = 0;
        loadData();
    }

    @Override
    public void onLoadMore() {
        loadData();
    }

    private void showLoading() {
        // 加载动画
        ll_progress_bar.setVisibility(View.VISIBLE);
        ImageView img = findViewById(R.id.img_progress);
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    private void showContentView() {
        // 停止动画
        if (ll_progress_bar.getVisibility() == View.VISIBLE) {
            ll_progress_bar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            if (mAnimationDrawable.isRunning()) {
                mAnimationDrawable.stop();
            }
        }
    }

    @Override
    public void onLoadingMore(View yourFooterView) {
        mFooterView.setState(LoadingFooterView.STATE_LOADING);
    }

    @Override
    public void onLoadMoreComplete(View yourFooterView) {
        mFooterView.setState(LoadingFooterView.STATE_COMPLETE);
    }

    @Override
    public void onSetNoMore(View yourFooterView, boolean isNoMore) {
        mFooterView.setState(isNoMore ? LoadingFooterView.STATE_NOMORE : LoadingFooterView.STATE_COMPLETE);
    }

}
