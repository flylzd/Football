package com.derby.football.ui.activity;


import com.derby.football.R;
import com.derby.football.base.BaseActivity;

public class TeamDetailActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_detail;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.team_detail_title;
    }
}
