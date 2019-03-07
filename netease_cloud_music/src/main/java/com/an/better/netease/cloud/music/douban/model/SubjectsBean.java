package com.an.better.netease.cloud.music.douban.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.an.better.netease.cloud.music.utils.Utils;
import com.anbetter.beyond.model.IModel;

import java.util.List;

/**
 * Created by android_ls on 2018/3/2.
 */

public class SubjectsBean implements IModel, Parcelable {

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

    /**
     * 电影类型
     * @return
     */
    public String getGenres() {
        return Utils.formatGenres(genres);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.title);
        dest.writeInt(this.collect_count);
        dest.writeString(this.original_title);
        dest.writeString(this.subtype);
        dest.writeString(this.year);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeStringList(this.genres);
        dest.writeTypedList(this.casts);
        dest.writeTypedList(this.directors);
    }

    public SubjectsBean() {
    }

    protected SubjectsBean(Parcel in) {
        this.rating = in.readParcelable(RatingBean.class.getClassLoader());
        this.title = in.readString();
        this.collect_count = in.readInt();
        this.original_title = in.readString();
        this.subtype = in.readString();
        this.year = in.readString();
        this.images = in.readParcelable(ImagesBean.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.genres = in.createStringArrayList();
        this.casts = in.createTypedArrayList(CastsBean.CREATOR);
        this.directors = in.createTypedArrayList(CastsBean.CREATOR);
    }

    public static final Parcelable.Creator<SubjectsBean> CREATOR = new Parcelable.Creator<SubjectsBean>() {
        @Override
        public SubjectsBean createFromParcel(Parcel source) {
            return new SubjectsBean(source);
        }

        @Override
        public SubjectsBean[] newArray(int size) {
            return new SubjectsBean[size];
        }
    };

}
