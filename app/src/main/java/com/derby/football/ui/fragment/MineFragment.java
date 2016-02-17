package com.derby.football.ui.fragment;


import com.derby.football.R;
import com.derby.football.base.BaseFragment;

public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }
}
