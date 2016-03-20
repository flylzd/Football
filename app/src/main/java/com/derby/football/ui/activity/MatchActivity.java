package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.MatchAdapter;
import com.derby.football.ui.adapter.TrainingAdapter;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;

public class MatchActivity  extends BaseActivity {

    @Bind(R.id.recyclerView)
    SuperRecyclerView recyclerView;

    private MatchAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.match_title;
    }


    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
//        recyclerView.setOnMoreListener(this);
//        recyclerView.setRefreshListener(this);
//        recyclerView.setupMoreListener(this);
        adapter = new MatchAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
    }
}
