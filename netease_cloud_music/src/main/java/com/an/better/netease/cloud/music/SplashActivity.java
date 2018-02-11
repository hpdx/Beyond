package com.an.better.netease.cloud.music;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.an.better.netease.cloud.music.databinding.ActivitySplashBinding;
import com.an.better.netease.cloud.music.utils.PerfectClickListener;
import com.an.better.netease.cloud.music.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.Random;

/**
 * Created by android_ls on 2018/1/22.
 */

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mBinding;
    private Handler mHandler = new Handler();
    private boolean isIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        int i = new Random().nextInt(Utils.TRANSITION_URLS.length);
        // 先显示默认图
        mBinding.ivDefaultPic.setImageDrawable(getResources().getDrawable(R.drawable.img_transition_default));
        Glide.with(this)
                .load(Utils.TRANSITION_URLS[i])
                .into(mBinding.ivPic);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.ivDefaultPic.setVisibility(View.GONE);
            }
        }, 1500);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 3500);

        mBinding.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMainActivity();
            }
        });
    }

    private void toMainActivity() {
        if (isIn) {
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

}
