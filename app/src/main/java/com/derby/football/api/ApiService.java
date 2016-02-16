package com.derby.football.api;


import com.derby.football.bean.UserBean;

import java.util.Map;

import retrofit.Call;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ApiService {

    String U = "u";  //'c'+用户id(默认为：c1)
    String I = "i";  //用户IMEI
    String T = "t";  //用户TOKEN
    String P = "p";  //具体的参数

    String P_BEAN = "bean";
    String P_ACTION = "action";
    String P_PARAM = "param";

    String C1 = "c1";


    /********************************************************/

    @FormUrlEncoded
    @POST("user/index.php")
    Call<UserBean> login(@FieldMap Map<String,String> params);
}
