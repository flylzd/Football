package com.derby.football.ui.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.derby.football.R;
import com.derby.football.utils.UIHelper;

public class AppStartActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UIHelper.showLoginActivity(AppStartActivity.this);
                AppStartActivity.this.finish();
            }
        },500);

    }


}
