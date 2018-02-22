package com.gank.tab.multi.type;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gank.tab.multi.type.adapter.MultiTypeAdapter;
import com.trident.beyond.adapter.BaseViewPagerAdapter;
import com.trident.beyond.fragment.BaseViewPagerFragment;
import com.trident.beyond.model.BaseModel;
import com.trident.beyond.model.TabData;
import com.trident.beyond.view.BaseView;
import com.trident.beyond.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2017/1/3.
 */

public class MultiTypeTabFragment extends BaseViewPagerFragment<BaseModel, BaseView, BaseViewModel> {

    public static MultiTypeTabFragment newInstance() {
        MultiTypeTabFragment fragment = new MultiTypeTabFragment();
        return fragment;
    }

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        mPageFragmentHost.setActionBarTitle("ViewPager MultiType Tab");
        mPageFragmentHost.displayActionBarBack(false);
    }

    @Override
    protected List<TabData> getTabData() {
        List<TabData> tabDataList = new ArrayList<>();

        TabData tabData2 = new TabData();
        tabData2.title = "多中类型的ViewHolder";
        tabData2.type = 1;
        tabDataList.add(tabData2);

        TabData tabData3 = new TabData();
        tabData3.title = "List";
        tabData3.type = 2;
        tabDataList.add(tabData3);

        TabData tabData4 = new TabData();
        tabData4.title = "Paging List";
        tabData4.type = 3;
        tabDataList.add(tabData4);

        Bundle bundle = new Bundle();
        bundle.putString("url", "http://blog.csdn.net/android_ls");

        TabData tabData5 = new TabData();
        tabData5.title = "Web页面";
        tabData5.type = 4;
        tabData5.data = bundle;
        tabDataList.add(tabData5);

        TabData tabData6 = new TabData();
        tabData6.title = "手写List Adapter";
        tabData6.type = 5;
        tabDataList.add(tabData6);

        TabData tabData7 = new TabData();
        tabData7.title = "手写Paging List Adapter";
        tabData7.type = 6;
        tabDataList.add(tabData7);
//
//        TabData tabData = new TabData();
//        tabData.title = "Page";
//        tabData.arg1 = 0;
//        tabDataList.add(tabData);

        return tabDataList;
    }

    @Override
    protected BaseViewPagerAdapter createAdapter(Context context, List<TabData> tabDataList) {
        return new MultiTypeAdapter(context, tabDataList);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showTabs();
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(BaseModel data) {

    }

    @Override
    protected BaseViewModel createViewModel() {
        return new BaseViewModel();
    }

}
