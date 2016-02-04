package com.derby.football.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.utils.ResUtil;
import com.derby.football.utils.ToastUtil;
import com.derby.football.utils.UIHelper;

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
            UIHelper.showRegisterActivity(this);
        }
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
