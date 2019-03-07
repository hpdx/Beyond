package com.gank.common.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anbetter.beyond.helper.ViewHolderProvider;
import com.anbetter.beyond.viewholder.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.gank.R;
import com.gank.common.model.GanKCellModel;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GanKViewHolderProvider extends ViewHolderProvider<GanKCellModel, GanKViewHolderProvider.GanKViewHolder> {

    @Override
    public GanKViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new GanKViewHolder(inflater, parent);
    }

    static class GanKViewHolder extends BaseViewHolder<GanKCellModel> {

        private SimpleDraweeView sdvCover;
        private TextView tvDesc;
        private TextView tvPublishedAt;

        public GanKViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            super(inflater.inflate(R.layout.gank_item, parent, false));
            sdvCover = findViewById(R.id.sdv_cover);
            tvDesc = findViewById(R.id.tv_desc);
            tvPublishedAt = findViewById(R.id.tv_published_at);
        }

        @Override
        public void bind(GanKCellModel cellModel, int position) {
            super.bind(cellModel, position);

            if(cellModel.getCoverUrl() != null) {
                Phoenix.with(sdvCover)
                        .load(cellModel.getCoverUrl());
            }

            tvDesc.setText(cellModel.getData().desc);
            tvPublishedAt.setText(cellModel.getData().publishedAt);
        }
    }

}
