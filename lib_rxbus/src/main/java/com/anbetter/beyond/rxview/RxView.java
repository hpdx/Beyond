package com.anbetter.beyond.rxview;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * <p>
 * Created by android_ls on 2018/10/23.
 *
 * @author 李松
 * @version 1.0
 */
public class RxView {

    private static final int ONCLICK_INTERVAL_TIME = 500;

    private RxView() {
        throw new AssertionError("No instances.");
    }

    public static void clicks(View view, Consumer<Object> consumer) {
        Disposable disposable = RxView.clicks(view)
                .throttleFirst(ONCLICK_INTERVAL_TIME, TimeUnit.MILLISECONDS)
                .subscribe(consumer);
    }

    @CheckResult
    @NonNull
    private static Observable<Object> clicks(@NonNull View view) {
        checkNotNull(view, "view == null");
        return new ViewClickObservable(view);
    }

    private static void checkNotNull(Object value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }

}
