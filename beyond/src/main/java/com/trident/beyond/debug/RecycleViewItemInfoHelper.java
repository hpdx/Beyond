package com.trident.beyond.debug;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.anbetter.log.MLog;

/**
 *
 * Created by android_ls on 16/8/24.
 */
public class RecycleViewItemInfoHelper {

    public static void printItemInfo(RecyclerView recyclerView) {
//        if (!BuildConfig.DEBUG)
//            return;
        final GestureDetectorCompat detectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchListener(recyclerView));
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                detectorCompat.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                detectorCompat.onTouchEvent(e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private static class ItemTouchListener extends GestureDetector.SimpleOnGestureListener {
        RecyclerView recyclerView;

        public ItemTouchListener(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
                if (holder != null) {
                    MLog.w(holder.getClass().getSimpleName() + "-->" + holder.toString());
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
                if (holder != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(recyclerView.getContext());
                    builder.setMessage(holder.toString());
                    builder.show();
                }
            }
        }
    }

}
