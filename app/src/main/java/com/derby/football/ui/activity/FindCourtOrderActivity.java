package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.FindCourtOrderDateAdapter;
import com.derby.football.ui.adapter.FindCourtOrderReserveAdapter;
import com.derby.football.ui.adapter.ScrollTableAdapter;
import com.derby.football.utils.FullyLinearLayoutManager;
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

    private FindCourtOrderDateAdapter adapter;
    private ScrollTableAdapter scrollTableAdapter ;
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
        if (extras.containsKey("mid")){
            mid = extras.getString("mid");
        }
        if (extras.containsKey("date")){
            selectDate = extras.getString("date");
        }
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        adapter = new FindCourtOrderDateAdapter();
        scrollTableAdapter = new ScrollTableAdapter(this);
        reserveAdapter = new FindCourtOrderReserveAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).build());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        recyclerViewReserve.setLayoutManager(gridLayoutManager);
//        recyclerViewReserve.setHasFixedSize(true);
        recyclerViewReserve.setAdapter(reserveAdapter);

        tableFixHeaders.setAdapter(scrollTableAdapter);

        ApiClient.getCourtPlace(this,TAG,mid,selectDate);
    }

}

