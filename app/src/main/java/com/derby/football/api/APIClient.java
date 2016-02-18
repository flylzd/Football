package com.derby.football.api;


import android.content.Context;

import com.derby.football.R;
import com.derby.football.bean.BaseBean;
import com.derby.football.bean.UserBean;
import com.derby.football.config.AppConfig;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.utils.SPUtil;
import com.derby.football.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

public class ApiClient {

    public volatile static Map<Object, List<Call<?>>> apiCall = new HashMap<Object, List<Call<?>>>();

    private static Gson gson = new Gson();

    public static void login(final Context context, Object tag, String mobile, String password) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, ApiService.C1);
        params.put(ApiService.I, "");
        params.put(ApiService.T, "");
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "user");
        p.put(ApiService.P_ACTION, "login");
        Map<String, String> param = new HashMap<String, String>();
//        param.put("mobile","13202018415");
//        param.put("passwd","123456");
        param.put("mobile", mobile);
        param.put("passwd", password);
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<UserBean> call = API.getApiService().login(params);
        addCall(tag, call);
//        call.enqueue(new Callback<UserBean>() {
//            @Override
//            public void onResponse(Response<UserBean> response, Retrofit retrofit) {
//
////                System.out.println("11111");
//                if (response.body().status < 0) { //返回失败
//                    ToastUtil.showShort(response.body().message);
////                    System.out.println("dddd");
//                    return;
//                }
//
//                AppConfig.IMEI = response.body().data.imei;
//                AppConfig.TOKEN = response.body().data.token;
//                SPUtil.put(context, AppConfig.IMEI_KEY, AppConfig.IMEI);
//                SPUtil.put(context, AppConfig.TOKEN_KEY, AppConfig.TOKEN);
//
//                UIHelper.showMainActivity(context);
////                EventCenter<UserBean> eventCenter = new EventCenter<UserBean>(EventBusCode.SUCCESS_LOGIN, response.body());
////                EventBus.getDefault().post(eventCenter);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//
//            }
//        });
        call.enqueue(new ResponseCallback<UserBean>() {
            @Override
            void onSuccess(Response<UserBean> response, Retrofit retrofit) {

                AppConfig.IMEI = response.body().data.imei;
                AppConfig.TOKEN = response.body().data.token;
                SPUtil.put(context, AppConfig.IMEI_KEY, AppConfig.IMEI);
                SPUtil.put(context, AppConfig.TOKEN_KEY, AppConfig.TOKEN);

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_LOGIN);
                EventBus.getDefault().post(eventCenter);
            }
        });
    }

    public static void register(final Context context, Object tag, String phone, String verificationCode, String password) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, ApiService.C1);
        params.put(ApiService.I, "");
        params.put(ApiService.T, "");
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "user");
        p.put(ApiService.P_ACTION, "register");
        Map<String, String> param = new HashMap<String, String>();
        param.put("mobile", phone);
        param.put("passwd", password);
        param.put("repasswd", password);
        param.put("code",verificationCode);  //默认为888888
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<UserBean> call = API.getApiService().login(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<UserBean>() {
            @Override
            void onSuccess(Response<UserBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_REGISTER);
                EventBus.getDefault().post(eventCenter);
            }
        });
    }

    abstract static class ResponseCallback<T> implements retrofit.Callback<T> {

        @Override
        public void onResponse(Response<T> response, Retrofit retrofit) {
            int status = ((BaseBean) response.body()).status;
            if (status < 0) { //返回失败
                ToastUtil.showShort(((BaseBean) response.body()).message);
                EventCenter eventCenter = new EventCenter(EventBusCode.STATUS_LESS_THAN_ZERO);
                EventBus.getDefault().post(eventCenter);
                return;
            }

            onSuccess(response, retrofit);
        }

        @Override
        public void onFailure(Throwable t) {
            ToastUtil.showShort(R.string.network_failure);
            EventCenter eventCenter = new EventCenter(EventBusCode.FAILURE);
            EventBus.getDefault().post(eventCenter);
        }

        /**
         * Successful HTTP response.
         */
        abstract void onSuccess(Response<T> response, Retrofit retrofit);
    }

    private synchronized static void addCall(Object tag, Call<?> call) {
        if (tag == null) {
            throw new NullPointerException("the tag is null");
        }

        if (apiCall.size() == 0){
            List<Call<?>> callList = new ArrayList<Call<?>>();
            callList.add(call);
            apiCall.put(tag,callList);
            return;
        }

        for (Object key : apiCall.keySet()) {
            if (tag.equals(key)) {
                apiCall.get(key).add(call);
            } else {
                List<Call<?>> callList = new ArrayList<Call<?>>();
                callList.add(call);
                apiCall.put(tag,callList);
            }
        }
    }

    public synchronized static void cancel(Object tag) {
        if (tag == null) {
            throw new NullPointerException("the tag is null");
        }
        for (Object key : apiCall.keySet()) {
            if (tag.equals(key)) {
                for (Call<?> call : apiCall.get(key)) {
                    System.out.println("1111111111111");
                    call.cancel();
                }
            }
        }
        System.out.println("22222222222");
        apiCall.remove(tag);
    }

}
