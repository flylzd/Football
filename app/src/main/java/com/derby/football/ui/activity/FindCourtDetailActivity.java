package com.derby.football.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.FindCourtDateAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FindCourtDetailActivity extends BaseActivity {


    @Bind(R.id.rlvDate)
    RecyclerView rlvDate;

    private FindCourtDateAdapter adapter;

    private final static int SPAN_COUNT = 5;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_court_details;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.find_court_details_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        adapter = new FindCourtDateAdapter();

//        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlvDate.setLayoutManager(layoutManager);
//        rlvDate.addItemDecoration(new );
        rlvDate.setAdapter(adapter);
    }

}
