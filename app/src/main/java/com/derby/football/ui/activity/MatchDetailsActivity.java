package com.derby.football.ui.activity;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;


public class MatchDetailsActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match_details;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.match_details_title;
    }
}
