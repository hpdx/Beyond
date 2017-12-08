package com.trident.beyond.model;

import android.os.Bundle;

import com.trident.beyond.host.PageTabHost;

/**
 * 每个Tab的数据结构
 *
 * Created by android_ls on 16/7/25.
 */
public class TabData<M> {

    /**标题*/
    public String title;

    public String postData;

    /**向服务器端请求当前Tab Page的数据时，可能需要的参数*/
    public int type;

    /**当前选中的TAB*/
    public int current;

    public Bundle data;

    /**数据源*/
    public M dataSource;

    /**
     * 保存当前Page的状态
     * 1、在离开当前Page时存储页面状态，比如RecyclerView的滚动位置
     * 2、在再次回退回来时恢复该页面的状态，比如RecyclerView恢复到离开时滚动到的位置
     * */
    public Bundle instanceState;

    public PageTabHost mHost;

}
