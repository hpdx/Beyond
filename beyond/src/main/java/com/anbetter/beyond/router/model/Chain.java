package com.anbetter.beyond.router.model;

import android.net.Uri;
import android.os.Bundle;

/**
 * Created by android_ls on 16/12/26.
 */

public class Chain {

    public Uri uri;
    public Bundle bundle;
    private boolean intercepted;

    public Chain(Uri uri, Bundle bundle) {
        this.uri = uri;
        this.bundle = bundle;
    }

    public boolean isIntercepted() {
        return intercepted;
    }

    public void setIntercepted(boolean isIntercepted) {
        this.intercepted = isIntercepted;
    }

}
