package com.an.better.netease.cloud.music.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.R;
import com.anbetter.log.MLog;

/**
 * 干货定制
 *
 * Created by android_ls on 2018/2/11.
 */

public class CustomFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i("CustomFragment--->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLog.i("CustomFragment--->onDestroy");
    }

}
