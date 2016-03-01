package com.derby.football.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseFragment;
import com.derby.football.bean.CourtBean;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.ui.adapter.FindCourtAdapter;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.Bind;

public class FindCourtFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    @Bind(R.id.recyclerView)
    SuperRecyclerView recyclerView;

    private FindCourtAdapter adapter;

    private StickyHeadersItemDecoration top;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_court;
    }

    @Override
    protected void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
//        recyclerView.setOnMoreListener(this);
//        recyclerView.setRefreshListener(this);
//        recyclerView.setupMoreListener(this);
        adapter = new FindCourtAdapter(getActivity());


//        adapter.setHasStableIds(true);
//        top = new StickyHeadersBuilder()
//                .setAdapter(adapter)
//                .setRecyclerView(recyclerView.getRecyclerView())
//                .setStickyHeadersAdapter(new InitialHeaderAdapter())
//                .setSticky(false)
//                .build();


//        recyclerView.addItemDecoration(top);

    }

    @Override
    protected void onFirstUserVisible() {
        recyclerView.setAdapter(adapter);
//        ApiClient.getArea(getActivity(), TAG, 0);
        if (adapter.isEmpty()){
            ApiClient.getCourtList(getActivity(), TAG, "", 0);
        }
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

        System.out.println("overallItemsCount " + overallItemsCount);
        System.out.println("itemsBeforeMore " + itemsBeforeMore);
        System.out.println("maxLastVisiblePosition " + maxLastVisiblePosition);
//        recyclerView.hideMoreProgress();
    }

    @Override
    public void onRefresh() {

    }


    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_FIND_COURT_getlist: {

                adapter.refreshAll(((CourtBean) eventCenter.getData()).data);
//                String mid = ((CourtBean) eventCenter.getData()).data.get(0).mid;
//                ApiClient.getCourtInfo(getActivity(), TAG, mid);
            }
            break;
            case EventBusCode.SUCCESS_FIND_COURT_getinfo:

                break;
        }
    }
}
