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

import com.an.better.netease.cloud.music.adapter.MyFragmentPagerAdapter;
import com.an.better.netease.cloud.music.book.BookFragment;
import com.an.better.netease.cloud.music.databinding.ActivityMainBinding;
import com.an.better.netease.cloud.music.douban.DoubanFragment;
import com.an.better.netease.cloud.music.gank.GankFragment;
import com.an.better.netease.cloud.music.statusbar.StatusBarUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initStatusView();

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, mBinding.drawerLayout,
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
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mBinding.vpContent.setAdapter(adapter);
        mBinding.vpContent.addOnPageChangeListener(this);

        // 默认选中第一项
        mBinding.ivTitleGank.setSelected(true);
        mBinding.vpContent.setCurrentItem(0);

        mBinding.navView.inflateHeaderView(R.layout.nav_header_main);

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
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
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
//            case R.id.iv_avatar: // 头像进入GitHub
//                WebViewActivity.loadUrl(v.getContext(),CommonUtils.getString(R.string.string_url_cloudreader),"CloudReader");
//                break;
//            case R.id.ll_nav_exit:// 退出应用
//                finish();
//                break;
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
