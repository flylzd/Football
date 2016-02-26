package com.derby.football.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.ui.adapter.CityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CityAreaActivity extends BaseActivity {


    @Bind(R.id.recyclerViewCity)
    RecyclerView recyclerViewCity;
    @Bind(R.id.recyclerViewArea)
    RecyclerView recyclerViewArea;

    private CityAdapter cityAdapter;
    private CityAdapter areaAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_city_area;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.city_area_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        List<String> cityDatas = new ArrayList<String>();
        List<String> areaDatas = new ArrayList<String>();

        cityDatas.add("广州");
        cityDatas.add("佛山");

        areaDatas.add("天河区");
        areaDatas.add("番禺区");
        areaDatas.add("黄埔区");

        cityAdapter = new CityAdapter(cityDatas);
        areaAdapter = new CityAdapter(areaDatas);

        LinearLayoutManager linearLayoutManagerCity = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManagerArea = new LinearLayoutManager(this);
        recyclerViewCity.setLayoutManager(linearLayoutManagerCity);
        recyclerViewArea.setLayoutManager(linearLayoutManagerArea);
        recyclerViewCity.setHasFixedSize(true);
        recyclerViewArea.setHasFixedSize(true);
        recyclerViewCity.setAdapter(cityAdapter);
        recyclerViewArea.setAdapter(areaAdapter);



    }


}
