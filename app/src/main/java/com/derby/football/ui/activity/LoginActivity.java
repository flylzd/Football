package com.derby.football.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.api.API;
import com.derby.football.api.ApiService;
import com.derby.football.base.BaseActivity;
import com.derby.football.bean.UserBean;
import com.derby.football.config.AppConfig;
import com.derby.football.utils.SPUtil;
import com.derby.football.utils.ToastUtil;
import com.derby.football.utils.UIHelper;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends BaseActivity {

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    @Bind(R.id.etUsername)
    EditText etUsername;

    @Bind(R.id.etPassword)
    EditText etPassword;

    @Bind(R.id.btnLogin)
    Button btnLogin;

    @Bind(R.id.tvRegister)
    TextView tvRegister;

    @Bind(R.id.tvForgotPassword)
    TextView tvForgotPassword;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.login_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btnLogin)
    void login() {
        if (checkLogin()){
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            performLogin(username,password);
        }
    }

    private void performLogin(String mobile, String password) {

        Gson gson = new Gson();
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

        API.getApiService().login(params).enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Response<UserBean> response, Retrofit retrofit) {

                if (response.body().status < 0){ //返回失败
                    ToastUtil.showShort(response.body().message);
                    return;
                }

                AppConfig.IMEI = response.body().data.imei;
                AppConfig.TOKEN = response.body().data.token;
                SPUtil.put(LoginActivity.this,AppConfig.IMEI_KEY,AppConfig.IMEI);
                SPUtil.put(LoginActivity.this,AppConfig.TOKEN_KEY,AppConfig.TOKEN);

                UIHelper.showMainActivity(LoginActivity.this);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @OnClick(R.id.tvRegister)
    void register() {
        UIHelper.showRegisterActivity(this);
    }

    @OnClick(R.id.tvForgotPassword)
    void forgotPassword() {
        UIHelper.showRegisterActivity(this);
    }

    private boolean checkLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            ToastUtil.showShortCenter(R.string.login_username_hint);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShortCenter(R.string.login_password_hint);
            return false;
        }
        return true;
    }
}
