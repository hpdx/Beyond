package com.anbetter.beyond.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;


public class BackToTopView extends ImageView {

    private int visiblePosition;

    private RecyclerView recyclerView;

    public BackToTopView(Context context) {
        this(context, null);
    }

    public BackToTopView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackToTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(null);
    }

    public void setVisiblePosition(int visiblePosition) {
        this.visiblePosition = visiblePosition;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(new BackToTopScrollListener(this));
    }

    @Override
    public void setOnClickListener(final OnClickListener listener) {
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView != null) {
                    recyclerView.scrollToPosition(0);
                    setVisibility(View.GONE);
                }

                if (listener != null) {
                    listener.onClick(BackToTopView.this);
                }
            }
        });
    }

    static class BackToTopScrollListener extends RecyclerView.OnScrollListener {

        private final WeakReference<BackToTopView> backToTopViewRef;

        public BackToTopScrollListener(BackToTopView backToTopView) {
            backToTopViewRef = new WeakReference<>(backToTopView);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                final BackToTopView backToTopView = backToTopViewRef.get();
                if (backToTopView != null) {
                    if (lastVisibleItem > backToTopView.visiblePosition) {
                        backToTopView.setVisibility(View.VISIBLE);
                    } else {
                        backToTopView.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

}
