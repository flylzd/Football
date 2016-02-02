package com.derby.football.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.derby.football.R;
import com.derby.football.utils.StatusBarCompat;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private static final int ERROR_LAYOUT_ID = 0;

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        onBeforeSetContentView();
        int layoutId = getLayoutId();
        if (layoutId == ERROR_LAYOUT_ID) {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        rootView = View.inflate(this, getLayoutId(), null);
        setContentView(rootView);

//        StatusBarCompat.compat(this, getResources().getColor(R.color.colorAccent));
        StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimary));
        ButterKnife.bind(this);

        initViewsAndEvents(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void getBundleExtras(Bundle extras) {

    }

    protected void onBeforeSetContentView() {

    }

    protected int getLayoutId() {
        return ERROR_LAYOUT_ID;
    }

    protected void initToolbar() {

    }

    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    protected View getRootView() {
        return rootView;
    }

}
