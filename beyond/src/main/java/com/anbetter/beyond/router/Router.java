package com.anbetter.beyond.router;


import android.net.Uri;
import android.os.Bundle;

import com.anbetter.beyond.router.dispatcher.Dispatcher;
import com.anbetter.beyond.router.interfaces.IDispatcher;
import com.anbetter.beyond.router.interfaces.INavigation;
import com.anbetter.beyond.router.interfaces.Interceptor;
import com.anbetter.beyond.router.model.Chain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by android_ls on 16/12/26.
 */

public class Router {
    private static Router sRouter;
    private List<Interceptor> mInterceptors = new ArrayList<>();
    private IDispatcher mDispatcher;
    private INavigation mNavigation;

    private Router(INavigation navigationManager) {
        this.mNavigation = navigationManager;
        mDispatcher = new Dispatcher();
    }

    public static Router install(INavigation navigation) {
        if (sRouter == null) {
            sRouter = new Router(navigation);
        }
        return sRouter;
    }

    public INavigation getNavigationManager() {
        return mNavigation;
    }

    public static Builder navigation() {
        return new Builder(sRouter);
    }

    public Router addInterceptor(Interceptor interceptor) {
        if (!mInterceptors.contains(interceptor)) {
            mInterceptors.add(interceptor);
        }
        return this;
    }

    public IDispatcher getDispatcher() {
        return mDispatcher;
    }

    public List<Interceptor> getInterceptors() {
        return mInterceptors;
    }

    public static class Builder {

        protected Router router;
        protected INavigation navigationManager;

        protected Uri.Builder builder;
        /**
         * 打开一个Fragment需要传递数据Bundle
         */
        protected Bundle bundle;
        protected Uri uri;

        public Builder(Router router) {
            this.router = router;
            this.navigationManager = router.getNavigationManager();
            bundle = new Bundle();
            builder = new Uri.Builder().scheme(SwitchersProvider.SCHEME_DATING);
            builder.authority(SwitchersProvider.HOST);
        }

        public Builder setUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder setUri(String uriString) {
            this.uri = Uri.parse(uriString);
            return this;
        }

        public Builder setBundle(Bundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public void start() {
            Uri realUri = uri == null ? builder.build() : uri;
            Chain chain = new Chain(realUri, bundle);
            if (router.getInterceptors() != null && router.getInterceptors().size() > 0) {
                for (Interceptor interceptor : router.getInterceptors()) {
                    interceptor.intercept(navigationManager, chain);
                }
            }

            if (!chain.isIntercepted()) {
                router.getDispatcher().dispatch(navigationManager, chain.uri, chain.bundle);
            }
        }

        public void goBack() {
            if(navigationManager != null) {
                navigationManager.goBack();
            }
        }

        public void goHome() {
            if (navigationManager != null) {
                navigationManager.goHome();
            }
        }

        public String getCurrentPage() {
            if(navigationManager != null) {
                return navigationManager.getCurrentPage();
            }
            return null;
        }

        public Builder appendQueryParameter(String key, String value) {
            builder = builder.appendQueryParameter(key, value);
            return this;
        }

        public Builder setTitle(String title) {
            bundle.putString(FragmentSwitcher.TITLE, title);
            return this;
        }

        public Builder setPageName(String pageName) {
            builder.appendPath(pageName);
            return this;
        }

        public Builder appendPath(String path) {
            builder = builder.appendPath(path);
            return this;
        }

        public Builder putString(String key, String value) {
            bundle.putString(key, value);
            return this;
        }

        public Builder putInt(String key, int value) {
            bundle.putInt(key, value);
            return this;
        }

        public Builder putBoolean(String key, boolean value) {
            bundle.putBoolean(key, value);
            return this;
        }
    }

    public void release() {
        if(mNavigation != null) {
            mNavigation = null;
        }

        if(mInterceptors != null) {
            mInterceptors.clear();
            mInterceptors = null;
        }
    }

    public static void unInstall() {
        if (sRouter != null) {
            sRouter.release();
            sRouter = null;
        }
    }

}
