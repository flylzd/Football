package com.derby.football.utils;


import android.content.Context;
import android.content.Intent;

import com.derby.football.ui.activity.CityAreaActivity;
import com.derby.football.ui.activity.FindCourtDetailActivity;
import com.derby.football.ui.activity.FindCourtOrderActivity;
import com.derby.football.ui.activity.LoginActivity;
import com.derby.football.ui.activity.MainActivity;
import com.derby.football.ui.activity.RegisterActivity;

public class UIHelper {

    public static void showLoginActivity(Context context ) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void showRegisterActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    public static void showMainActivity(Context context ) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 场地详细
     */
    public static void showFindCourtDetailActivity(Context context ) {
        Intent intent = new Intent(context, FindCourtDetailActivity.class);
        context.startActivity(intent);
    }

    /**
     * 场次订购
     */
    public static void showFindCourtOrderActivity(Context context ) {
        Intent intent = new Intent(context, FindCourtOrderActivity.class);
        context.startActivity(intent);
    }


    /**
     * 选择城市
     */
    public static void showCityAreaActivity(Context context ) {
        Intent intent = new Intent(context, CityAreaActivity.class);
        context.startActivity(intent);
    }

}
