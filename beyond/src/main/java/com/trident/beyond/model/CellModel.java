package com.trident.beyond.model;

import com.trident.beyond.core.IModel;

/**
 * Created by android_ls on 16/8/23.
 */
public class CellModel<M> implements IModel {

    protected M data;

    public CellModel(M data) {
        this.data = data;
    }

    public M getData() {
        return data;
    }

    public void setData(M data) {
        this.data = data;
    }

}
