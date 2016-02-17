package com.derby.football.ui.fragment;


import com.derby.football.R;
import com.derby.football.base.BaseFragment;

public class TeamFragment extends BaseFragment {

    public static TeamFragment newInstance() {
        TeamFragment fragment = new TeamFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team;
    }
}
