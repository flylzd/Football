package com.derby.football.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.bean.PlaceBean;
import com.derby.football.bean.PlaceBean.RowItem;
import com.derby.football.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindCourtOrderReserveAdapter extends RecyclerView.Adapter<FindCourtOrderReserveAdapter.ViewHolder> {

    private Context context;
    private List<RowItem> rowItems = new ArrayList<RowItem>();

    public FindCourtOrderReserveAdapter(Context context) {
        this.context = context;
    }

    public void add(RowItem rowItem) {
        this.rowItems.add(rowItem);
        this.notifyDataSetChanged();
    }

    public void remove(RowItem rowItem) {
        int size = this.rowItems.size();
        for (int i=0; i<size; i++){
            RowItem tmp = this.rowItems.get(i);
            if (tmp.toString().equals(rowItem.toString())){
                this.rowItems.remove(i);
                break;
            }
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_court_order_reserve, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RowItem rowItem = rowItems.get(position);
        int timeTmp = Integer.valueOf(rowItem.time);
        String time = TimeUtil.getTimePeriods(rowItem.time);
        holder.tvTime.setText(time);
        holder.tvPlace.setText(rowItem.placeName);
    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvTime)
        TextView tvTime;
        @Bind(R.id.tvPlace)
        TextView tvPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
