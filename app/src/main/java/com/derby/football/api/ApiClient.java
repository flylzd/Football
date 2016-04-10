package com.derby.football.api;


import android.content.Context;

import com.derby.football.R;
import com.derby.football.bean.AreaBean;
import com.derby.football.bean.BaseBean;
import com.derby.football.bean.CourtBean;
import com.derby.football.bean.CourtInfoBean;
import com.derby.football.bean.PlaceBean;
import com.derby.football.bean.UserBean;
import com.derby.football.config.AppConfig;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.utils.RandomID;
import com.derby.football.utils.SPUtil;
import com.derby.football.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

public class ApiClient {

    public static Map<Object, List<Call<?>>> apiCall = new HashMap<Object, List<Call<?>>>();

    private static Gson gson = new Gson();

    private final static int PER_PAGE = 20;

    /**
     * 登陆
     *
     * @param context
     * @param tag
     * @param mobile
     * @param password
     */
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

                AppConfig.UID = response.body().data.id;
                AppConfig.IMEI = response.body().data.imei;
                AppConfig.TOKEN = response.body().data.token;
                AppConfig.STATUS = response.body().data.status;
                SPUtil.put(context, AppConfig.UID_KEY, AppConfig.UID);
                SPUtil.put(context, AppConfig.IMEI_KEY, AppConfig.IMEI);
                SPUtil.put(context, AppConfig.TOKEN_KEY, AppConfig.TOKEN);
                SPUtil.put(context, AppConfig.STATUS_KEY, AppConfig.STATUS);

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_LOGIN);
                EventBus.getDefault().post(eventCenter);
            }
        });
    }

    /**
     * 注册
     *
     * @param context
     * @param tag
     * @param phone
     * @param verificationCode
     * @param password
     */
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
        param.put("code", verificationCode);  //默认为888888
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<BaseBean> call = API.getApiService().register(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<BaseBean>() {
            @Override
            void onSuccess(Response<BaseBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_REGISTER);
                EventBus.getDefault().post(eventCenter);
            }
        });
    }


    /**
     * 保存用户信息
     */
    public static void saveUser(final Context context, Object tag, UserBean.UserData userData) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, getUID());
        params.put(ApiService.I, AppConfig.IMEI);
        params.put(ApiService.T, AppConfig.TOKEN);
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "user");
        p.put(ApiService.P_ACTION, "save");
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", userData.name);
        param.put("nickName", userData.nickName);
        param.put("birthday", userData.birthday);     //20160201
        param.put("sex", userData.sex);  //M代表男，F代表女
        param.put("areaID", userData.areaID);
//        param.put("field", verificationCode); //上传的图片input字段名
//        param.put("url", userData.url);   //上传的图片地址
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<BaseBean> call = API.getApiService().register(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<BaseBean>() {
            @Override
            void onSuccess(Response<BaseBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_MINE_EDIT);
                EventBus.getDefault().post(eventCenter);
            }
        });
    }


    /**
     * 获取地区
     *
     * @param context
     * @param tag
     * @param areaID  父级地区ID，为0或不传时返回省份
     */
    public static void getArea(final Context context, final Object tag, int areaID) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, getUID());
        params.put(ApiService.I, AppConfig.IMEI);
        params.put(ApiService.T, AppConfig.TOKEN);
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "area");
        p.put(ApiService.P_ACTION, "getarea");
        Map<String, String> param = new HashMap<String, String>();
        param.put("parentID", String.valueOf(areaID));
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<AreaBean> call = API.getApiService().getArea(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<AreaBean>() {
            @Override
            void onSuccess(Response<AreaBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_AREA);
                EventBus.getDefault().post(eventCenter);
            }
        });

    }

    /**
     * 获取球场列表
     *
     * @param context
     * @param tag
     * @param areaID
     * @param nowPage
     */
    public static void getCourtList(final Context context, final Object tag, String areaID, int nowPage) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, getUID());
        params.put(ApiService.I, AppConfig.IMEI);
        params.put(ApiService.T, AppConfig.TOKEN);
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "merchant");
        p.put(ApiService.P_ACTION, "getlist");
        Map<String, String> param = new HashMap<String, String>();
