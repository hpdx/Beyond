package com.anbetter.beyond.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.anbetter.beyond.R;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

/**
 * <p>
 * Created by android_ls on 2019/2/20.
 *
 * @author 李松
 * @version 1.0
 */
public class ProgressDialog extends Dialog {

    private RingProgressBar progressBar;

    public ProgressDialog(Context context) {
        super(context, R.style.confirm_dialog);

        LayoutInflater inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.bolo_progress_dialog, null);
        setContentView(contentView);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }

    public void setProgress(long current, long total) {
        int progress = (int) ((float) current / (float) total * 100);
        progressBar.setProgress(progress);
    }

}
