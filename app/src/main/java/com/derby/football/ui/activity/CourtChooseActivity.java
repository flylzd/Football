package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.bean.PlaceBean;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.ui.adapter.CourtChooseDateAdapter;
import com.derby.football.ui.adapter.FindCourtOrderReserveAdapter;
import com.derby.football.ui.adapter.ScrollTableAdapter;
import com.derby.football.utils.ToastUtil;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import butterknife.Bind;

public class CourtChooseActivity extends BaseActivity {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    //    SuperRecyclerView recyclerView;

    @Bind(R.id.tableFixHeaders)
    TableFixHeaders tableFixHeaders;

    @Bind(R.id.recyclerViewReserve)
    RecyclerView recyclerViewReserve;

    @Bind(R.id.layoutReserveStatus)
    RelativeLayout layoutReserveStatus;

    @Bind(R.id.layoutReserveList)
    LinearLayout layoutReserveList;

    @Bind(R.id.btnOrder)
    Button btnOrder;

    private CourtChooseDateAdapter dateAdapter;
    private ScrollTableAdapter scrollTableAdapter;
    private FindCourtOrderReserveAdapter reserveAdapter;

    private String mid;
    private String selectDate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_court_choose;
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

        dateAdapter = new CourtChooseDateAdapter();
        scrollTableAdapter = new ScrollTableAdapter(this);
        reserveAdapter = new FindCourtOrderReserveAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).build());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(dateAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerViewReserve.setLayoutManager(gridLayoutManager);
//        recyclerViewReserve.setHasFixedSize(true);
        recyclerViewReserve.setAdapter(reserveAdapter);

        tableFixHeaders.setAdapter(scrollTableAdapter);

        ApiClient.getCourtPlace(this, TAG, mid, selectDate);
    }

    @Override
    protected void onResume() {
       super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_COURT_getplace:
                scrollTableAdapter.refreshAll(((PlaceBean) eventCenter.getData()).data);
                break;
            case EventBusCode.SUCCESS_COURT_place_add:
                if (reserveAdapter.getItemCount() <= 4) {
                    reserveAdapter.add((PlaceBean.RowItem) eventCenter.getData());
                    layoutReserveStatus.setVisibility(View.GONE);
                    layoutReserveList.setVisibility(View.VISIBLE);
                }
                break;
            case EventBusCode.SUCCESS_COURT_place_del:
                reserveAdapter.remove((PlaceBean.RowItem) eventCenter.getData());
                if (reserveAdapter.getItemCount() == 0) {
                    layoutReserveStatus.setVisibility(View.VISIBLE);
                    layoutReserveList.setVisibility(View.GONE);
                }
                break;
            case EventBusCode.SUCCESS_COURT_choose_date_change:
                selectDate = (String) eventCenter.getData();
                ApiClient.getCourtPlace(this, TAG, mid, selectDate);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_court_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.action_order:
                ToastUtil.showShort("action_order");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}

