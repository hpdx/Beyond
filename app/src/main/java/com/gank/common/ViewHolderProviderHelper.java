package com.gank.common;

import com.gank.common.model.GanKCellModel;
import com.gank.common.viewholder.DBGanKViewHolderProvider;
import com.gank.day.model.GanKDayBanner;
import com.gank.day.model.GanKDayCategory;
import com.gank.day.viewholder.BannerViewHolderProvider;
import com.gank.day.viewholder.CategoryViewHolderProvider;
import com.trident.beyond.list.helper.ViewHolderProviderPool;

/**
 * Created by android_ls on 2016/12/28.
 */

public class ViewHolderProviderHelper {

    public static void register() {
//        ViewHolderProviderPool.register(GanKCellModel.class, new GanKViewHolderProvider());
        ViewHolderProviderPool.register(GanKCellModel.class, new DBGanKViewHolderProvider());
        ViewHolderProviderPool.register(GanKDayBanner.class, new BannerViewHolderProvider());
        ViewHolderProviderPool.register(GanKDayCategory.class, new CategoryViewHolderProvider());

    }

}
