package com.derby.football.ui.fragment;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.derby.football.R;
import com.derby.football.base.BaseFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindFragment extends BaseFragment {

    @Bind(R.id.viewPagerTab)
    SmartTabLayout viewPagerTab;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void onUserVisible() {
    }

    @Override
    protected void initView() {

        FragmentPagerItems.Creator itemsCreator = FragmentPagerItems.with(getActivity());
        itemsCreator.add(R.string.find_tab_match, FindMatchFragment.class);
        itemsCreator.add(R.string.find_tab_team, FindTeamFragment.class);
        itemsCreator.add(R.string.find_tab_court, FindCourtFragment.class);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), itemsCreator.create());

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

    }

}
