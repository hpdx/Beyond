package com.anbetter.beyond.tab;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.mvvm.StatusLayout;

public abstract class BaseVdbMvvmTab<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>,
        VDB extends ViewDataBinding> extends MvvmTab<M, V , VM> {

    protected VDB binding;

    public BaseVdbMvvmTab(Context context) {
        super(context);
    }

    public void onViewCreated() {
        binding = DataBindingUtil.inflate(mLayoutInflater, getLayoutRes(), null, false);
        mStatusLayout = new StatusLayout();
        mStatusLayout.onCreateView(mLayoutInflater, null, binding.getRoot());
        mStatusLayout.onViewCreated(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClicked();
            }
        });
        viewModel.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding.unbind();
            binding = null;
        }
    }

}
