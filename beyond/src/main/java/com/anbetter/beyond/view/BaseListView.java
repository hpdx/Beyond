package com.anbetter.beyond.view;

import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.listener.OnRefreshCompleteListener;
import com.anbetter.beyond.model.BaseListRequest;

/**
 * Created by android_ls on 16/7/22.
 */
public interface BaseListView<M extends BaseListRequest<?, ?>> extends MvvmBaseView<M>, OnRefreshCompleteListener {

}
