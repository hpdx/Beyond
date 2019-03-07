package com.gank.day.model;

import com.anbetter.beyond.model.CellModel;

/**
 * 对GankInfo数据进行加工处理，服务端返回的数据，就是你想要的，不需要做加工处理，那么这个类可以干掉
 */

public class GanKDayCellModel extends CellModel<GanKDayInfo> {

    public GanKDayCellModel(GanKDayInfo data) {
        super(data);
    }

}