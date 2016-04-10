package com.derby.football.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.bean.CourtData;
import com.derby.football.bean.CourtInfoBean;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.ui.adapter.CourtDetailDateAdapter;
import com.derby.football.utils.FullyLinearLayoutManager;
import com.derby.football.utils.UIHelper;

import butterknife.Bind;


public class CourtDetailActivity extends BaseActivity {

    @Bind(R.id.rlvDate)
    RecyclerView rlvDate;
    @Bind(R.id.tvPictureNumbers)
    TextView tvPictureNumbers;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
    @Bind(R.id.tvPhone)
    TextView tvPhone;
    @Bind(R.id.tvAddress)
    TextView tvAddress;
    @Bind(R.id.tvDiban)
    TextView tvDiban;
    @Bind(R.id.tvOther)
    TextView tvOther;
    @Bind(R.id.tvMaipin)
    TextView tvMaipin;
    @Bind(R.id.tvZulin)
    TextView tvZulin;
    @Bind(R.id.tvXiyu)
    TextView tvXiyu;
    @Bind(R.id.tvTingche)
    TextView tvTingche;

    private CourtDetailDateAdapter adapter;

    private String mid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_court_details;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.find_court_details_title;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        if (extras.containsKey("mid")) {
            mid = extras.getString("mid");
        }
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

        adapter = new CourtDetailDateAdapter(this);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlvDate.setLayoutManager(layoutManager);
        rlvDate.setAdapter(adapter);
//        rlvDate.setVisibility(View.VISIBLE);

        ApiClient.getCourtInfo(this, TAG, mid);

    }

    private void showDetails(CourtData data) {

        tvPictureNumbers.setText(data.pictureNumbers);
        tvDescription.setText(data.description);
        tvPhone.setText(data.tel);
        tvAddress.setText(data.address);
        tvDiban.setText(data.diban);
        tvOther.setText(data.other);
        tvMaipin.setText(data.maipin);
        tvZulin.setText(data.zulin);
        tvXiyu.setText(data.xiyu);
        tvTingche.setText(data.tingche);
    }

    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_COURT_getinfo:
                showDetails(((CourtInfoBean)eventCenter.getData()).data);
                break;
            case EventBusCode.SUCCESS_COURT_go_choose:
                String date = (String) eventCenter.getData();
                UIHelper.showCourtChooseActivity(CourtDetailActivity.this, mid, date);
//                finish();
                break;
        }
    }

}
