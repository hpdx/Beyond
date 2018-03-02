package com.an.better.netease.cloud.music.douban.model;

import com.an.better.netease.cloud.music.utils.Utils;
import com.trident.beyond.model.IModel;

import java.util.List;

/**
 * Created by android_ls on 2018/3/2.
 */

public class SubjectsBean implements IModel {

    /**
     * rating : {"max":10,"average":8.5,"stars":"45","min":0}
     * genres : ["剧情","动作"]
     * title : 红海行动
     * casts : [{"alt":"https://movie.douban.com/celebrity/1274761/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg"},"name":"张译","id":"1274761"},{"alt":"https://movie.douban.com/celebrity/1354442/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1458138265.51.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1458138265.51.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1458138265.51.jpg"},"name":"黄景瑜","id":"1354442"},{"alt":"https://movie.douban.com/celebrity/1272245/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49399.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49399.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49399.jpg"},"name":"海清","id":"1272245"}]
     * collect_count : 182869
     * original_title : 红海行动
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1275075/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372934445.18.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372934445.18.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372934445.18.jpg"},"name":"林超贤","id":"1275075"}]
     * year : 2018
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg"}
     * alt : https://movie.douban.com/subject/26861685/
     * id : 26861685
     */

    public RatingBean rating;
    public String title;
    public int collect_count;
    public String original_title;
    public String subtype;
    public String year;
    public ImagesBean images;
    public String alt;
    public String id;
    public List<String> genres;
    public List<CastsBean> casts;
    public List<CastsBean> directors;

    /**
     * 获取主演姓名
     * @return
     */
    public String getCastsNames() {
        return Utils.formatName(casts);
    }

    /**
     * 获取导演姓名
     * @return
     */
    public String getDirectors() {
        return Utils.formatName(directors);
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 8.5
         * stars : 45
         * min : 0
         */

        public int max;
        public double average;
        public String stars;
        public int min;
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg
         * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg
         * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg
         */

        public String small;
        public String large;
        public String medium;
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1274761/
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg"}
         * name : 张译
         * id : 1274761
         */

        public String alt;
        public AvatarsBean avatars;
        public String name;
        public String id;

        public static class AvatarsBean {
            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg
             */

            public String small;
            public String large;
            public String medium;
        }
    }

}
