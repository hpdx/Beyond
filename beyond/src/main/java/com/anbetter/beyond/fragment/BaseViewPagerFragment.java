package com.anbetter.beyond.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.anbetter.beyond.R;
import com.anbetter.beyond.adapter.BaseViewPagerAdapter;
import com.anbetter.beyond.host.BinderFragment;
import com.anbetter.beyond.host.PageFragmentHost;
import com.anbetter.beyond.host.PageTabHost;
import com.anbetter.beyond.model.TabData;
import com.anbetter.beyond.mvvm.MvvmBaseFragment;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.widgets.viewpagertab.NavTabContainer;
import com.anbetter.beyond.widgets.viewpagertab.OnNavTabItemClickListener;
import com.anbetter.log.MLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 16/6/3.
 */
public abstract class BaseViewPagerFragment<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmBaseFragment<M, V, VM> implements BinderFragment, ViewPager.OnPageChangeListener,
        PageTabHost, OnNavTabItemClickListener {

    private static final String TAB_LAYOUT_INSTANCE_STATES = "TabLayoutInstanceStates";
    private static final String CURRENT_SELECTED_ITEM = "CurrentSelectedItem";

    private static final String TAG = "BaseViewPagerFragment";

    protected Context mContext;
    protected PageFragmentHost mPageFragmentHost;

    protected ViewPager mViewPager;
    protected NavTabContainer mTabContainer;
    protected BaseViewPagerAdapter mTabbedAdapter;

    protected List<TabData> mTabDataList;
    protected int mRestoreSelectedPanel;
    protected Bundle mSavedInstanceState;

    public BaseViewPagerFragment() {
        mSavedInstanceState = new Bundle();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    protected int getLayoutRes() {
        return R.layout.page_view_page_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mTabContainer = (NavTabContainer) view.findViewById(R.id.pager_tab_container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPageFragmentHost == null) {
            mContext = getActivity();
            if (getActivity() instanceof PageFragmentHost) {
                mPageFragmentHost = (PageFragmentHost) getActivity();
            }
        }

        if (savedInstanceState != null) {
            mSavedInstanceState = savedInstanceState;
            restoreState();
        }

        rebindActionBar();
        loadData(false);
    }

    protected void showTabs() {
        showTabs(0);
    }

    protected void showTabs(int selectPosition) {
        if (mTabDataList == null) {
            mTabDataList = getTabData();
        }

        mTabbedAdapter = createAdapter(mContext, mTabDataList);
        mViewPager.setAdapter(mTabbedAdapter);
        mViewPager.addOnPageChangeListener(this);

        mRestoreSelectedPanel = selectPosition;
        showTabIndicator();
    }

    protected abstract List<TabData> getTabData();

    protected abstract BaseViewPagerAdapter createAdapter(Context context, List<TabData> tabDataList);

    protected void showTabIndicator() {
        mTabContainer.setTabTextSizes(13, 19);
        mTabContainer.setTabTextColors(ContextCompat.getColor(mContext, R.color.gray_969696),
                ContextCompat.getColor(mContext, R.color.black_464646));
        mTabContainer.setSelectedIndicatorColor(
                ContextCompat.getColor(mContext, R.color.transparent));

        mTabContainer.setOnItemClickListener(this);
        mTabContainer.setData(mTabDataList, mRestoreSelectedPanel);
        mTabContainer.setVisibility(View.VISIBLE);

        mTabContainer.onPageSelected(mRestoreSelectedPanel);
        mViewPager.setCurrentItem(mRestoreSelectedPanel);
    }

    @Override
    public void OnNavTabItemClick(View view, int position) {
        mRestoreSelectedPanel = position;
        mViewPager.setCurrentItem(mRestoreSelectedPanel);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        recordState();
        outState.putAll(mSavedInstanceState);
        super.onSaveInstanceState(outState);
    }

    protected void recordState() {
        if (mViewPager == null || mTabDataList.size() == 0) {
            return;
        }

        ArrayList<Bundle> instanceStates = new ArrayList<>();
        for (TabData tabData : mTabDataList) {
            if (tabData.instanceState != null) {
                instanceStates.add(tabData.instanceState);
            }
        }
        mSavedInstanceState.putParcelableArrayList(TAB_LAYOUT_INSTANCE_STATES, instanceStates);

        if (mViewPager.getCurrentItem() > 0) {
            mSavedInstanceState.putInt(CURRENT_SELECTED_ITEM, mViewPager.getCurrentItem());
        }
    }

    protected void restoreState() {
        if (mViewPager == null || mTabDataList == null || mTabDataList.size() == 0) {
            return;
        }

        if (mSavedInstanceState.containsKey(TAB_LAYOUT_INSTANCE_STATES)) {
            List<Bundle> restoreScrollPositions = mSavedInstanceState.getParcelableArrayList(TAB_LAYOUT_INSTANCE_STATES);
            if (restoreScrollPositions != null && restoreScrollPositions.size() > 0
                    && restoreScrollPositions.size() == mTabDataList.size()) {
                for (int i = 0; i < restoreScrollPositions.size(); i++) {
                    TabData tabData = mTabDataList.get(i);
                    tabData.instanceState = restoreScrollPositions.get(i);
                }
            }
        }

        if (mSavedInstanceState.containsKey(CURRENT_SELECTED_ITEM)) {
            if (mSavedInstanceState.getInt(CURRENT_SELECTED_ITEM, 0) > 0) {
                mRestoreSelectedPanel = mSavedInstanceState.getInt(CURRENT_SELECTED_ITEM, 0);
            }
        }
    }

    @Override
    public void rebindActionBar() {
        if (mPageFragmentHost != null) {
            mPageFragmentHost.toggleActionBar(false);
        }
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public boolean onMenuBackClick(MenuItem item) {
        return onBackPressed();
    }

    @Override
    public void onDestroyView() {
        if (mTabContainer != null) {
            mTabContainer.recycler();
            mTabContainer = null;
        }

        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
            mViewPager.setAdapter(null);
            mViewPager = null;
        }

        if (mTabbedAdapter != null) {
            mTabbedAdapter.recycler();
            mTabbedAdapter = null;
        }

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 该方法请慎重调用，执行过后会清空如下数据及状态：
     * 1、当前列表中item滚动的位置
     * 2、当前选中的Page
     * 3、ViewPager中所有Page的数据
     * <p>
     * 推荐在子类的{@link #onDestroy()}中调用，示例如下
     *
     * @Override public void onDestroy() {
     * super.onDestroy();
     * // 重置当前Page的所有状态
     * resetInstanceState();
     * }
     */
    protected void resetInstanceState() {
        mRestoreSelectedPanel = 0;
        mTabDataList = null;
    }

    /**
     * 指定当前选中那个Tab，推荐在子类的{@link #***onActivityCreated(@Nullable Bundle savedInstanceState)}中调用，示例如下
     *
     * @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
     * super.onActivityCreated(savedInstanceState);
     * // 指定当前选中那个Tab
     * setCurrentPage();
     * }
     */
    protected void setCurrentPage() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(mRestoreSelectedPanel);
                }
            }
        }, 20);
    }

    /**
     * 切换当前选中的Tab
     *
     * @param position Tab的下标
     */
    public void switchToTab(int position) {
        if (isAdded() && mViewPager != null && mTabContainer != null
                && position < mTabbedAdapter.getCount()) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mTabContainer.onPageScrolled(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
//        MLog.i(TAG, "onPageSelected position = " + position);
        mRestoreSelectedPanel = position;
        mTabContainer.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mTabContainer.onPageScrollStateChanged(state);
    }

    @Override
    public void showBannerTips(String message) {
        if (mPageFragmentHost != null) {
            mPageFragmentHost.showGlobalBannerTips(message);
        }
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }
}
