package com.an.better.netease.cloud.music.gank.child.music;

import android.net.Uri;

import com.an.better.netease.cloud.music.api.ApiUrls;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.gank.child.music.model.FocusBean;
import com.an.better.netease.cloud.music.gank.child.music.model.Mix1Bean;
import com.an.better.netease.cloud.music.gank.child.music.model.MovieSongs;
import com.an.better.netease.cloud.music.gank.child.music.model.SongMenuBean;
import com.an.better.netease.cloud.music.gank.child.music.model.TingBlock;
import com.trident.beyond.model.BaseListRequest;
import com.trident.beyond.model.Section;
import com.trident.dating.libcommon.IRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by android_ls on 2018/2/11.
 */

public class MusicListRequest extends BaseListRequest<TingBlock, Section<Integer, Object>> {

    public static final int VIEW_TYPE_BANNER = 1;
    public static final int VIEW_TYPE_FIRST_PUBLISH = 2;
    public static final int VIEW_TYPE_SONG_MENU = 3;
    public static final int VIEW_TYPE_MOVIE_SONGS = 4;

    @Override
    public String getUrl() {
        return Uri.withAppendedPath(Uri.parse(ApiUrls.TING_BASE_URL),
                ApiUrls.HOME_BANNER_URL).buildUpon().toString();
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getBaiduMusic(this);
    }

    @Override
    protected List<Section<Integer, Object>> getItemsFromResponse(TingBlock response) {
        ArrayList<Section<Integer, Object>> cellMapping = new ArrayList<>();

        if(response.result == null) {
            return Collections.emptyList();
        }

        // Banner
        if (response.result.focus != null) {
            FocusBean focusBean = response.result.focus;
            cellMapping.add(new Section<>(MusicListRequest.VIEW_TYPE_BANNER, focusBean));
        }

        // 首发
        Mix1Bean mix1Bean = response.result.mix_1;
        if (mix1Bean != null) {
            cellMapping.add(new Section<>(MusicListRequest.VIEW_TYPE_FIRST_PUBLISH, mix1Bean));
        }

        // 歌单精选
        if(response.result.diy != null) {
            List<SongMenuBean> songMenuBeanList = response.result.diy.result;
            if(songMenuBeanList != null && songMenuBeanList.size() > 0) {
                cellMapping.add(new Section<>(MusicListRequest.VIEW_TYPE_SONG_MENU, songMenuBeanList));
            }
        }

        // 影视歌曲
        if(response.result.recsong != null) {
            List<MovieSongs> movieSongsList = response.result.recsong.result;
            if(movieSongsList != null && movieSongsList.size() > 0) {
                cellMapping.add(new Section<>(MusicListRequest.VIEW_TYPE_MOVIE_SONGS, movieSongsList));
            }
        }

        return cellMapping;
    }

}
