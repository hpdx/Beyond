package com.an.better.netease.cloud.music.api;

/**
 *
 * Created by android_ls on 2018/2/11.
 */

public interface ApiUrls {

    // gank
    String GANK_BASE_URL = "https://gank.io/api/";

    // 豆瓣
    String DOUBAN_BASE_URL = "https://api.douban.com/";

    // 轮播图
    String TING_BASE_URL = "https://tingapi.ting.baidu.com/v1/restserver/";

    // 首页轮播图
    String HOME_BANNER_URL = "ting?from=android&version=5.8.1.0&channel=ppzs&operator=3&method=baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14";

    // 每日数据：https://gank.io/api/day/年/月/日
    String GANK_DAY_URL = "day/2018/02/22";

    // https://gank.io/api/data/数据类型/请求个数/第几页
    // https://gank.io/api/data/福利/30/1
    String GANK_MEIZI_URL = "data/福利/";

    // 豆瓣热映电影，每日更新
    // https://api.douban.com/v2/movie/in_theaters
    String DOUBAN_HOT_MOVIE_URL = "v2/movie/in_theaters";

    // 获取电影详情
    // https://api.douban.com/v2/movie/subject/26861685
    String MOVIE_DETAIL_URL = "v2/movie/subject";

    // 获取豆瓣电影top250
    // https://api.douban.com/v2/movie/top250?start=0&count=20
    String MOVIE_TOP_250_URL = "v2/movie/top250";

}
