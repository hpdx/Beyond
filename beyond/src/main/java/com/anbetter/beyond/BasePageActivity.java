package com.anbetter.beyond;

import com.anbetter.beyond.model.BaseModel;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.view.BaseView;
import com.anbetter.beyond.viewmodel.BaseViewModel;

/**
 * Created by android_ls on 16/2/17.
 */
public abstract class BasePageActivity extends MvvmPageActivity<BaseModel, BaseView, BaseViewModel>
        implements MvvmBaseView<BaseModel> {

    @Override
    protected BaseViewModel createViewModel() {
        return new BaseViewModel();
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(BaseModel data) {

    }

}
