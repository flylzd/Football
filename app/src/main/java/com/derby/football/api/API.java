package com.derby.football.api;


import com.blackcat.retrofitutil.RetrofitUtil;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class API {

//    private static String BASE_URL = "http://api.zxyqx.top/user/index.php";
    private static String BASE_URL = "http://api.zxyqx.top/";
    private static ApiService apiService;

//    public static ApiService getApiService() {
//        if (apiService == null) {
//            synchronized (API.class) {
//                if (apiService == null) {
//
//                    Retrofit retrofit = new Retrofit.Builder()
//                                        .baseUrl(BASE_URL)
//                                        .addConverterFactory(GsonConverterFactory.create())
//                                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                                        .build();
//                    apiService = retrofit.create(ApiService.class);
//                }
//            }
//        }
//        return apiService;
//    }


    public static ApiService getApiService() {
        return createApiService(ApiService.class);
    }

    public static <T> T createApiService(Class<T> clazz) {
        RetrofitUtil.getOkHttpConfig().HTTP_CONNECT_TIMEOUT = 10* 1000;
        RetrofitUtil.getOkHttpConfig().HTTP_READ_TIMEOUT = 10* 1000;
        return RetrofitUtil.createApiService(clazz,BASE_URL);
    }

}
