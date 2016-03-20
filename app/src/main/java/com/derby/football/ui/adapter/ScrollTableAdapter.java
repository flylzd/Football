package com.derby.football.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.bean.PlaceBean;
import com.derby.football.bean.PlaceBean.Header;
import com.derby.football.bean.PlaceBean.Row;
import com.derby.football.bean.PlaceBean.RowItem;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.utils.ResUtil;
import com.derby.football.utils.ToastUtil;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class ScrollTableAdapter extends BaseTableAdapter {

    private final Context context;
    private final LayoutInflater inflater;

    private int firstColumnWidth;
    private int columnWidth;
    private int firstRowHeight;
    private int rowHeight;

    //    public Map<String,Map<String,String>>  list = new HashMap<String,Map<String,String>>();  //时间、价格
//    public Map<String,String> placelist = new HashMap<String,String>();   //场地
    public List<Map<String, Map<String, String>>> list = new ArrayList<Map<String, Map<String, String>>>();  //时间、价格
    public List<Map<String, String>> placelist = new ArrayList<Map<String, String>>(); //场地

    public List<Header> headers = new ArrayList<Header>();
    public List<Row> rows = new ArrayList<Row>();

    public List<RowItem> selectRowItems = new ArrayList<RowItem>();

    public ScrollTableAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        firstColumnWidth = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_first_column_width);
        columnWidth = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_column_width);
        firstRowHeight = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_first_row_height);
        rowHeight = ResUtil.getDimensionPixelSize(R.dimen.find_court_order_table_row_height);
    }

    public void refreshAll(PlaceBean.PlaceInfo placeInfo) {

        headers.clear();
        rows.clear();
//        selectRowItems.clear();

        for (String key : placeInfo.placelist.keySet()) {
            Header header = new Header();
            header.id = key;
            header.name = placeInfo.placelist.get(key);
            header.isChecked = false;
            headers.add(header);
        }
        Map<String, Map<String, String>> timeList = placeInfo.list;
        for (String key : timeList.keySet()) {
            Row row = new Row();
            row.name = key;
            Map<String, String> itemMap = timeList.get(key);
            for (String itemKey : itemMap.keySet()) {
                RowItem rowItem = new RowItem();
                rowItem.id = itemKey;
                rowItem.name = itemMap.get(itemKey);
                rowItem.time = key;  //时间
                rowItem.placeName = placeInfo.placelist.get(itemKey);
                rowItem.isChecked = false;
                row.rowItemList.add(rowItem);
            }
            rows.add(row);
        }
//        System.out.println("header'size is " + headers.size());
//        System.out.println("rows'size is " + rows.size());
        this.notifyDataSetChanged();
    }

    private void order(int row, int column, boolean isChecked) {
//        if (isChecked){  //订购
//        }
        headers.get(column).isChecked = isChecked;
        rows.get(row).isChecked = isChecked;
        rows.get(row).rowItemList.get(column).isChecked = isChecked;
        this.notifyDataSetChanged();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return headers.size();
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_table_header_first, parent, false);
        }
        return convertView;
    }

    private View getHeader(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_table_header, parent, false);
        }
        TextView tvOrderSite = (TextView) convertView.findViewById(R.id.tvOrderSite);

        Header header = headers.get(column);
        tvOrderSite.setText(header.name);
        if (header.isChecked) {
            tvOrderSite.setSelected(true);
        } else {
            tvOrderSite.setSelected(false);
        }

        return convertView;
    }

    private View getBodyFirst(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_table_body_first, parent, false);
        }
        TextView tvOrderTime = (TextView) convertView.findViewById(R.id.tvOrderTime);
        Row rowTime = rows.get(row);
        tvOrderTime.setText(rowTime.name + ":00");
        if (rowTime.isChecked) {
            tvOrderTime.setSelected(true);
        } else {
            tvOrderTime.setSelected(false);
        }

        return convertView;
    }

    private View getBody(final int row, final int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_order_table_body, parent, false);
        }

        final ImageView ivOrderPriceSwitch = (ImageView) convertView.findViewById(R.id.ivOrderPriceSwitch);
        TextView tvOrderPrice = (TextView) convertView.findViewById(R.id.tvOrderPrice);

        Row rowBean = rows.get(row);
        final RowItem rowItem = rowBean.rowItemList.get(column);
        tvOrderPrice.setText(rowItem.name);
//        System.out.println("price " + rowItem.name);

        if (rowItem.isChecked) {
            ivOrderPriceSwitch.setImageDrawable(ResUtil.getDrawable(R.drawable.selector_find_court_order_my_reserve));
        } else {
            ivOrderPriceSwitch.setImageDrawable(ResUtil.getDrawable(R.drawable.selector_find_court_order_can_reserve));
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!selectRowItems.contains(rowItem)){
                    if (selectRowItems.size() == 4) {
                        ToastUtil.showShort(R.string.find_court_order_too_much);
                        return;
                    }
                }
                rowItem.isChecked = !rowItem.isChecked;
                if (rowItem.isChecked){
                    EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_place_add,rowItem);
                    EventBus.getDefault().post(eventCenter);
                    selectRowItems.add(rowItem);
//                    System.out.println("add list'size is " + selectRowItems.size());
                } else {
                    EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_place_del,rowItem);
                    EventBus.getDefault().post(eventCenter);
                    selectRowItems.remove(rowItem);
//                    System.out.println("remove list'size is " + selectRowItems.size());
                }
                order(row, column, rowItem.isChecked);
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
