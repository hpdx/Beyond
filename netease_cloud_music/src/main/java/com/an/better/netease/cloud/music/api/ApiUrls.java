package com.an.better.netease.cloud.music.api;

/**
 *
 * Created by android_ls on 2018/2/11.
 */

public interface ApiUrls {

    // gank
    String GANK_BASE_URL = "https://gank.io/api/";

    // 豆瓣
    String DOUBAN_BASE_URL = "Https://api.douban.com/";

    // 轮播图
    String TING_BASE_URL = "https://tingapi.ting.baidu.com/v1/restserver/";

    // 首页轮播图
    String HOME_BANNER_URL = "ting?from=android&version=5.8.1.0&channel=ppzs&operator=3&method=baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14";

    // 每日数据：https://gank.io/api/day/年/月/日
    String GANK_DAY_URL = "day/2018/02/22";

    // https://gank.io/api/data/数据类型/请求个数/第几页
    // https://gank.io/api/data/福利/30/1
    String GANK_MEIZI_URL = "data/福利/";

}
