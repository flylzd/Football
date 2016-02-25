package com.derby.football.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derby.football.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindCourtOrderDateAdapter extends RecyclerView.Adapter<FindCourtOrderDateAdapter.ViewHolder> {

    private ViewHolder oldHolder;
    private int oldPosition;
    private int firstSelect = 0;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_court_order_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position == 0) {
            this.oldHolder = holder;
            oldHolder.tvWeek.setSelected(true);
            oldHolder.tvDate.setSelected(true);
            oldHolder.lineView.setSelected(true);
        }

//        holder.itemRootLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (oldPosition != position) {
//
//                    holder.tvWeek.setSelected(true);
//                    holder.tvDate.setSelected(true);
//                    holder.lineView.setSelected(true);
//
//                    oldHolder.tvWeek.setSelected(false);
//                    oldHolder.tvDate.setSelected(false);
//                    oldHolder.lineView.setSelected(false);
//
//                    oldHolder = holder;
//                    oldPosition = position;
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemRootLayout)
        LinearLayout itemRootLayout;
        @Bind(R.id.tvWeek)
        TextView tvWeek;
        @Bind(R.id.tvDate)
        TextView tvDate;
        @Bind(R.id.lineView)
        View lineView;

        @OnClick(R.id.itemRootLayout)
        public void onClick() {

            if (!oldHolder.equals(this)) {
                this.tvWeek.setSelected(true);
                this.tvDate.setSelected(true);
                this.lineView.setSelected(true);

                oldHolder.tvWeek.setSelected(false);
                oldHolder.tvDate.setSelected(false);
                oldHolder.lineView.setSelected(false);

                oldHolder = this;
            }
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
