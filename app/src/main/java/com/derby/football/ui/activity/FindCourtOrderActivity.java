package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.FindCourtOrderDateAdapter;
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

    private FindCourtOrderDateAdapter adapter;
    private ScrollTableAdapter scrollTableAdapter ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_court_order;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.find_court_order_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        adapter = new FindCourtOrderDateAdapter();
        scrollTableAdapter = new ScrollTableAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).build());
//        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
//        rlvDate.setVisibility(View.VISIBLE);

        tableFixHeaders.setAdapter(scrollTableAdapter);
    }

}

