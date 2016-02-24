package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.FindCourtOrderDateAdapter;
import com.derby.football.utils.FullyLinearLayoutManager;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindCourtOrderActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private FindCourtOrderDateAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_court_order;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.find_court_details_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        adapter = new FindCourtOrderDateAdapter();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).build());
        recyclerView.setAdapter(adapter);
//        rlvDate.setVisibility(View.VISIBLE);
    }

}

