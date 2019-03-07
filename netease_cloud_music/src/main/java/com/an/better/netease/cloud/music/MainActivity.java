package com.an.better.netease.cloud.music;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.an.better.netease.cloud.music.book.BookFragment;
import com.an.better.netease.cloud.music.databinding.ActivityMainBinding;
import com.an.better.netease.cloud.music.douban.hot.DoubanFragment;
import com.an.better.netease.cloud.music.gank.GankFragment;
import com.an.better.netease.cloud.music.statusbar.StatusBarUtils;
import com.an.better.netease.cloud.music.webview.WebViewActivity;
import com.anbetter.beyond.adapter.FragmentAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initStatusView();

        StatusBarUtils.setColorNoTranslucentForDrawerLayout(MainActivity.this, mBinding.drawerLayout,
                getResources().getColor(R.color.colorTheme));

        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mBinding.llTitleMenu.setOnClickListener(this);
        mBinding.ivTitleGank.setOnClickListener(this);
        mBinding.ivTitleDou.setOnClickListener(this);
        mBinding.ivTitleOne.setOnClickListener(this);

        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new GankFragment());
        mFragmentList.add(new DoubanFragment());
        mFragmentList.add(new BookFragment());

        // 注意使用的是：getSupportFragmentManager
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mBinding.vpContent.setAdapter(adapter);
        mBinding.vpContent.addOnPageChangeListener(this);
        mBinding.vpContent.setOffscreenPageLimit(2);

        // 默认选中第一项
        mBinding.ivTitleGank.setSelected(true);
        mBinding.vpContent.setCurrentItem(0);

        View menuView = mBinding.navView.inflateHeaderView(R.layout.nav_header_main);
        menuView.findViewById(R.id.ll_nav_homepage).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = mBinding.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtils.getStatusBarHeight(this);
        mBinding.viewStatus.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:// 开启菜单
                mBinding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_gank:// 干货栏
                if (mBinding.vpContent.getCurrentItem() != 0) {
                    mBinding.ivTitleGank.setSelected(true);
                    mBinding.ivTitleOne.setSelected(false);
                    mBinding.ivTitleDou.setSelected(false);
                    mBinding.vpContent.setCurrentItem(0);
                }
                break;
            case R.id.iv_title_one:// 电影栏
                if (mBinding.vpContent.getCurrentItem() != 1) {
                    mBinding.ivTitleOne.setSelected(true);
                    mBinding.ivTitleGank.setSelected(false);
                    mBinding.ivTitleDou.setSelected(false);
                    mBinding.vpContent.setCurrentItem(1);
                }
                break;
            case R.id.iv_title_dou:// 书籍栏
                if (mBinding.vpContent.getCurrentItem() != 2) {
                    mBinding.ivTitleDou.setSelected(true);
                    mBinding.ivTitleOne.setSelected(false);
                    mBinding.ivTitleGank.setSelected(false);
                    mBinding.vpContent.setCurrentItem(2);
                }
                break;
            case R.id.ll_nav_homepage:
                WebViewActivity.loadUrl(this, "https://github.com/hpdx/Beyond", "Beyond");
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mBinding.ivTitleGank.setSelected(true);
                mBinding.ivTitleOne.setSelected(false);
                mBinding.ivTitleDou.setSelected(false);
                break;
            case 1:
                mBinding.ivTitleOne.setSelected(true);
                mBinding.ivTitleGank.setSelected(false);
                mBinding.ivTitleDou.setSelected(false);
                break;
            case 2:
                mBinding.ivTitleDou.setSelected(true);
                mBinding.ivTitleOne.setSelected(false);
                mBinding.ivTitleGank.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
