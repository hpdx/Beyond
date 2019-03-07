package com.anbetter.beyond.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.anbetter.beyond.host.BinderTab;
import com.anbetter.beyond.model.TabData;

import java.util.List;

/**
 *
 * Created by android_ls on 16/7/26.
 */
public abstract class BaseViewPagerAdapter<M, Tab extends BinderTab> extends PagerAdapter {

    protected List<TabData> mTabDataList;
    protected Context mContext;

    public BaseViewPagerAdapter(Context context, List<TabData> tabDataList) {
        this.mContext = context;
        this.mTabDataList = tabDataList;
    }

    public abstract M getDataSource(TabData tabData);

    public abstract Tab getTabLayout(Context context, TabData tabData);

    @Override
    public int getCount() {
        return mTabDataList.size();
    }

    @Override
    public String getPageTitle(int index) {
        return mTabDataList.get(index).title;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TabData tabData = mTabDataList.get(position);
        if (tabData.dataSource == null) {
            tabData.dataSource = getDataSource(tabData);
        }

        BinderTab tab = getTabLayout(mContext, tabData);
        if (tabData.instanceState != null) {
            tab.onRestoreInstanceState(tabData.instanceState);
        }
        tab.onViewCreated();
        tab.onActivityCreated();
        container.addView(tab.getView());
        return tab;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        BinderTab tab = (BinderTab) object;
        if (mTabDataList != null && mTabDataList.size() > 0) {
            TabData tabData = mTabDataList.get(position);
            tabData.instanceState = tab.onSaveInstanceState();
        }

        if (tab.getView() != null) {
            container.removeView(tab.getView());
        }

        tab.onDestroyView();
        tab.onDestroy();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        View oldView = ((BinderTab) object).getView();
        return oldView != null && oldView == view;
    }

    public void recycler() {
        mTabDataList = null;
        mContext = null;
    }

}
