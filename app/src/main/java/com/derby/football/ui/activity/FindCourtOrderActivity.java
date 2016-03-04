package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.bean.CourtInfoBean;
import com.derby.football.bean.PlaceBean;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.ui.adapter.FindCourtOrderDateAdapter;
import com.derby.football.ui.adapter.FindCourtOrderReserveAdapter;
import com.derby.football.ui.adapter.ScrollTableAdapter;
import com.derby.football.utils.FullyLinearLayoutManager;
import com.derby.football.utils.ToastUtil;
import com.derby.football.utils.UIHelper;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import butterknife.Bind;

public class FindCourtOrderActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
//    SuperRecyclerView recyclerView;
    RecyclerView recyclerView;

    @Bind(R.id.tableFixHeaders)
    TableFixHeaders tableFixHeaders;

    @Bind(R.id.recyclerViewReserve)
    RecyclerView recyclerViewReserve;

    @Bind(R.id.layoutReserveStatus)
    RelativeLayout layoutReserveStatus;

    @Bind(R.id.layoutReserveList)
    LinearLayout layoutReserveList;

    @Bind(R.id.btnOrder)
    LinearLayout btnOrder;

    private FindCourtOrderDateAdapter adapter;
    private ScrollTableAdapter scrollTableAdapter;
    private FindCourtOrderReserveAdapter reserveAdapter;

    private String mid;
    private String selectDate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_court_order;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.find_court_order_title;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        if (extras.containsKey("mid")) {
            mid = extras.getString("mid");
        }
        if (extras.containsKey("date")) {
            selectDate = extras.getString("date");
        }
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        adapter = new FindCourtOrderDateAdapter();
        scrollTableAdapter = new ScrollTableAdapter(this);
        reserveAdapter = new FindCourtOrderReserveAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).build());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerViewReserve.setLayoutManager(gridLayoutManager);
//        recyclerViewReserve.setHasFixedSize(true);
        recyclerViewReserve.setAdapter(reserveAdapter);

        tableFixHeaders.setAdapter(scrollTableAdapter);

        ApiClient.getCourtPlace(this, TAG, mid, selectDate);
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_FIND_COURT_getplace:
                scrollTableAdapter.refreshAll(((PlaceBean) eventCenter.getData()).data);
                break;
            case EventBusCode.SUCCESS_FIND_COURT_order_add:
                if (reserveAdapter.getItemCount() <= 4) {
                    reserveAdapter.add((PlaceBean.RowItem) eventCenter.getData());
                    layoutReserveStatus.setVisibility(View.GONE);
                    layoutReserveList.setVisibility(View.VISIBLE);
                }
                break;
            case EventBusCode.SUCCESS_FIND_COURT_order_del:
                reserveAdapter.remove((PlaceBean.RowItem) eventCenter.getData());
                if (reserveAdapter.getItemCount() == 0) {
                    layoutReserveStatus.setVisibility(View.VISIBLE);
                    layoutReserveList.setVisibility(View.GONE);
                }
                break;
        }
    }

}

