package com.derby.football.ui.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseFragment;
import com.derby.football.ui.adapter.InitialHeaderAdapter;
import com.derby.football.ui.adapter.StringListAdapter;
import com.eowise.recyclerview.stickyheaders.StickyHeadersBuilder;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;

public class FindCourtFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    @Bind(R.id.recyclerView)
    SuperRecyclerView recyclerView;

    private StickyHeadersItemDecoration top;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_court;
    }

    @Override
    protected void initView() {

        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("data " + i);
        }

        ArrayList<String> headers = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            datas.add("header " + i);
        }

        StringListAdapter adapter = new StringListAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        recyclerView.setOnMoreListener(this);
        recyclerView.setRefreshListener(this);

        adapter.setHasStableIds(true);
        top = new StickyHeadersBuilder()
                .setAdapter(adapter)
                .setRecyclerView(recyclerView.getRecyclerView())
                .setStickyHeadersAdapter(new InitialHeaderAdapter())
                .setSticky(false)
                .build();

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(top);

        ApiClient.getArea(getActivity(), TAG, 0);

    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {

    }

    @Override
    public void onRefresh() {

    }
}
