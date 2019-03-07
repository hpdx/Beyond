package com.an.better.netease.cloud.music.gank.child.everyday;

import android.net.Uri;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.api.ApiUrls;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayBanner;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayBlock;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayCategory;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayInfo;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.utils.Utils;
import com.anbetter.beyond.model.BaseListRequest;
import com.anbetter.beyond.model.IRequest;
import com.anbetter.beyond.model.Section;
import com.anbetter.log.MLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2018/2/11.
 */

public class EverydayListRequest extends BaseListRequest<GanKDayBlock, Section<Integer, Object>> {

    public static final int VIEW_TYPE_BANNER = 1;
    public static final int VIEW_TYPE_BLOCK_TITLE = 2;
    public static final int VIEW_TYPE_MEIZI = 3;
    public static final int VIEW_TYPE_IOS_BLOCK = 4;
    public static final int VIEW_TYPE_REST_MOVIE = 5;
    public static final int VIEW_TYPE_RECOMMEND = 6;
    public static final int VIEW_TYPE_FRONT_WEB = 7;
    public static final int VIEW_TYPE_APP = 8;
    public static final int VIEW_TYPE_ANDROID = 9;

    @Override
    public String getUrl() {
        return Uri.withAppendedPath(Uri.parse(ApiUrls.GANK_BASE_URL),
                ApiUrls.GANK_DAY_URL).buildUpon().toString();
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getGankDay(url, this);
    }

    @Override
    protected List<Section<Integer, Object>> getItemsFromResponse(GanKDayBlock response) {
        GanKDayInfo ganKDayInfo = response.results;
        ArrayList<Section<Integer, Object>> cellMapping = new ArrayList<>();

        // 福利
        if (ganKDayInfo.welfare != null && ganKDayInfo.welfare.size() > 0) {
//            cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("福利", R.drawable.home_title_meizi)));
            GanKInfo ganKInfo = ganKDayInfo.welfare.get(0);
            cellMapping.add(new Section<>(VIEW_TYPE_MEIZI, new GanKDayBanner(ganKInfo.url)));
        }

        // iOS
        ArrayList<GanKInfo> iosShows = ganKDayInfo.ios;
        if (iosShows != null && iosShows.size() > 0) {
            cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("iOS相关资源", R.drawable.home_title_ios)));
            for (GanKInfo ganKInfo : iosShows) {
                ganKInfo.imageUrl = Utils.getTwoImageUrl();
            }
            cellMapping.add(new Section<>(VIEW_TYPE_IOS_BLOCK, iosShows));
        }

        // android
        ArrayList<GanKInfo> android = ganKDayInfo.android;
        if (android != null && android.size() > 0) {
            cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("Android相关资源",
                    R.drawable.home_title_android)));
            for (GanKInfo ganKInfo : android) {
                ganKInfo.imageUrl = Utils.getTwoImageUrl();
            }
            cellMapping.add(new Section<>(VIEW_TYPE_ANDROID, android));
        }

        // 瞎推荐
        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("瞎推荐", R.drawable.home_title_xia)));
        ArrayList<GanKInfo> recommend = ganKDayInfo.recommend;
        if (recommend != null && recommend.size() > 0) {
            for (GanKInfo ganKInfo : recommend) {
                ganKInfo.imageUrl = Utils.getThreeImageUrl();
            }
        } else {
            if (recommend == null) {
                recommend = new ArrayList<>();
            }
            recommend.add(new GanKInfo("美美哒", Utils.getThreeImageUrl()));
            recommend.add(new GanKInfo("你好妖娆啊", Utils.getThreeImageUrl()));
            recommend.add(new GanKInfo("虚无的剑气，我飘啊飘~~", Utils.getThreeImageUrl()));
        }
        cellMapping.add(new Section<>(VIEW_TYPE_RECOMMEND, recommend));

        // app相关资源
        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("App", R.drawable.home_title_app)));
        ArrayList<GanKInfo> apps = ganKDayInfo.app;
        if (apps != null && apps.size() > 0) {
            for (GanKInfo ganKInfo : apps) {
                ganKInfo.imageUrl = Utils.getThreeImageUrl();
            }
        } else {
            if (apps == null) {
                apps = new ArrayList<>();
            }
            apps.add(new GanKInfo("美美哒", Utils.getThreeImageUrl()));
        }
        cellMapping.add(new Section<>(VIEW_TYPE_APP, apps));

        // 前端
        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("前端", R.drawable.home_title_qian)));
        ArrayList<GanKInfo> webShows = ganKDayInfo.front;
        if (webShows != null && webShows.size() > 0) {
            MLog.i("webShows.size() = " + webShows.size());
            for (GanKInfo ganKInfo : webShows) {
                ganKInfo.imageUrl = Utils.getThreeImageUrl();
            }
        }

        if (webShows == null) {
            webShows = new ArrayList<>();
        }
        webShows.add(new GanKInfo("美美哒", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("你好妖娆啊", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("虚无的剑气，我飘啊飘~~", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("我不~~", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("摸摸头~~", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("宝宝不哭，要乖哦", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("爱心（喜欢你）喜欢你，想告诉全世界", Utils.getThreeImageUrl()));
        webShows.add(new GanKInfo("男朋友，送你男朋友，夜里不孤单", Utils.getThreeImageUrl()));
        cellMapping.add(new Section<>(VIEW_TYPE_FRONT_WEB, webShows));

        // 休息视频
        ArrayList<GanKInfo> restMovie = ganKDayInfo.restMovie;
        if (restMovie != null && restMovie.size() > 0) {
            cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("休息视频", R.drawable.home_title_movie)));
            for (GanKInfo ganKInfo : restMovie) {
                ganKInfo.imageUrl = Utils.getThreeImageUrl();
            }
            cellMapping.add(new Section<>(VIEW_TYPE_REST_MOVIE, restMovie));
        }

        return cellMapping;
    }

}
