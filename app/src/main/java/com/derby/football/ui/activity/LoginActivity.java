package com.derby.football.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derby.football.R;

import com.derby.football.api.ApiClient;
import com.derby.football.base.BaseActivity;
import com.derby.football.config.EventBusCode;
import com.derby.football.eventbus.EventCenter;
import com.derby.football.utils.ToastUtil;
import com.derby.football.utils.UIHelper;
import com.derby.football.widget.LoadingDialog;

import butterknife.Bind;
import butterknife.OnClick;

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

    private LoadingDialog loadingDialog;

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
        loadingDialog = new LoadingDialog(this);

    }

    @OnClick(R.id.btnLogin)
    void login() {
        if (checkLogin()) {
            loadingDialog.setMessage(R.string.loading_login);
            loadingDialog.show();

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            ApiClient.login(LoginActivity.this, TAG, username, password);
        }
    }

    @OnClick(R.id.tvRegister)
    void register() {
        UIHelper.showRegisterActivity(this);
    }

    @OnClick(R.id.tvForgotPassword)
    void forgotPassword() {
//        UIHelper.showRegisterActivity(this);
//        UIHelper.showFindCourtDetailActivity(this);
//        UIHelper.showFindCourtOrderActivity(this);
//        UIHelper.showCityAreaActivity(this);
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


    @Override
    protected void onEventBusHandler(EventCenter eventCenter) {

        loadingDialog.dismiss();
        switch (eventCenter.getEventCode()) {
            case EventBusCode.SUCCESS_LOGIN:
                this.finish();
                UIHelper.showMainActivity(this);
                break;
        }
    }
}
