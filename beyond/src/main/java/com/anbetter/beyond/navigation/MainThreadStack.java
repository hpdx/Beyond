package com.anbetter.beyond.navigation;

import android.os.Looper;

import java.util.Stack;

/**
 *
 * Created by android_ls on 2016/12/26.
 */
public class MainThreadStack<T> extends Stack<T> {

    /**
     *
     */
    private static final long serialVersionUID = -245355093197298112L;

    /**
     * Make sure the method must be called from UI thread.
     */
    public static void ensureOnMainThread() {
        if (Looper.myLooper() != null
                && Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException(
                    "This method must be called from the UI thread.");
        }
    }

    @Override
    public boolean isEmpty() {
        ensureOnMainThread();
        return super.isEmpty();
    }

    @Override
    public T peek() {
        ensureOnMainThread();
        return super.peek();
    }

    @Override
    public T pop() {
        ensureOnMainThread();
        return super.pop();
    }

    @Override
    public T push(T data) {
        ensureOnMainThread();
        return super.push(data);
    }

}
