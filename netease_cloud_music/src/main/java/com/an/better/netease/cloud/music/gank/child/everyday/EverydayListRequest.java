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
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.FocusBean;
import com.an.better.netease.cloud.music.utils.Utils;
import com.anbetter.log.MLog;
import com.trident.beyond.core.Section;
import com.trident.beyond.model.BaseListRequest;
import com.trident.dating.libcommon.IRequest;

import java.util.ArrayList;
import java.util.List;

/**
 *
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

    private FocusBean focusBean;

    public void setFocusBean(FocusBean focusBean) {
        this.focusBean = focusBean;
    }

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

        if(focusBean != null) {
            cellMapping.add(new Section<>(EverydayListRequest.VIEW_TYPE_BANNER, focusBean));
        }

        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("福利", R.drawable.home_title_meizi)));

        // 福利
        if (ganKDayInfo.welfare != null && ganKDayInfo.welfare.size() > 0) {
            GanKInfo ganKInfo = ganKDayInfo.welfare.get(0);
            cellMapping.add(new Section<>(VIEW_TYPE_MEIZI, new GanKDayBanner(ganKInfo.url)));
        }

        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("iOS相关资源", R.drawable.home_title_ios)));

        // iOS
        ArrayList<GanKInfo> iosShows = ganKDayInfo.ios;
        if (iosShows != null && iosShows.size() > 0) {
            cellMapping.add(new Section<>(VIEW_TYPE_IOS_BLOCK, iosShows));
        }

        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("休息视频", R.drawable.home_title_movie)));

        // 休息视频
        ArrayList<GanKInfo> restMovie = ganKDayInfo.restMovie;
        if (restMovie != null && restMovie.size() > 0) {
            cellMapping.add(new Section<>(VIEW_TYPE_REST_MOVIE, restMovie));
        }

//        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("瞎推荐", R.drawable.home_title_xia)));
//
//        // 休息视频
//        ArrayList<GanKInfo> recommend = ganKDayInfo.recommend;
//        if (recommend != null && recommend.size() > 0) {
//            cellMapping.add(new Section<>(VIEW_TYPE_RECOMMEND, recommend));
//        }

        cellMapping.add(new Section<>(VIEW_TYPE_BLOCK_TITLE, new GanKDayCategory("前端", R.drawable.home_title_qian)));

        // 前端
        ArrayList<GanKInfo> webShows = ganKDayInfo.front;
        if (webShows != null && webShows.size() > 0) {
            MLog.i("webShows.size() = " + webShows.size());

            webShows.add(new GanKInfo("美美哒", Utils.getThreeImageUrl()));
            webShows.add(new GanKInfo("你好妖娆啊", Utils.getThreeImageUrl()));
            webShows.add(new GanKInfo("呵呵额呵呵", Utils.getThreeImageUrl()));
            webShows.add(new GanKInfo("幻家老祖", Utils.getThreeImageUrl()));
            webShows.add(new GanKInfo("虚无的剑气，我飘啊飘~~", Utils.getThreeImageUrl()));
            cellMapping.add(new Section<>(VIEW_TYPE_FRONT_WEB, webShows));
        }









//        if ("Android".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_android));
//            index = 0;
//        } else if ("福利".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_meizi));
//            index = 1;
//        } else if ("IOS".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_ios));
//            index = 2;
//        } else if ("休息视频".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_movie));
//            index = 2;
//        } else if ("拓展资源".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_source));
//            index = 2;
//        } else if ("瞎推荐".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_xia));
//            index = 2;
//        } else if ("前端".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_qian));
//            index = 2;
//        } else if ("App".equals(title)) {
//            binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(R.drawable.home_title_app));
//            index = 2;
//        }


//        List<IModel> cellModels = new ArrayList<>();
//

//
//        // 添加标题
//        cellModels.add(new GanKDayCategory("Android相关资源"));
//
//        // Android
//        ArrayList<GanKInfo> shows = ganKDayInfo.Android;
//        if (shows != null) {
//            int size = shows.size();
//            for (int i = 0; i < size; i++) {
//                cellModels.add(new GanKCellModel(shows.get(i)));
//            }
//        }
//
//        // 添加标题
//        cellModels.add(new GanKDayCategory("iOS相关资源"));
//

//
//        // 添加标题
//        cellModels.add(new GanKDayCategory("Web相关资源"));
//

//
//        // 添加标题
//        cellModels.add(new GanKDayCategory("app相关资源"));
//
//        // app
//        ArrayList<GanKInfo> appShows = ganKDayInfo.App;
//        if (appShows != null) {
//            int size4 = appShows.size();
//            for (int i = 0; i < size4; i++) {
//                cellModels.add(new GanKCellModel(appShows.get(i)));
//            }
//        }




        return cellMapping;
    }


}
