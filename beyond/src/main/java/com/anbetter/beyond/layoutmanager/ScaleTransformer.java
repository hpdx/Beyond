package com.anbetter.beyond.layoutmanager;

import android.view.View;

public class ScaleTransformer implements ItemTransformer {

    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        if(Float.isNaN(fraction)) {
            return;
        }

        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        float scale = 1 - 0.3f * Math.abs(fraction);
        item.setScaleX(scale);
        item.setScaleY(scale);
    }

}
