package com.derby.football.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.derby.football.R;
import com.derby.football.base.BaseActivity;

import butterknife.Bind;

public class MineInfoActivity extends BaseActivity {

    @Bind(R.id.btnExit)
    Button btnExit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.mine_title;
    }


    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineInfoActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
