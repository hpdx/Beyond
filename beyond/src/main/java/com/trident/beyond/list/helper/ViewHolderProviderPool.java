package com.trident.beyond.list.helper;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.trident.beyond.core.IModel;

import java.util.ArrayList;

/**
 * Created by android_ls on 16/8/23.
 * 这里虽然是静态的list持有对象 但是并不会造成内存泄露
 */
public final class ViewHolderProviderPool {

    private static ArrayList<Class<? extends IModel>> mCellModels = new ArrayList<>();
    private static ArrayList<ViewHolderProvider> mCellProviders = new ArrayList<>();

    public synchronized static void register(@NonNull Class<? extends IModel> cellModel,
                                             @NonNull ViewHolderProvider cellViewModel) {
        if (!mCellModels.contains(cellModel)) {
            mCellModels.add(cellModel);
            mCellProviders.add(cellViewModel);
        }
    }

    public static int getItemViewType(Class<? extends IModel> modelClass) {
        int index = mCellModels.indexOf(modelClass);
        if(index < 0) {
            throw new ViewHolderNotFoundException(modelClass.toString());
        }
        return index;
    }

    @NonNull
    public static ViewHolderProvider getCellProvider(int viewType) {
        try {
            ViewHolderProvider viewHolderProvider = mCellProviders.get(viewType);
            if(viewHolderProvider == null) {
                throw new ViewHolderNotFoundException("ViewHolderProvider");
            }
            return viewHolderProvider;
        } catch (ViewHolderNotFoundException e) {
            throw new ViewHolderNotFoundException("ViewHolderProvider");
        }
    }

    public static class ViewHolderNotFoundException extends RuntimeException {
        ViewHolderNotFoundException(String key) {
            super("你可能没有注册（"+ (TextUtils.isEmpty(key)?"key == null":key) +"）到ViewHolderProviderPool中");
        }
    }

}
