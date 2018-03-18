package com.dating.jd_home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dating.jd_common.TestParcelable;
import com.dating.jd_home.utils.Uris;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Uris.PAGE_NAME_TEST_ACTIVITY)
public class TestActivity extends AppCompatActivity {

    @Autowired()
    String name ;

    @Autowired(name = "age")
    int age ;

    @Autowired
    TestParcelable test;

    @BindView(R.id.tv_text)
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);

        tvTest.setText("name:" + name +",age:" + age  + ",test:"+test);

    }


}
