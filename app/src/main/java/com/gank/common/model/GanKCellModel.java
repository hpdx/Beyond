package com.gank.common.model;

import com.trident.beyond.model.CellModel;

/**
 * 对GankInfo数据进行加工处理，服务端返回的数据，就是你想要的，不需要做加工处理，那么这个类可以干掉
 *
 * Created by android_ls on 2016/12/28.
 */

public class GanKCellModel extends CellModel<GanKInfo> {

    public GanKCellModel(GanKInfo data) {
        super(data);
    }

    public String getCoverUrl() {
        if(data.images != null && data.images.size() > 0) {
            return data.images.get(0);
        }
        return null;
    }

}
