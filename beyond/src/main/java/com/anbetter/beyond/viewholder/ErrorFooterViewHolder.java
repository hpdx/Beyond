package com.anbetter.beyond.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anbetter.beyond.R;
import com.anbetter.beyond.model.IModel;

/**
 * Created by android_ls on 2017/2/9.
 */

public class ErrorFooterViewHolder extends BaseViewHolder<IModel> {
    public TextView retryButton;
    public TextView errorMsg;

    private LinearLayout llLoading;
    private LinearLayout llNetworkError;

    public ErrorFooterViewHolder(LayoutInflater inflater, ViewGroup parent, final View.OnClickListener listener) {
        super(inflater.inflate(R.layout.footer_error, parent, false));

        llNetworkError = findViewById(R.id.ll_network_error);
        llLoading = findViewById(R.id.ll_loading);
        retryButton = findViewById(R.id.retry_button);
        errorMsg = findViewById(R.id.error_msg);

        retryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                llLoading.setVisibility(View.VISIBLE);
                llNetworkError.setVisibility(View.GONE);

                itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onClick(v);
                        }
                    }
                }, 300);
            }
        });
    }

    @Override
    public void bind(IModel cellModel, int position) {
        super.bind(cellModel, position);
        llLoading.setVisibility(View.GONE);
        llNetworkError.setVisibility(View.VISIBLE);
    }

}
