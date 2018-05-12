package com.dating.jd_home.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.anbetter.base.adapter.entity.IMultiItemType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 2018/3/21.
 */

public class ItemInfoListBean implements IMultiItemType, Parcelable {

    public static final int TYPE_TOP_BANNER = 0xff01;
    public static final int TYPE_ICON_LIST = 0xff02;
    public static final int TYPE_NEW_USER = 0xff03;
    public static final int TYPE_JD_BULLETIN = 0xff04;
    public static final int TYPE_JD_SPIKE_HEADER = 0xff05;
    public static final int TYPE_JD_SPIKE_CONTENT = 0xff06;
    public static final int TYPE_SHOW_EVENT_3 = 0xff07;
    public static final int TYPE_FIND_GOOD_STUFF = 0xff08;
    public static final int TYPE_WIDTH_PROPORTION_211 = 0xff09;
    public static final int TYPE_TITLE = 0xff10;
    public static final int TYPE_WIDTH_PROPORTION_22 = 0xff11;
    public static final int TYPE_WIDTH_PROPORTION_1111 = 0xff12;
    public static final int TYPE_MIDDLE_BANNER = 0xff13;
    public static final int TYPE_SHOW_EVENT_FILL_UP = 0xff14;
    public static final int TYPE_FIND_GOOD_SHOP = 0xff15;
    public static final int TYPE_PREFERRED_LIST = 0xff16;
    public static final int TYPE_LIVE = 0xff17;
    public static final int TYPE_RECOMMENDED_WARE = 0xff18;
    public static final Parcelable.Creator<ItemInfoListBean> CREATOR = new Parcelable.Creator<ItemInfoListBean>() {
        @Override
        public ItemInfoListBean createFromParcel(Parcel source) {
            return new ItemInfoListBean(source);
        }

        @Override
        public ItemInfoListBean[] newArray(int size) {
            return new ItemInfoListBean[size];
        }
    };
    /**
     * itemType : topBanner
     * module : topBanner
     * itemContentList : [{"imageUrl":"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4903/41/12296166/85214/15205dd6/58d92373N127109d8.jpg!q70.jpg","clickUrl":"男装超级品牌类日"},{"imageUrl":"https://img1.360buyimg.com/da/jfs/t4309/113/2596274814/85129/a59c5f5e/58d4762cN72d7dd75.jpg","clickUrl":"京东春茶季"},{"imageUrl":"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4675/88/704144946/137165/bbbe8a4e/58d3a160N621fc59c.jpg!q70.jpg","clickUrl":"装出新高度"},{"imageUrl":"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4627/177/812580410/198036/24a79c26/58d4f1e9N5b9fc5ee.jpg!q70.jpg","clickUrl":"宝宝出行利器"},{"imageUrl":"https://m.360buyimg.com/mobilecms/s720x322_jfs/t3097/241/9768114398/78418/47e4335e/58d8a637N6f178fbd.jpg!q70.jpg","clickUrl":"慕尼黑新品上市"},{"imageUrl":"https://m.360buyimg.com/mobilecms/s720x322_jfs/t4282/364/2687292678/87315/e4311cd0/58d4d923N24a2f5eb.jpg!q70.jpg","clickUrl":"空调让你百试不爽"},{"imageUrl":"https://img1.360buyimg.com/da/jfs/t4162/171/1874609280/92523/a1206b3f/58c7a832Nc8582e81.jpg","clickUrl":"奥妙全新上市"},{"imageUrl":"https://img1.360buyimg.com/da/jfs/t4387/338/1185667042/56822/bcd7fc3d/58d9e139Nadf09c53.jpg","clickUrl":"吸尘品类直降"}]
     */

    public String itemType;
    public String module;
    public List<ItemContentListBean> itemContentList;

    public ItemInfoListBean() {
    }

    protected ItemInfoListBean(Parcel in) {
        this.itemType = in.readString();
        this.module = in.readString();
        this.itemContentList = new ArrayList<ItemContentListBean>();
        in.readList(this.itemContentList, ItemContentListBean.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemType);
        dest.writeString(this.module);
        dest.writeList(this.itemContentList);
    }

    @Override
    public int getItemType() {
//        if ("iconList".equals(itemType)) {
//            return TYPE_ICON_LIST;
//        }

        return TYPE_TOP_BANNER;

//        if ("topBanner".equals(itemType)) {
//            return TYPE_TOP_BANNER;
//        } else if ("iconList".equals(itemType)) {
//            return TYPE_ICON_LIST;
//        } else if ("newUser".equals(itemType)) {
//            return TYPE_NEW_USER;
//        } else if ("jdBulletin".equals(itemType)) {
//            return TYPE_JD_BULLETIN;
//        } else if ("jdSpikeHeader".equals(itemType)) {
//            return TYPE_JD_SPIKE_HEADER;
//        } else if ("jdSpikeContent".equals(itemType)) {
//            return TYPE_JD_SPIKE_CONTENT;
//        } else if ("showEvent".equals(itemType)) {
//            return TYPE_SHOW_EVENT_3;
//        } else if ("findGoodStuff".equals(itemType)) {
//            return TYPE_FIND_GOOD_STUFF;
//        } else if ("type_211".equals(itemType)) {
//            return TYPE_WIDTH_PROPORTION_211;
//        } else if ("type_Title".equals(itemType)) {
//            return TYPE_TITLE;
//        } else if ("type_22".equals(itemType)) {
//            return TYPE_WIDTH_PROPORTION_22;
//        } else if ("type_1111".equals(itemType)) {
//            return TYPE_WIDTH_PROPORTION_1111;
//        } else if ("type_middleBanner".equals(itemType)) {
//            return TYPE_MIDDLE_BANNER;
//        } else if ("showEventFillUp".equals(itemType)) {
//            return TYPE_SHOW_EVENT_FILL_UP;
//        } else if ("findGoodShop".equals(itemType)) {
//            return TYPE_FIND_GOOD_SHOP;
//        } else if ("preferredList".equals(itemType)) {
//            return TYPE_PREFERRED_LIST;
//        } else if ("live".equals(itemType)) {
//            return TYPE_LIVE;
//        } else if ("recommended_ware".equals(itemType)) {
//            return TYPE_RECOMMENDED_WARE;
//        }
    }

}
