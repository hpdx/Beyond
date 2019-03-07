package com.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.anbetter.beyond.fragment.BasePageFragment;
import com.anbetter.log.MLog;
import com.gank.nav.Navigator;

/**
 *
 * Created by android_ls on 2016/12/26.
 */

public class BlankFragment extends BasePageFragment {

    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    private String title = "";
    public static BlankFragment newInstance(Bundle bundle) {
        BlankFragment fragment = new BlankFragment();

        if(bundle != null) {
            String title = bundle.getString("title");
            String name = bundle.getString("name");
            int age = bundle.getInt("age");

            MLog.i("------------BlankFragment---------------");
            MLog.i("title = " + title);
            MLog.i("name = " + name);
            MLog.i("age = " + age);

            fragment.title = title;
        }

        return fragment;
    }

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        mPageFragmentHost.setActionBarTitle("GanK");
        mPageFragmentHost.displayActionBarBack(false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_blank;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView textView = findViewById(R.id.title_template);
        textView.setText(title);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.baidu.com";
                Navigator.goToWebFragment(url);
            }
        });

        findViewById(R.id.btn_open_gank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.goToGanKListFragment();
            }
        });

        findViewById(R.id.btn_open_gank2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.goToGanKPagingListFragment();
            }
        });

        findViewById(R.id.btn_open_gank_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.goToGanKDayFragment();
            }
        });

        findViewById(R.id.btn_open_gank_tab_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.goToGanKTabListFragment();
            }
        });

        findViewById(R.id.btn_open_gank_tab_paging_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.goToGanKTabPagingListFragment();
            }
        });

        findViewById(R.id.btn_open_gank_multi_type_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.goToGanKMultiTypeTabFragment();
            }
        });

    }


}
