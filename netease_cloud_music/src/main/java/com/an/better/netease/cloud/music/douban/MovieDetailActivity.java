package com.an.better.netease.cloud.music.douban;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.binding.adapter.BlurTransformation;
import com.an.better.netease.cloud.music.databinding.HeaderTitleBarBinding;
import com.an.better.netease.cloud.music.databinding.MovieDetailContentBinding;
import com.an.better.netease.cloud.music.databinding.MovieDetailHeaderBinding;
import com.an.better.netease.cloud.music.douban.model.CastsBean;
import com.an.better.netease.cloud.music.douban.model.MovieDetailInfo;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.an.better.netease.cloud.music.statusbar.StatusBarUtils;
import com.an.better.netease.cloud.music.utils.CommonUtils;
import com.an.better.netease.cloud.music.utils.Utils;
import com.an.better.netease.cloud.music.webview.WebViewActivity;
import com.anbetter.log.MLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.dating.libcommon.listener.ResponseListener;

import java.util.ArrayList;

/**
 *
 * Created by android_ls on 2018/3/5.
 */

public class MovieDetailActivity extends AppCompatActivity implements OnItemClickListener<CastsBean> {

    private HeaderTitleBarBinding bindingTitleView;
    private MovieDetailHeaderBinding bindingHeaderView;
    private MovieDetailContentBinding bindingContentView;

    private SubjectsBean mSubjectsBean;
    private MovieDetailInfo mMovieDetailInfo;

    // 滑动多少距离后标题不透明
    private int slidingDistance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        FrameLayout title_container = findViewById(R.id.title_container);
        FrameLayout header_container = findViewById(R.id.header_container);
        FrameLayout container = findViewById(R.id.container);
        NestedScrollView nested_scroll_view = findViewById(R.id.nested_scroll_view);

        LayoutInflater layoutInflater = getLayoutInflater();
        bindingTitleView = HeaderTitleBarBinding.inflate(layoutInflater, null, false);
        title_container.addView(bindingTitleView.getRoot());

        bindingHeaderView = MovieDetailHeaderBinding.inflate(layoutInflater, null, false);
        header_container.addView(bindingHeaderView.getRoot());

        bindingContentView = MovieDetailContentBinding.inflate(layoutInflater, null, false);
        container.addView(bindingContentView.getRoot());

        mSubjectsBean = getIntent().getParcelableExtra("subjectsBean");
        // 设置toolbar
        setToolBar();

        // 设置toolbar的背景
        if (mSubjectsBean != null && mSubjectsBean.images != null) {
            if(!TextUtils.isEmpty(mSubjectsBean.images.medium)) {
                setImgHeaderBg(mSubjectsBean.images.medium);
            }
        }

        initSlideShapeTheme();

