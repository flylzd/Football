package com.derby.football.ui.activity;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.main_tab_team;
    }
}
