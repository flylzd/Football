package com.derby.football.ui.activity;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.utils.ToastUtil;
import com.derby.football.widget.LoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.btnVerificationCode)
    Button btnVerificationCode;
    @Bind(R.id.etVerificationCode)
    EditText etVerificationCode;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.etPasswordConfirm)
    EditText etPasswordConfirm;
    @Bind(R.id.btnRegister)
    Button btnRegister;
    @Bind(R.id.contentLayout)
    LinearLayout contentLayout;

    private String phone;
    private String verificationCode;
    private String password;
    private String confirmPassword;

    private LoadingDialog loadingDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.register_title;
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(this);
    }

    @OnClick({R.id.btnVerificationCode, R.id.btnRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVerificationCode:
                getVerificationCode();
                break;
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void getVerificationCode() {

    }

    private void register() {
        if (checkRegister()) {

        }
    }

    private boolean checkRegister() {
        phone = etPhone.getText().toString();
        verificationCode = etVerificationCode.getText().toString();
        password = etPassword.getText().toString();
        confirmPassword = etPasswordConfirm.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showShortCenter(R.string.login_password_hint);
            return false;
        }

        if (TextUtils.isEmpty(verificationCode)) {
            ToastUtil.showShortCenter(R.string.register_phone_verification_code);
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShortCenter(R.string.login_password_hint);
            return false;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            ToastUtil.showShortCenter(R.string.register_password_confirm);
            return false;
        }
        return true;
    }

}
