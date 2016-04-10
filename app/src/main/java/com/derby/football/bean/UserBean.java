package com.derby.football.bean;


import java.io.Serializable;

public class UserBean extends BaseBean {

    public UserData data;

    public static class UserData implements Serializable {

        public String id;
        public String imei;
        public String token;
        public int status;

        public String name;
        public String nickName;
        public String birthday;
        public String sex;
        public String areaID;
        public String url;

        @Override
        public String toString() {
            return "UserData{" +
                    "id='" + id + '\'' +
                    ", imei='" + imei + '\'' +
                    ", token='" + token + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "data=" + data.toString() +
                '}';
    }
}
