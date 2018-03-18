package com.dating.jd_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dating.jd_common.TestParcelable;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_test)
    public void onClick(View v) {

        ARouter.getInstance()
                .build("/test/Test2Activity")
                .withString("name", "zhangsan")
                .withInt("age", 18)
                .withParcelable("test", new TestParcelable("即刻约", "与帕软甲"))
                .navigation();
    }

}
