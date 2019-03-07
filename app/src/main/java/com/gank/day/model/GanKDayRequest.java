package com.gank.day.model;

import com.anbetter.beyond.model.BaseListRequest;
import com.anbetter.beyond.model.IModel;
import com.anbetter.beyond.model.IRequest;
import com.gank.common.model.GanKCellModel;
import com.gank.common.model.GanKInfo;
import com.gank.ApiUrls;
import com.gank.Apis;

import java.util.ArrayList;
import java.util.List;

public class GanKDayRequest extends BaseListRequest<GanKDayBlock, IModel> {

    @Override
    public String getUrl() {
        return ApiUrls.GANK_DAY_URL;
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getGankDay(url, this);
    }

    @Override
    protected List<IModel> getItemsFromResponse(GanKDayBlock response) {
        if (response == null || response.results == null) {
            return null;
        }

        List<IModel> cellModels = new ArrayList<>();
        GanKDayInfo ganKDayInfo = response.results;

        // 福利
        if (ganKDayInfo.福利 != null && ganKDayInfo.福利.size() > 0) {
            GanKInfo ganKInfo = ganKDayInfo.福利.get(0);

            GanKDayBanner banner = new GanKDayBanner();
            banner.url = ganKInfo.url;
            cellModels.add(banner);
        }

        // 添加标题
        cellModels.add(new GanKDayCategory("Android相关资源"));

        // Android
        ArrayList<GanKInfo> shows = ganKDayInfo.Android;
        if (shows != null) {
            int size = shows.size();
            for (int i = 0; i < size; i++) {
                cellModels.add(new GanKCellModel(shows.get(i)));
            }
        }

        // 添加标题
        cellModels.add(new GanKDayCategory("iOS相关资源"));

        // iOS
        ArrayList<GanKInfo> iosShows = ganKDayInfo.iOS;
        if (iosShows != null) {
            int size2 = iosShows.size();
            for (int i = 0; i < size2; i++) {
                cellModels.add(new GanKCellModel(iosShows.get(i)));
            }
        }

        // 添加标题
        cellModels.add(new GanKDayCategory("Web相关资源"));

        // 前端
        ArrayList<GanKInfo> webShows = ganKDayInfo.前端;
        if (webShows != null) {
            int size3 = webShows.size();
            for (int i = 0; i < size3; i++) {
                cellModels.add(new GanKCellModel(webShows.get(i)));
            }
        }

        // 添加标题
        cellModels.add(new GanKDayCategory("app相关资源"));

        // app
        ArrayList<GanKInfo> appShows = ganKDayInfo.App;
        if (appShows != null) {
            int size4 = appShows.size();
            for (int i = 0; i < size4; i++) {
                cellModels.add(new GanKCellModel(appShows.get(i)));
            }
        }

        return cellModels;
    }


}