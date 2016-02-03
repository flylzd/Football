package com.derby.football.ui.activity;


import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;

import butterknife.Bind;

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

//    @Override
//    protected boolean isDisplayBackEnabled() {
//        return true;
//    }
}
