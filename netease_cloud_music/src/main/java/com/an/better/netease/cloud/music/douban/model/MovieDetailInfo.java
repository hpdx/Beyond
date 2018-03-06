package com.an.better.netease.cloud.music.douban.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.an.better.netease.cloud.music.utils.Utils;

import java.util.List;

/**
 * Created by android_ls on 2018/3/6.
 */

public class MovieDetailInfo implements Parcelable {

    /**
     * rating : {"max":10,"average":8.5,"stars":"45","min":0}
     * reviews_count : 4648
     * wish_count : 37579
     * douban_site :
     * year : 2018
     * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg"}
     * alt : https://movie.douban.com/subject/26861685/
     * id : 26861685
     * mobile_url : https://movie.douban.com/subject/26861685/mobile
     * title : 红海行动
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/26861685
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26861685/cinema/
     * episodes_count : null
     * countries : ["中国大陆","香港"]
     * genres : ["剧情","动作","犯罪"]
     * collect_count : 302814
     * casts : [{"alt":"https://movie.douban.com/celebrity/1274761/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg"},"name":"张译","id":"1274761"},{"alt":"https://movie.douban.com/celebrity/1354442/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1458138265.51.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1458138265.51.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1458138265.51.jpg"},"name":"黄景瑜","id":"1354442"},{"alt":"https://movie.douban.com/celebrity/1272245/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49399.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49399.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49399.jpg"},"name":"海清","id":"1272245"},{"alt":"https://movie.douban.com/celebrity/1322949/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1502100680.45.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1502100680.45.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1502100680.45.jpg"},"name":"杜江","id":"1322949"}]
     * current_season : null
     * original_title : 红海行动
     * summary : 索马里海域外，中国商船遭遇劫持，船员全数沦为阶下囚。蛟龙突击队沉着应对，潜入商船进行突袭，成功解救全部人质。
     返航途中，非洲北部伊维亚共和国发生政变，恐怖组织连同叛军攻入首都， 当地华侨面临危险，海军战舰接到上级命令改变航向，前往执行撤侨任务。蛟龙突击队八人，整装待发。
     时间紧迫，在“撤侨遇袭可反击，相反则必须避免交火，以免引起外交冲突”的大原则下，海军战舰及蛟龙突击队深入伊维亚，在恶劣的环境之下，借助海陆等多种装备，成功转移等候在码头的中国侨民，并在激烈的遭遇战之后，营救了被恐怖分子追击的中国领事馆巴士。
     然而事情尚未完结，就在掩护华侨撤离之际，蛟龙突击队收到中国人质被恐怖分子劫持的消息。众人深感责任重大，义无反顾地再度展开营救行动。前方路途险恶，蛟龙突击队即将遭遇的，远不止人质营救那么简单,恐怖分子的惊天阴谋即将浮出水面…..
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1275075/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372934445.18.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372934445.18.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1372934445.18.jpg"},"name":"林超贤","id":"1275075"}]
     * comments_count : 139088
     * ratings_count : 292364
     * aka : ["刀锋·红海行动","Operation Red Sea"]
     */

    public RatingBean rating;
    public int reviews_count;
    public int wish_count;
    public String douban_site;
    public String year;
    public ImagesBean images;
    public String alt;
    public String id;
    public String mobile_url;
    public String title;
    public String share_url;
    public String schedule_url;
    public int collect_count;
    public String original_title;
    public String summary;
    public String subtype;
    public int comments_count;
    public int ratings_count;
    public List<String> countries;
    public List<String> genres;
    public List<CastsBean> casts;
    public List<CastsBean> directors;
    public List<String> aka;

    /**
     * 又名
     * @return
     */
    public String getAka() {
        return Utils.formatGenres(aka);
    }


    public MovieDetailInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeInt(this.reviews_count);
        dest.writeInt(this.wish_count);
        dest.writeString(this.douban_site);
        dest.writeString(this.year);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeString(this.mobile_url);
        dest.writeString(this.title);
        dest.writeString(this.share_url);
        dest.writeString(this.schedule_url);
        dest.writeInt(this.collect_count);
        dest.writeString(this.original_title);
        dest.writeString(this.summary);
        dest.writeString(this.subtype);
        dest.writeInt(this.comments_count);
        dest.writeInt(this.ratings_count);
        dest.writeStringList(this.countries);
        dest.writeStringList(this.genres);
        dest.writeTypedList(this.casts);
        dest.writeTypedList(this.directors);
        dest.writeStringList(this.aka);
    }

    protected MovieDetailInfo(Parcel in) {
        this.rating = in.readParcelable(RatingBean.class.getClassLoader());
        this.reviews_count = in.readInt();
        this.wish_count = in.readInt();
        this.douban_site = in.readString();
        this.year = in.readString();
        this.images = in.readParcelable(ImagesBean.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.mobile_url = in.readString();
        this.title = in.readString();
        this.share_url = in.readString();
        this.schedule_url = in.readString();
        this.collect_count = in.readInt();
        this.original_title = in.readString();
        this.summary = in.readString();
        this.subtype = in.readString();
        this.comments_count = in.readInt();
        this.ratings_count = in.readInt();
        this.countries = in.createStringArrayList();
        this.genres = in.createStringArrayList();
        this.casts = in.createTypedArrayList(CastsBean.CREATOR);
        this.directors = in.createTypedArrayList(CastsBean.CREATOR);
        this.aka = in.createStringArrayList();
    }

    public static final Creator<MovieDetailInfo> CREATOR = new Creator<MovieDetailInfo>() {
        @Override
        public MovieDetailInfo createFromParcel(Parcel source) {
            return new MovieDetailInfo(source);
        }

        @Override
        public MovieDetailInfo[] newArray(int size) {
            return new MovieDetailInfo[size];
        }
    };
}
