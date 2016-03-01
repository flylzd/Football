package com.derby.football.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.bean.CourtBean;
import com.derby.football.bean.CourtData;
import com.derby.football.utils.ResUtil;
import com.derby.football.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindCourtAdapter extends RecyclerView.Adapter<FindCourtAdapter.ViewHolder> {

    private Context context;
    private List<CourtData> dataList = new ArrayList<CourtData>();

    public FindCourtAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<CourtData> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void refreshAll(List<CourtData> dataList) {
        this.dataList.clear();
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return dataList.isEmpty();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_court, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final CourtData itemBean = dataList.get(position);

        holder.tvName.setText(itemBean.name);
        holder.tvAddress.setText(itemBean.address);

        String lowPrice = String.format(ResUtil.getString(R.string.find_court_low_price),itemBean.lowPrice);
        holder.tvPrice.setText(lowPrice);

        String phone = String.format(ResUtil.getString(R.string.find_court_phone),itemBean.tel);
        holder.tvPhone.setText(phone);

        holder.layoutCourtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UIHelper.showFindCourtDetailActivity(context,itemBean.mid);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.layoutCourtDetail)
        LinearLayout layoutCourtDetail;

        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvDistance)
        TextView tvDistance;
        @Bind(R.id.tvAddress)
        TextView tvAddress;
        @Bind(R.id.tvPrice)
        TextView tvPrice;

        @Bind(R.id.tvPhone)
        TextView tvPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
//            layoutCourtDetail = (LinearLayout) itemView.findViewById(R.id.layoutCourtDetail);
        }
    }
}
