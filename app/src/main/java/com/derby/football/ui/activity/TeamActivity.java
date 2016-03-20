package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.TeamAdapter;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.melnykov.fab.FloatingActionButton;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;

public class TeamActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private TeamAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.team_title;
    }


    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
//        recyclerView.setOnMoreListener(this);
//        recyclerView.setRefreshListener(this);
//        recyclerView.setupMoreListener(this);
        adapter = new TeamAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

//        fab.attachToRecyclerView(recyclerView);

    }

}
