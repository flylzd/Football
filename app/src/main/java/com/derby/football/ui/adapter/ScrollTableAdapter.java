package com.derby.football.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.utils.ResUtil;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

public class ScrollTableAdapter extends BaseTableAdapter {

    private final Context context;
    private final LayoutInflater inflater;

    private int firstColumnWidth;
    private int columnWidth;
    private int firstRowHeight;
    private int rowHeight;

    public ScrollTableAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        firstColumnWidth = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_first_column_width);
        columnWidth  = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_column_width);
        firstRowHeight = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_first_row_height);
        rowHeight = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_row_height);

    }

    @Override
    public int getRowCount() {
        return 14;
    }

    @Override
    public int getColumnCount() {
        return 20;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        View view = null;
        int viewType = getItemViewType(row, column);
        switch (viewType) {
            case 0:
                view = getHeaderFirst(row, column, convertView, parent);
                break;
            case 1:
                view = getHeader(row, column, convertView, parent);
                break;
            case 2:
                view = getBodyFirst(row, column, convertView, parent);
                break;
            case 3:
                view = getBody(row, column, convertView, parent);
                break;
        }
        return view;
    }

    private View getHeaderFirst(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_order_table_header_first, parent, false);
        }
        return convertView;
    }

    private View getHeader(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_order_table_header, parent, false);
        }
        return convertView;
    }

    private View getBodyFirst(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_table_body_first, parent, false);
        }
        return convertView;
    }

    private View getBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_table_body, parent, false);
        }

        final ImageView ivOrderPriceSwitch = (ImageView) convertView.findViewById(R.id.ivOrderPriceSwitch);
        TextView tvOrderPrice = (TextView) convertView.findViewById(R.id.tvOrderPrice);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivOrderPriceSwitch.setImageDrawable(ResUtil.getDrawable(R.drawable.selector_find_court_order_has_reserve));
//                ivOrderPriceSwitch.setBackground(ResUtil.getDrawable(R.drawable.selector_find_court_order_has_reserve));
            }
        });

        return convertView;
    }

    @Override
    public int getWidth(int column) {
//        return 150;
        if (column == -1) {
            return firstColumnWidth;
        } else {
            return columnWidth;
        }
    }

    @Override
    public int getHeight(int row) {
//        return 100;
        if (row == -1) {
            return firstRowHeight;
        } else {
            return rowHeight;
        }
    }

    @Override
    public int getItemViewType(int row, int column) {
        int itemViewType = 0;
        if (row == -1 && column == -1) {
            itemViewType = 0;
        } else if (row == -1) {
            itemViewType = 1;
        } else if (column == -1) {
            itemViewType = 2;
        } else {
            itemViewType = 3;
        }
        return itemViewType;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }
}
