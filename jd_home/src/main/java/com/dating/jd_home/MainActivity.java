package com.dating.jd_home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.anbetter.bottomnavigation.BadgeItem;
import com.anbetter.bottomnavigation.BottomNavigationBar;
import com.anbetter.bottomnavigation.BottomNavigationItem;
import com.anbetter.log.MLog;
import com.dating.jd_common.TestParcelable;
import com.dating.jd_home.utils.Uris;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.main_container)
    FrameLayout mMainContainer;

    private FragmentManager mFragmentManager;
    private Fragment[] mFragments;
    private int mCurrentTabIndex = -1;

    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private FindFragment mFindFragment;
    private ShoppingCartFragment mShoppingCartFragment;
    private MeFragment mMeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mHomeFragment = HomeFragment.newInstance();
        mCategoryFragment = CategoryFragment.newInstance();
        mShoppingCartFragment = ShoppingCartFragment.newInstance();
        mFindFragment = FindFragment.newInstance();
        mMeFragment = MeFragment.newInstance();

        mFragments = new Fragment[]{mHomeFragment, mCategoryFragment, mFindFragment,
                mShoppingCartFragment, mMeFragment};

        mFragmentManager = getSupportFragmentManager();
        switchTab(0);
        initBottomNavigation();

    }

    private void switchTab(int index) {
        if (mCurrentTabIndex != index) {
            mFragmentManager.beginTransaction()
                    .replace(R.id.main_container, mFragments[index])
                    .commitAllowingStateLoss();
            mCurrentTabIndex = index;
        }
    }

    private void initBottomNavigation() {
        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(2)
                .setBackgroundColorResource(R.color.badge_bg)
                .setText("399+")
                .setHideOnSelect(false);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setAutoHideEnabled(true);

        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.axh, "")
                        .setInactiveIconResource(R.drawable.axg)
                        .setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axd, "")
                        .setInactiveIconResource(R.drawable.axc)
                        .setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axf, "")
                        .setInactiveIconResource(R.drawable.axe)
                        .setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.axb, "")
                        .setInactiveIconResource(R.drawable.axa)
                        .setActiveColorResource(R.color.colorAccent)
                        .setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.axj, "")
                        .setInactiveIconResource(R.drawable.axi)
                        .setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
    }



    //    @OnClick(R.id.btn_test)
//    @OnClick({R.id.btn_test, R.id.btn_test2})
    public void onClick(View v) {
        MLog.i("我呵呵~~");

//        if (v.getId() == R.id.btn_test) {

//            ARouter.getInstance()
//                .build(Uris.PAGE_NAME_TEST_ACTIVITY)
//                .navigation();
//
//            ARouter.getInstance()
//                    .build(Uris.PAGE_NAME_TEST_ACTIVITY)
//                    .navigation(this, new NavCallback() {
//
//                        @Override
//                        public void onFound(Postcard postcard) {
//                            MLog.i("onFound: 找到了 ");
//
//                        }
//
//                        @Override
//                        public void onLost(Postcard postcard) {
//                            MLog.i("onLost: 找不到了 ");
//
//                        }
//
//                        @Override
//                        public void onArrival(Postcard postcard) {
//                            MLog.i("onArrival: 跳转完了 ");
//                            String group = postcard.getGroup();
//                            MLog.i("分组是: " + group);
//
//                        }
//
//                        @Override
//                        public void onInterrupt(Postcard postcard) {
//                            MLog.i("onInterrupt: 被拦截了 ");
//
//                        }
//                    });


            ARouter.getInstance()
                    .build(Uris.PAGE_NAME_TEST_ACTIVITY)
                    .withString("name", "zhangsan")
                    .withInt("age", 18)
                    .withParcelable("test", new TestParcelable("即刻约", "与帕软甲"))
                    .navigation();

//        } else if (v.getId() == R.id.btn_test2) {
//            ServiceManager serviceManager = new ServiceManager();
//            serviceManager.gotoTest2Page("韦小宝", 23, new TestParcelable("色鬼", "黑扫"));

            ARouter.getInstance()
                    .build("/test/Test2Activity")
                    .withString("name", "zhangsan")
                    .withInt("age", 18)
                    .withParcelable("test", new TestParcelable("即刻约", "与帕软甲"))
                    .navigation();

//        }
    }


    @Override
    public void onTabSelected(int position) {
        MLog.i("onTabSelected position = " + position);
        switchTab(position);
    }

    @Override
    public void onTabUnselected(int position) {
        MLog.i("onTabUnselected position = " + position);

    }

    @Override
    public void onTabReselected(int position) {
        MLog.i("onTabReselected position = " + position);

    }

}
