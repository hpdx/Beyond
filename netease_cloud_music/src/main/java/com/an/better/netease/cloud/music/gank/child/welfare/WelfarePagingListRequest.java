package com.an.better.netease.cloud.music.gank.child.welfare;

import android.net.Uri;

import com.an.better.netease.cloud.music.api.ApiUrls;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.trident.beyond.model.BasePaginatedListRequest;
import com.trident.dating.libcommon.IRequest;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/23.
 */

public class WelfarePagingListRequest extends BasePaginatedListRequest<GankDataBlock, GanKInfo> {

    private static final int PAGE_SIZE = 20;
    private int page = 1;

    @Override
    public String getUrl() {
        Uri.Builder builder = Uri.withAppendedPath(Uri.parse(ApiUrls.GANK_BASE_URL),
                ApiUrls.GANK_MEIZI_URL).buildUpon();
        builder.appendPath(String.valueOf(PAGE_SIZE));
        builder.appendPath(String.valueOf(page));
        return builder.build().toString();
    }

    @Override
    protected String getNextPageUrl() {
        page++;
        return getUrl();
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getGankMeizi(url, this);
    }

    @Override
    protected List<GanKInfo> getItemsFromResponse(GankDataBlock response) {
        return response.results;
    }

}
