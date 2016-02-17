package com.derby.football.base;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.utils.StatusBarCompat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private static final int ERROR_LAYOUT_ID = 0;

    private View rootView;

    private Toolbar toolbar;
    private TextView toolbarTitle;


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

        initToolbar();
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

    private void initToolbar() {

        View toolbarView = rootView.findViewById(R.id.toolbar);
        if (toolbarView != null) {
            toolbar = (Toolbar) toolbarView;
            setSupportActionBar(toolbar);
            toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            if (toolbarTitle != null) {
                int titleResId = getToolbarTitle();
                if (titleResId != 0) {
                    toolbarTitle.setText(titleResId);
                }
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
            if (isDisplayBackEnabled()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    protected View getRootView() {
        return rootView;
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    protected int getToolbarTitle() {
        return 0;
    }

    public void setTitle(int resId) {
        if (toolbarTitle != null) {
            toolbarTitle.setText(resId);
        }
    }

    public void setTitle(String title) {
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }


    protected boolean isDisplayBackEnabled() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
