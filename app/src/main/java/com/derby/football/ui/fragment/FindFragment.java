package com.derby.football.ui.fragment;


import com.derby.football.R;
import com.derby.football.base.BaseFragment;

public class FindFragment extends BaseFragment {

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }
}
