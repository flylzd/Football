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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_court_order_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        System.out.println("oldHolder position " + position);
        if (position == 0){
            this.oldHolder = holder;
            System.out.println("oldHolder position222 ==  " + position);
            System.out.println("oldHolder itemView " + oldHolder.itemRootLayout);
            holder.tvWeek.setSelected(true);
            holder.tvDate.setSelected(true);
            holder.lineView.setSelected(true);
        }
        holder.itemRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldHolder.itemRootLayout.equals(v)){
                    System.out.println("sanmesanme  ocClick -----------------------");
                }
                System.out.println("itemRootLayout " + oldHolder.itemRootLayout);
                System.out.println("view " + v);
//                if (holder.itemRootLayout)
                System.out.println("test ocClick --------------------------");
            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemRootLayout)
        LinearLayout itemRootLayout;
        @Bind(R.id.tvWeek)
        TextView tvWeek;
        @Bind(R.id.tvDate)
        TextView tvDate;
        @Bind(R.id.lineView)
        View lineView;

//        @OnClick(R.id.itemRootLayout)
//        public void onClick() {
//            System.out.println("test ocClick --------------------------");
//            tvWeek.setSelected(true);
//            tvDate.setSelected(true);
//            lineView.setSelected(true);
//        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
