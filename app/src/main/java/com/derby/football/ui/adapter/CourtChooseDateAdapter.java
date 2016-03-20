package com.derby.football.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class CourtChooseDateAdapter extends RecyclerView.Adapter<CourtChooseDateAdapter.ViewHolder> {

    private ViewHolder oldHolder;
    private int oldPosition;
    private int firstSelect = 0;
    private int currentPosition = 0;

    private List<Date> weekDays;

    public CourtChooseDateAdapter() {
        weekDays = DateUtil.dateToWeek(new Date());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_court_choose_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (position == currentPosition) {
            this.oldHolder = holder;
            oldHolder.tvDay.setSelected(true);
            oldHolder.tvDate.setSelected(true);
            oldHolder.lineView.setSelected(true);
        }

        final Date weekDay = weekDays.get(position);
        // 定义输出日期格式
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEE");
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM月dd日");
        holder.tvDay.setText(sdfDay.format(weekDay));
        holder.tvDate.setText(sdfDate.format(weekDay));

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemRootLayout)
        LinearLayout itemRootLayout;
        @Bind(R.id.tvDay)
        TextView tvDay;
        @Bind(R.id.tvDate)
        TextView tvDate;
        @Bind(R.id.lineView)
        View lineView;

        @OnClick(R.id.itemRootLayout)
        public void onClick() {

            if (!oldHolder.equals(this)) {
                this.tvDay.setSelected(true);
                this.tvDate.setSelected(true);
                this.lineView.setSelected(true);

                oldHolder.tvDay.setSelected(false);
                oldHolder.tvDate.setSelected(false);
                oldHolder.lineView.setSelected(false);

//                System.out.println("position " + this.getLayoutPosition());
                currentPosition = this.getLayoutPosition();
                Date weekDay = weekDays.get(currentPosition);
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
                String date = sdfDate.format(weekDay);
                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_choose_date_change, date);
                EventBus.getDefault().post(eventCenter);

                oldHolder = this;
            }
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
