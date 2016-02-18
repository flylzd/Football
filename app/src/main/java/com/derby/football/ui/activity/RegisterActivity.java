package com.derby.football.ui.activity;


import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;
import com.derby.football.utils.ResUtil;
import com.derby.football.utils.ToastUtil;
import com.derby.football.widget.LoadingDialog;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
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

    private boolean isShowRegisterLayout = false;

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
//        loadingDialog = new LoadingDialog(this);

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

        ToastUtil.showShortCenter(R.string.verification_code_send);
//        handler.postDelayed(runnable, TIME);
        handler.post(runnable);

        if (!isShowRegisterLayout) {
            contentLayout.setVisibility(View.VISIBLE);
            contentLayout.startAnimation(AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.register_layout_enter));
        }
        isShowRegisterLayout = true;
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

    private int timeIndex = 60;
    private int TIME = 1000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeIndex--;
            btnVerificationCode.setEnabled(false);
            String timeHint = String.format(ResUtil.getString(R.string.register_get_verification_code_60), timeIndex);
            btnVerificationCode.setText(timeHint);
            if (timeIndex < 0) {
                btnVerificationCode.setEnabled(true);
                btnVerificationCode.setText(R.string.register_get_verification_code);
                timeIndex = 60;
                return;
            }
            handler.postDelayed(runnable, TIME);
        }
    };


}
