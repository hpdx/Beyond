package com.an.better.netease.cloud.music.api;

import android.net.Uri;

import com.an.better.netease.cloud.music.douban.model.HotMovieBlock;
import com.an.better.netease.cloud.music.douban.model.MovieDetailInfo;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayBlock;
import com.an.better.netease.cloud.music.gank.child.music.model.TingBlock;
import com.an.better.netease.cloud.music.gank.child.welfare.GankDataBlock;
import com.trident.dating.libcommon.IRequest;
import com.trident.dating.libcommon.listener.ResponseListener;
import com.trident.dating.libnetwork.okhttp.OKHttpRequest;

/**
 *
 *
    Uri.Builder builder = Uri.withAppendedPath(Uri.parse(ApiUrls.GANK_BASE_URL),
    ApiUrls.GANK_MEIZI_URL).buildUpon();
    builder.appendPath("");
    builder.appendQueryParameter("last_timestamp", "");
    builder.appendQueryParameter("page_size", "");
    String url = builder.build().toString();
 *
 * Created by android_ls on 2018/2/11.
 */

public class Apis {

    /**
     * 百度音乐首页数据
     * @param listener
     */
    public static IRequest getBaiduMusic(ResponseListener<TingBlock> listener) {
        String url = Uri.withAppendedPath(Uri.parse(ApiUrls.TING_BASE_URL),
                ApiUrls.HOME_BANNER_URL).buildUpon().toString();
        return new OKHttpRequest<>(url, listener, TingBlock.class).enqueue();
    }

    /**
     * 获取每天的干货
     * @param url
     * @param listener
     * @return
     */
    public static IRequest getGankDay(String url, final ResponseListener<GanKDayBlock> listener) {
        return new OKHttpRequest<>(url, listener, GanKDayBlock.class).enqueue();
    }

    /**
     * 获取妹子图数据
     * @param url
     * @param listener
     * @return
     */
    public static IRequest getGankMeizi(String url, final ResponseListener<GankDataBlock> listener) {
        return new OKHttpRequest<>(url, listener, GankDataBlock.class).enqueue();
    }

    /**
     * 获取豆瓣热门电影
     * @param url
     * @param listener
     * @return
     */
    public static IRequest getHotMovies(String url, final ResponseListener<HotMovieBlock> listener) {
        return new OKHttpRequest<>(url, listener, HotMovieBlock.class).enqueue();
    }

    /**
     * 获取电影详情
     * @param movieId
     * @param listener
     */
    public static void getMovieDetail(String movieId, ResponseListener<MovieDetailInfo> listener) {
        Uri.Builder builder = Uri.withAppendedPath(Uri.parse(ApiUrls.DOUBAN_BASE_URL),
                ApiUrls.MOVIE_DETAIL_URL).buildUpon();
        builder.appendPath(movieId);
        new OKHttpRequest<>(builder.toString(), listener, MovieDetailInfo.class).enqueue();
    }

    /**
     * 获取豆瓣电影top250
     * @param start
     * @param listener
     */
    public static void getMovieTop250(int start, ResponseListener<HotMovieBlock> listener) {
        Uri.Builder builder = Uri.withAppendedPath(Uri.parse(ApiUrls.DOUBAN_BASE_URL),
                ApiUrls.MOVIE_TOP_250_URL).buildUpon();
        builder.appendQueryParameter("start", String.valueOf(start));
        builder.appendQueryParameter("count", String.valueOf(20));
        new OKHttpRequest<>(builder.toString(), listener, HotMovieBlock.class).enqueue();
    }

}
