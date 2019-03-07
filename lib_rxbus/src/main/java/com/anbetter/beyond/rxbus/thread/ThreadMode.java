package com.anbetter.beyond.rxbus.thread;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public enum ThreadMode {
    MAIN,
    NEW_THREAD,
    IO,
    COMPUTATION,
    TRAMPOLINE,
    EXECUTOR;

    public static Scheduler getScheduler(ThreadMode thread) {
        Scheduler scheduler;
        switch (thread) {
            case MAIN:
                scheduler = AndroidSchedulers.mainThread();
                break;
            case NEW_THREAD:
                scheduler = Schedulers.newThread();
                break;
            case IO:
                scheduler = Schedulers.io();
                break;
            case COMPUTATION:
                scheduler = Schedulers.computation();
                break;
            case TRAMPOLINE:
                scheduler = Schedulers.trampoline();
                break;
            case EXECUTOR:
                scheduler = Schedulers.from(ThreadHandler.DEFAULT.getExecutor());
                break;
            default:
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        return scheduler;
    }
}
