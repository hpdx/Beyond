package com.gank.tab.multi.type.model;

import android.net.Uri;

import com.anbetter.log.MLog;
import com.gank.common.model.GanKBlock;
import com.gank.common.model.GanKCellModel;
import com.gank.common.model.GanKInfo;
import com.gank.day.model.GanKDayBanner;
import com.gank.network.ApiUrls;
import com.gank.network.Apis;
import com.trident.beyond.model.IModel;
import com.trident.beyond.model.BasePaginatedListRequest;
import com.trident.dating.libcommon.IRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2016/12/28.
 */

public class MTGanKPagingListRequest extends BasePaginatedListRequest<GanKBlock, IModel> {

    private int pageCount = 1;

    @Override
    public String getUrl() {
        Uri.Builder builder = Uri.parse(ApiUrls.GANK_URL).buildUpon();
        builder.appendPath(String.valueOf(20));
        builder.appendPath(String.valueOf(pageCount));

        MLog.i("getUrl() url =" + builder.toString());
        return builder.toString();
    }

    @Override
    protected String getNextPageUrl() {
        pageCount++;
        return getUrl();
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getGank(url, this);
    }

    @Override
    protected List<IModel> getItemsFromResponse(GanKBlock response) {
        if (response == null || response.results == null || response.results.size() == 0) {
            return null;
        }

        List<IModel> cellModels = new ArrayList<>();

        if(pageCount == 1) {
            // 添加Banner
            GanKDayBanner ganKDayBanner = new GanKDayBanner();
            ganKDayBanner.url = "http://ww3.sinaimg.cn/large/610dc034jw1fbd818kkwjj20u011hjup.jpg";
            cellModels.add(ganKDayBanner);
        }

        // 添加数据
        ArrayList<GanKInfo> shows = response.results;
        int size = shows.size();
        for (int i = 0; i < size; i++) {
            cellModels.add(new GanKCellModel(shows.get(i)));
        }
        return cellModels;
    }

}