//        param.put("areaID", areaID);  190201
        param.put("areaID", "190201");  //默认
        param.put("nowPage", String.valueOf(nowPage));
        param.put("perPage", String.valueOf(PER_PAGE));
        param.put("type", String.valueOf(1));  //场地类型:1三人场,2五人场,3七人场,4十一人场"}}'
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<CourtBean> call = API.getApiService().getCourtList(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<CourtBean>() {
            @Override
            void onSuccess(Response<CourtBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_getlist, response.body());
                EventBus.getDefault().post(eventCenter);
            }
        });
    }

    /**
     * 获取球场详情
     *
     * @param context
     * @param tag
     * @param mid     球场ID
     */
    public static void getCourtInfo(final Context context, final Object tag, String mid) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, getUID());
        params.put(ApiService.I, AppConfig.IMEI);
        params.put(ApiService.T, AppConfig.TOKEN);
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "merchant");
        p.put(ApiService.P_ACTION, "getinfo");
        Map<String, String> param = new HashMap<String, String>();
        param.put("mid", mid);
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<CourtInfoBean> call = API.getApiService().getCourtInfo(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<CourtInfoBean>() {
            @Override
            void onSuccess(Response<CourtInfoBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_getinfo, response.body());
                EventBus.getDefault().post(eventCenter);
            }
        });
    }

    /**
     * 获取球场场地价格列表
     *
     * @param context
     * @param tag
     * @param mid     球场ID
     * @param date    日期（20160202）
     */
    public static void getCourtPlace(final Context context, final Object tag, String mid, String date) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, getUID());
        params.put(ApiService.I, AppConfig.IMEI);
        params.put(ApiService.T, AppConfig.TOKEN);
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "merchant");
        p.put(ApiService.P_ACTION, "getplace");
        Map<String, String> param = new HashMap<String, String>();
        param.put("mid", mid);
        param.put("date", date);
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<PlaceBean> call = API.getApiService().getCourtPlace(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<PlaceBean>() {
            @Override
            void onSuccess(Response<PlaceBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_getplace, response.body());
                EventBus.getDefault().post(eventCenter);
            }
        });
    }

    /**
     * 下单
     *
     * @param context
     * @param tag
     * @param mid
     * @param date
     * @param orders  //{"球场场地ID":[时段1,时段2]}，例{"1":[12,13]}"  数组
     */
//    public static void order(final Context context, final Object tag, String mid, String date,List<Map<String,List<Integer>>> orders) {
    public static void order(final Context context, final Object tag, String mid, String date, Map<String, List<Integer>> orders) {

        Map<String, String> params = new HashMap<String, String>();
        params.put(ApiService.U, getUID());
        params.put(ApiService.I, AppConfig.IMEI);
        params.put(ApiService.T, AppConfig.TOKEN);
        Map<String, String> p = new HashMap<String, String>();
        p.put(ApiService.P_BEAN, "order");
        p.put(ApiService.P_ACTION, "create");
        Map<String, String> param = new HashMap<String, String>();
        param.put("mid", mid);
        param.put("date", date);
        param.put("key", RandomID.getRandom32ID());
        param.put("payType", "1");
        param.put("detail", gson.toJson(orders));
        System.out.println("detail == " + gson.toJson(orders));
        p.put(ApiService.P_PARAM, gson.toJson(param));
        params.put(ApiService.P, gson.toJson(p));

        Call<BaseBean> call = API.getApiService().order(params);
        addCall(tag, call);
        call.enqueue(new ResponseCallback<BaseBean>() {
            @Override
            void onSuccess(Response<BaseBean> response, Retrofit retrofit) {

                EventCenter eventCenter = new EventCenter(EventBusCode.SUCCESS_COURT_place_send, response.body());
                EventBus.getDefault().post(eventCenter);
            }
        });

    }


    private static String getUID() {
        return "c" + AppConfig.UID;
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

        if (apiCall.size() == 0) {
            List<Call<?>> callList = new ArrayList<Call<?>>();
            callList.add(call);
            apiCall.put(tag, callList);
            return;
        }
        System.out.println("ApiClient addCall ");
        for (Object key : apiCall.keySet()) {
            System.out.println(" key " + key);
            if (tag.equals(key)) {
                apiCall.get(key).add(call);
            } else {
                List<Call<?>> callList = new ArrayList<Call<?>>();
                callList.add(call);
                apiCall.put(tag, callList);
            }
        }
    }

    public synchronized static void cancel(Object tag) {
        if (tag == null) {
            throw new NullPointerException("the tag is null");
        }
        System.out.println("ApiClient cancel Call ");
        for (Object key : apiCall.keySet()) {
            System.out.println("key " + key);
            if (tag.equals(key)) {
                for (Call<?> call : apiCall.get(key)) {
                    call.cancel();
                }
            }
        }
        apiCall.remove(tag);
    }

}
