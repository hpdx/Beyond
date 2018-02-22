package com.trident.beyond.core;

import android.support.v4.util.Pair;

/**
 *
 * Created by android_ls on 2018/2/9.
 */

public class Section<F, S> implements IModel {

    public final F first;
    public final S second;

    public Section(F first, S second) {
        this.first = first;
        this.second = second;
    }

    private static boolean objectsEqual(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static <A, B> Pair<A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return objectsEqual(p.first, first) && objectsEqual(p.second, second);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

}
