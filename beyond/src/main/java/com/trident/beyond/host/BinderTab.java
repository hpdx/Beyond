package com.trident.beyond.host;

import android.os.Bundle;
import android.view.View;

/**
 * Tab对外开放的接口
 *
 * Created by android_ls on 16/7/25.
 */
public interface BinderTab {

    View getView();

    void onViewCreated();

    void onActivityCreated();

    void onDestroyView();

    void onDestroy();

    Bundle onSaveInstanceState();

    void onRestoreInstanceState(Bundle savedInstanceState);

}
