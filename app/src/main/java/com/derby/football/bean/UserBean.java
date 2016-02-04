package com.derby.football.bean;


import java.io.Serializable;

public class UserBean extends BaseBean {

    public UserData data;

    public class UserData implements Serializable {

        public String id;
        public String imei;
        public String token;
        public int status;
    }
}
