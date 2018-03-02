package com.an.better.netease.cloud.music.douban;

import android.net.Uri;
import android.text.TextUtils;

import com.an.better.netease.cloud.music.api.ApiUrls;
import com.an.better.netease.cloud.music.api.Apis;
import com.an.better.netease.cloud.music.douban.model.HotMovieBlock;
import com.an.better.netease.cloud.music.douban.model.HotMovieTitle;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.trident.beyond.model.BaseListRequest;
import com.trident.beyond.model.IModel;
import com.trident.dating.libcommon.IRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2018/3/2.
 */

public class DoubanListRequest extends BaseListRequest<HotMovieBlock, IModel> {

    @Override
    public String getUrl() {
        return Uri.withAppendedPath(Uri.parse(ApiUrls.DOUBAN_BASE_URL),
                ApiUrls.DOUBAN_HOT_MOVIE_URL).buildUpon().toString();
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getHotMovies(url, this);
    }

    @Override
    protected List<IModel> getItemsFromResponse(HotMovieBlock response) {
        List<IModel> models = new ArrayList<>();
        if(!TextUtils.isEmpty(response.title)) {
            models.add(new HotMovieTitle(response.title));
        }

        List<SubjectsBean> subjects = response.subjects;
        if(subjects != null && subjects.size() > 0) {
            models.addAll(subjects);
        }
        return models;
    }

}
