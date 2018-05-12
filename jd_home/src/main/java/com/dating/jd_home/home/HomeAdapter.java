package com.dating.jd_home.home;

import android.view.View;
import android.widget.Toast;

import com.anbetter.base.adapter.BaseMultiItemQuickAdapter;
import com.anbetter.base.adapter.BaseViewHolder;
import com.dating.jd_home.R;
import com.dating.jd_home.home.model.ItemContentListBean;
import com.dating.jd_home.home.model.ItemInfoListBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by android_ls on 2018/3/21.
 */

public class HomeAdapter extends BaseMultiItemQuickAdapter<ItemInfoListBean, BaseViewHolder> {

    public HomeAdapter(List<ItemInfoListBean> data) {
        super(data);

        addItemType(ItemInfoListBean.TYPE_TOP_BANNER, R.layout.homerecycle_item_top_banner);
//        addItemType(ItemInfoListBean.TYPE_ICON_LIST, R.layout.homerecycle_item_icon_list);

    }

    @Override
    protected void convert(BaseViewHolder helper, ItemInfoListBean item) {
        switch (helper.getItemViewType()) {
            case ItemInfoListBean.TYPE_TOP_BANNER:
                bindTopBannerData(helper, item);
                break;
//            case ItemInfoListBean.TYPE_ICON_LIST:
//
//                break;
        }
    }



    private void bindTopBannerData(BaseViewHolder helper, final ItemInfoListBean item) {
        BGABanner banner = helper.getView(R.id.banner);
        banner.setDelegate(new BGABanner.Delegate<View, ItemContentListBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, ItemContentListBean model, int position) {
                Toast.makeText(itemView.getContext(),
                        item.itemContentList.get(position).clickUrl,
                        Toast.LENGTH_SHORT).show();
            }
        });
        banner.setAdapter(new BGABanner.Adapter<View, ItemContentListBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, ItemContentListBean model, int position) {
                SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.sdv_item_fresco_content);
                Phoenix.with(simpleDraweeView).load(model.imageUrl);
            }
        });
        banner.setData(R.layout.homerecycle_top_banner_content, item.itemContentList, null);
    }






}
