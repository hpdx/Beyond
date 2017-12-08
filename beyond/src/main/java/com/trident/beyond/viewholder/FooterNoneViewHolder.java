package com.trident.beyond.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by android_ls on 2017/8/16.
 */

public class FooterNoneViewHolder extends RecyclerView.ViewHolder {

    public FooterNoneViewHolder(View itemView) {
        super(itemView);
    }

    public void setVisibility(boolean isVisible) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        itemView.setLayoutParams(layoutParams);
    }

}
