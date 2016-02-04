package com.derby.football.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.derby.football.ui.activity.RegisterActivity;

public class UIHelper {

    public static void showRegisterActivity(Context context ) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
