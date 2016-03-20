package com.derby.football.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import de.greenrobot.event.EventBus;

public class CourtDetailDateAdapter extends RecyclerView.Adapter<CourtDetailDateAdapter.ViewHolder> {


    private Context context;
    private List<Date> weekDays;

    public CourtDetailDateAdapter(Context context) {
        this.context = context;
        weekDays = DateUtil.dateToWeek(new Date());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_court_details_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Date weekDay = weekDays.get(position);
        // 定义输出日期格式
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEE");
//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM月dd日");
        holder.tvDay.setText(sdfDay.format(weekDay));
        holder.tvDate.setText(sdfDate.format(weekDay));

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UIHelper.showFindCourtOrderActivity(context);
                SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
                String date = sdfDate.format(weekDay);
                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_go_choose, date);
                EventBus.getDefault().post(eventCenter);

            }
        });
    }

    @Override
    public int getItemCount() {
        return weekDays.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvDay)
        TextView tvDay;
        @Bind(R.id.tvDate)
        TextView tvDate;
        @Bind(R.id.btnBook)
        Button btnBook;
        @Bind(R.id.layoutItem)
        LinearLayout layoutItem;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
