package com.derby.football.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.base.BaseFragment;
import com.derby.football.utils.UIHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @Bind(R.id.layoutMine)
    LinearLayout layoutMine;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.searchLayout)
    LinearLayout searchLayout;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @OnClick(R.id.layoutMine)
    public void onClick() {
        UIHelper.showMineInfoActivity(getActivity());
    }
}