        nested_scroll_view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });

        // 标题
        bindingTitleView.tbBaseTitle.setTitle(mSubjectsBean.title);
        // 副标题
        String casts = String.format("主演：%s", Utils.formatName(mSubjectsBean.casts));
        bindingTitleView.tbBaseTitle.setSubtitle(casts);

        bindingHeaderView.setSubjectsBean(mSubjectsBean);
        bindingHeaderView.executePendingBindings();

        Apis.getMovieDetail(String.valueOf(mSubjectsBean.id),
                new ResponseListener<MovieDetailInfo>() {
            @Override
            public void onResponse(MovieDetailInfo response) {
                mMovieDetailInfo = response;

                // 上映日期
                bindingHeaderView.tvOneDay.setText(String.format("上映日期：%s", mMovieDetailInfo.year));
                // 制片国家
                bindingHeaderView.tvOneCity.setText(String.format("制片国家/地区：%s", Utils.formatGenres(mMovieDetailInfo.countries)));
                bindingContentView.setBean(mMovieDetailInfo);
                bindingContentView.executePendingBindings();

                ArrayList<CastsBean> castsBeans = new ArrayList<>();
                castsBeans.addAll(mMovieDetailInfo.casts);
                castsBeans.addAll(mMovieDetailInfo.directors);
                MovieDetailAdapter adapter = new MovieDetailAdapter(MovieDetailActivity.this, castsBeans, MovieDetailActivity.this);
                bindingContentView.recyclerView.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this));
                // 需加，不然滑动不流畅
                bindingContentView.recyclerView.setNestedScrollingEnabled(false);
                bindingContentView.recyclerView.setHasFixedSize(true);
                bindingContentView.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(Exception error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail, menu);
        return true;
    }

    /**
     * 根据页面滑动距离改变Header方法
     */
    private void scrollChangeHeader(int scrolledY) {
        if (scrolledY < 0) {
            scrolledY = 0;
        }

        Drawable drawable = bindingTitleView.ivBaseTitlebarBg.getDrawable();
        if (drawable == null) {
            return;
        }

        if (scrolledY <= slidingDistance) {
            // title部分的渐变
            float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);
            drawable.mutate().setAlpha((int) (alpha * 255));
            bindingTitleView.ivBaseTitlebarBg.setImageDrawable(drawable);
        } else {
            drawable.mutate().setAlpha(255);
            bindingTitleView.ivBaseTitlebarBg.setImageDrawable(drawable);
        }
    }

    /**
     * 初始化滑动渐变
     */
    protected void initSlideShapeTheme() {
        // toolbar 的高
        int toolbarHeight = bindingTitleView.tbBaseTitle.getLayoutParams().height;
        final int headerBgHeight = toolbarHeight + StatusBarUtils.getStatusBarHeight(this);
        MLog.i("headerBgHeight = " + headerBgHeight);

        // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
        ViewGroup.LayoutParams params = bindingTitleView.ivBaseTitlebarBg.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) bindingTitleView.ivBaseTitlebarBg.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);

        bindingTitleView.ivBaseTitlebarBg.setImageAlpha(0);
        StatusBarUtils.setTranslucentImageHeader(this, 0, bindingTitleView.tbBaseTitle);

        // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) bindingHeaderView.imgItemBg.getLayoutParams();
        layoutParams.setMargins(0, -StatusBarUtils.getStatusBarHeight(this), 0, 0);

        ViewGroup.LayoutParams imgItemBgparams = bindingHeaderView.imgItemBg.getLayoutParams();
        // 获得高斯图背景的高度
        int imageBgHeight = imgItemBgparams.height;

        // 变色
        int titleBarAndStatusHeight = (int) (CommonUtils.dip2px(R.dimen.nav_bar_height) + StatusBarUtils.getStatusBarHeight(this));
        // 减掉后，没到顶部就不透明了
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (CommonUtils.dip2px(R.dimen.base_header_activity_slide_more));
    }

    /**
     * 加载titlebar背景
     */
    private void setImgHeaderBg(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            // 高斯模糊背景 原来 参数：12,5  23,4
            RequestOptions options = new RequestOptions()
                    .optionalTransform(new BlurTransformation(this, 23, 4))
                    .placeholder(R.drawable.stackblur_default)
                    .error(R.drawable.stackblur_default);

            Glide.with(this)
                    .load(imgUrl)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            bindingTitleView.tbBaseTitle.setBackgroundColor(Color.TRANSPARENT);
                            bindingTitleView.ivBaseTitlebarBg.setImageAlpha(0);
                            bindingTitleView.ivBaseTitlebarBg.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(bindingTitleView.ivBaseTitlebarBg);
        }
    }

    /**
     * toolbar 单击"更多信息"
     */
    protected void setTitleClickMore() {

    }

    /**
     * 设置toolbar
     */
    protected void setToolBar() {
        setSupportActionBar(bindingTitleView.tbBaseTitle);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        // 手动设置才有效果
        bindingTitleView.tbBaseTitle.setTitleTextAppearance(this, R.style.ToolBar_Title);
        bindingTitleView.tbBaseTitle.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);
        bindingTitleView.tbBaseTitle.inflateMenu(R.menu.movie_detail);
        bindingTitleView.tbBaseTitle.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        bindingTitleView.tbBaseTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bindingTitleView.tbBaseTitle.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionbar_more:// 更多信息
                        setTitleClickMore();
                        break;
                }
                return false;
            }
        });
    }

    public static void start(Activity context, SubjectsBean subjectsBean, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("subjectsBean", subjectsBean);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, CommonUtils.getString(R.string.transition_movie_img));//与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    @Override
    public void onItemClick(View view, CastsBean data, int position) {
        WebViewActivity.loadUrl(MovieDetailActivity.this, data.alt, "加载中...");
    }

}
