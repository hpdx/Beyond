package com.anbetter.beyond.rxbus.annotation;

import com.anbetter.beyond.rxbus.thread.ThreadMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    int code() default -1;
    ThreadMode threadMode() default ThreadMode.MAIN;
}