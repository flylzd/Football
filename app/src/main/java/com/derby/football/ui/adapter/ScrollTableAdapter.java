package com.derby.football.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.derby.football.R;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

public class ScrollTableAdapter extends BaseTableAdapter {

    private final Context context;
    private final LayoutInflater inflater;

    public ScrollTableAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getRowCount() {
        return 50;
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
            case 1:
            case 2:
            case 3:
                view = getBody(row, column, convertView, parent);
                break;
        }
        return view;
    }

    private View getBody(int row, int column, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_order_table_body, parent, false);
        return convertView;
    }

    @Override
    public int getWidth(int column) {
        return 100;
    }

    @Override
    public int getHeight(int row) {
        return 50;
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
