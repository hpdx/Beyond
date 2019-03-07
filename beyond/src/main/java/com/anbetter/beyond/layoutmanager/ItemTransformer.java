package com.anbetter.beyond.layoutmanager;

import android.view.View;

/**
 * <p>
 * Created by android_ls on 2018/12/24.
 *
 * @author 李松
 * @version 1.0
 */
public interface ItemTransformer {
    void transformItem(GalleryLayoutManager layoutManager, View item, float fraction);
}
