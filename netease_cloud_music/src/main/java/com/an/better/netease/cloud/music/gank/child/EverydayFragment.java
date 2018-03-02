package com.an.better.netease.cloud.music.gank.child;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.gank.child.everyday.EverydayListRequest;
import com.an.better.netease.cloud.music.gank.child.everyday.EverydayListView;
import com.an.better.netease.cloud.music.gank.child.everyday.EverydayViewModel;
import com.an.better.netease.cloud.music.gank.child.everyday.adapter.EverydayAdapter;
import com.an.better.netease.cloud.music.gank.child.everyday.event.EverydayEventHandler;
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.BannerBean;
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.FocusBean;
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.TingBlock;
import com.an.better.netease.cloud.music.webview.WebViewActivity;
import com.anbetter.log.MLog;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.fragment.BaseListFragment;
import com.trident.dating.libcommon.listener.ResponseListener;

/**
 * 每日推荐
 *
 * Created by android_ls on 2018/2/11.
 */

public class EverydayFragment extends BaseListFragment<EverydayListRequest, EverydayListView,
        EverydayViewModel>  implements EverydayListView, EverydayEventHandler {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i("EverydayFragment--->onCreate");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_everyday;
    }

    @Override
    public void loadData() {
        Apis.getBanner(new ResponseListener<TingBlock>() {

            @Override
            public void onResponse(TingBlock response) {
                if (response.result != null
                        && response.result.focus != null) {
                    FocusBean focusBean = response.result.focus;
                    mList.setFocusBean(focusBean);

                    loadData(false);
                }
            }

            @Override
            public void onErrorResponse(Exception error) {

            }
        });
    }

    @Override
    protected BaseListAdapter createAdapter(EverydayListRequest data) {
        return new EverydayAdapter(data, this);
    }

    @Override
    protected EverydayViewModel createViewModel() {
        return new EverydayViewModel();
    }

    @Override
    protected EverydayListRequest getList() {
        return new EverydayListRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MLog.i("EverydayFragment--->onDestroy");
    }

    @Override
    public void OnBannerClick(BannerBean bannerBean, int position) {
        if (bannerBean.code != null && (bannerBean.code.startsWith("http")
                || bannerBean.code.startsWith("https"))) {
            WebViewActivity.loadUrl(mContext, bannerBean.code, "加载中...");
        }
    }


}
