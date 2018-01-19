package com.gank.list.model;

import com.gank.common.model.GanKBlock;
import com.gank.common.model.GanKCellModel;
import com.gank.common.model.GanKInfo;
import com.gank.network.ApiUrls;
import com.gank.network.Apis;
import com.trident.beyond.model.BaseListRequest;
import com.trident.dating.libcommon.IRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GanKListRequest extends BaseListRequest<GanKBlock, GanKCellModel> {

    @Override
    public String getUrl() {
        return ApiUrls.GANK_URL + "/60/3";
    }

    @Override
    protected IRequest makeRequest(String url) {
        return Apis.getGank(url, this);
    }

    @Override
    protected List<GanKCellModel> getItemsFromResponse(GanKBlock response) {
        if (response == null || response.results == null || response.results.size() == 0) {
            return null;
        }

        List<GanKCellModel> cellModels = new ArrayList<>();
        ArrayList<GanKInfo> shows = response.results;
        int size = shows.size();
        for (int i = 0; i < size; i++) {
            cellModels.add(new GanKCellModel(shows.get(i)));
        }
        return cellModels;
    }

}
