package com.trident.beyond.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.trident.beyond.R;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Banner Tips Message
 * <p>
 * Created by android_ls on 2017/3/29.
 */

public class BannerTipsView extends FrameLayout implements Runnable {

    private static final long BANNER_TIPS_DURATION = 2000;

    private Handler mHandler;

    private TextView tvBannerTipsMessageHint;

    /**
     * Banner Tips Message Queue
     */
    private volatile LinkedBlockingQueue<String> mBannerTipsMessageQueue = new LinkedBlockingQueue<>();

    /**
     * 显示Banner Tips Message
     */
    private boolean showBannerTipsMessage = true;

    /**
     * Banner Tips 显示持续的时长，默认2秒
     */
    private long duration = BANNER_TIPS_DURATION;

    private volatile int mQueueCount = 0;

    /**
     * 上一次显示的消息内容
     */
    private String lastMessage;

    /**
     * 上一次显示消息时的时间
     */
    private long lastTime;

    public BannerTipsView(@NonNull Context context) {
        super(context);
        setupViews(context);
    }

    public BannerTipsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupViews(context);
    }

    public void addBannerTips(final String message) {
        try {
            if(!TextUtils.isEmpty(message)) {
                if(lastMessage != null && TextUtils.equals(lastMessage, message)
                        && lastTime > 0 && System.currentTimeMillis() - lastTime < BANNER_TIPS_DURATION) {
                    // 2秒内，消息内容相同的，只显示第一个，后面的自动过滤掉
                    return;
                }

                lastMessage = message;
                lastTime = System.currentTimeMillis();

                mQueueCount++;
                mBannerTipsMessageQueue.offer(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setShowBannerTipsMessage(boolean showBannerTipsMessage) {
        this.showBannerTipsMessage = showBannerTipsMessage;
    }

    public void destroy() {
        mQueueCount = 0;
        showBannerTipsMessage = false;
        mBannerTipsMessageQueue.clear();
    }

    private void setupViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.include_banner_tips_view, this, true);
        tvBannerTipsMessageHint = (TextView) findViewById(R.id.tv_message_hint);
        setVisibility(View.GONE);

        mQueueCount = 0;
        mHandler = new Handler(Looper.getMainLooper());
        Executors.newSingleThreadExecutor().submit(this);
    }

    @Override
    public void run() {
        while (showBannerTipsMessage) {
            try {
                final String message = mBannerTipsMessageQueue.take();
                if (mQueueCount > 1) {
                    Thread.sleep(BANNER_TIPS_DURATION + 800);
                }

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showBannerTips(message);
                    }
                }, 200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showBannerTips(String message) {
        setVisibility(View.VISIBLE);
        tvBannerTipsMessageHint.setText(message);

        if (tvBannerTipsMessageHint.getVisibility() == View.GONE) {
            tvBannerTipsMessageHint.setVisibility(View.VISIBLE);
            showMessageAnimation();
        }
    }

    private void showMessageAnimation() {
        Animation animationIn = AnimationUtils.loadAnimation(tvBannerTipsMessageHint.getContext(), R.anim.banner_tips_message_in);
        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animation animationOut = AnimationUtils.loadAnimation(tvBannerTipsMessageHint.getContext(), R.anim.banner_tips_message_out);
                        animationOut.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                mQueueCount--;
                                tvBannerTipsMessageHint.setVisibility(View.GONE);
                                BannerTipsView.this.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        tvBannerTipsMessageHint.startAnimation(animationOut);
                    }
                }, duration);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvBannerTipsMessageHint.startAnimation(animationIn);
    }

}
