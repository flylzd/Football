package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.bean.CourtBean;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.ui.adapter.CourtAdapter;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.Bind;

public class CourtActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    SuperRecyclerView recyclerView;

    private CourtAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_court;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.court_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
//        recyclerView.setOnMoreListener(this);
//        recyclerView.setRefreshListener(this);
//        recyclerView.setupMoreListener(this);
        adapter = new CourtAdapter(this);

        recyclerView.setAdapter(adapter);
        if (adapter.isEmpty()){
            ApiClient.getCourtList(this, TAG, "", 0);
        }
    }


    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_COURT_getlist: {

                adapter.refreshAll(((CourtBean) eventCenter.getData()).data);
//                String mid = ((CourtBean) eventCenter.getData()).data.get(0).mid;
//                ApiClient.getCourtInfo(getActivity(), TAG, mid);
            }
            break;
//            case EventBusCode.SUCCESS_FIND_COURT_getinfo:
//
//                break;
        }
    }
}
