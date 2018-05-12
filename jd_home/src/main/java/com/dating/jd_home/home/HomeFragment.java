package com.dating.jd_home.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dating.jd_home.App;
import com.dating.jd_home.R;
import com.dating.jd_home.home.model.HomeIndex;
import com.dating.jd_home.home.model.ItemInfoListBean;
import com.facebook.fresco.helper.utils.StreamTool;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2018/3/16.
 */

public class HomeFragment extends Fragment {

    private MyHandler mHandler;
    private WorkThread mWorkThread;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private List<ItemInfoListBean> mListBeans;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListBeans = new ArrayList<>();
        mAdapter = new HomeAdapter(mListBeans);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mHandler = new MyHandler(this);
        mWorkThread = new WorkThread(mHandler);
        mWorkThread.start();
    }

    public void setData(HomeIndex homeIndex) {
        mListBeans.addAll(homeIndex.itemInfoList);
        mAdapter.setNewData(mListBeans);

//        mAdapter.notifyDataSetChanged();
//        MLog.i("mListBeans = " + mListBeans.size());
    }

    private static final class MyHandler extends Handler {

        private WeakReference<HomeFragment> mWeakFragment;

        public MyHandler(HomeFragment fragment) {
            mWeakFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeFragment fragment = mWeakFragment.get();
            if (fragment != null) {
                HomeIndex homeIndex = (HomeIndex)msg.obj;
                fragment.setData(homeIndex);
            }
        }
    }

    private static final class WorkThread extends Thread {

        private MyHandler mHandler;

        public WorkThread(MyHandler handler) {
            mHandler = handler;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = App.get().getAssets().open("homeindex.txt");
                String jsonStr = new String(StreamTool.read(inputStream));
                Gson gson = new Gson();
                HomeIndex homeIndex = gson.fromJson(jsonStr, HomeIndex.class);

                Message message = mHandler.obtainMessage();
                message.obj = homeIndex;
                mHandler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        if(mWorkThread != null) {
            mWorkThread.interrupt();
            mWorkThread = null;
        }

        if(mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }

        super.onDestroy();
    }

}
