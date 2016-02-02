package com.derby.football;


import android.app.Application;
import android.content.Context;

import com.derby.football.utils.ResUtil;
import com.derby.football.utils.ToastUtil;
import com.orhanobut.logger.Logger;

public class AppContext extends Application {

    private static Context instatnce;

    @Override
    public void onCreate() {
        super.onCreate();
        instatnce = this;
        ToastUtil.register(this);
        ResUtil.init(this);
//        RetrofitUtil.init(this);
        Logger.init();
    }

    public static Context getInstatnce() {
        return instatnce;
    }
}
