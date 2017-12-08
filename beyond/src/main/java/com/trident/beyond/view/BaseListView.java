package com.trident.beyond.view;

import com.trident.beyond.core.MvvmBaseView;
import com.trident.beyond.listener.OnRefreshCompleteListener;
import com.trident.beyond.model.BaseListRequest;

/**
 * Created by android_ls on 16/7/22.
 */
public interface BaseListView<M extends BaseListRequest<?, ?>> extends MvvmBaseView<M>, OnRefreshCompleteListener {

}
