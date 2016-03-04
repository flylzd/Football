package com.derby.football.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceBean extends BaseBean {

    public PlaceInfo data;

    public class PlaceInfo implements Serializable {
        public Map<String,Map<String,String>>  list;
        public Map<String,String> placelist;   //场地
    }

    public List<Header> headers = new ArrayList<Header>();
    public List<Row> rows = new ArrayList<Row>();

    public static class Header implements Serializable{
        public String id;
        public String name;
        public boolean isChecked;
    }

    public static class Row implements Serializable{
        public String name;
        public boolean isChecked;

        public List<RowItem> rowItemList = new ArrayList<RowItem>();
    }

    public static class RowItem implements Serializable{
        public String id;
        public String time;
        public String name;
        public String placeName;
        public boolean isChecked;

        @Override
        public String toString() {
            return "RowItem{" +
                    "id='" + id + '\'' +
                    ", time='" + time + '\'' +
                    ", name='" + name + '\'' +
                    ", placeName='" + placeName + '\'' +
                    ", isChecked=" + isChecked +
                    '}';
        }
    }

}
