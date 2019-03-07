package com.anbetter.beyond.rxbus;


import com.anbetter.beyond.rxbus.thread.ThreadEnforcer;

/**
 * 对原开源项目做了大幅精简
 *
 * https://github.com/AndroidKnife/RxBus
 */
public class RxBus {

    private static Bus sBus;

    private RxBus() {

    }

    public static Bus get() {
        if (sBus == null) {
            synchronized (RxBus.class) {
                if (sBus == null) {
                    sBus = new Bus(ThreadEnforcer.ANY);
                }
            }
        }
        return sBus;
    }

}