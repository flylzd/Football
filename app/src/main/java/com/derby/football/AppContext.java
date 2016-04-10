package com.derby.football;


import android.app.Application;
import android.content.Context;

import com.blackcat.retrofitutil.RetrofitUtil;
import com.derby.football.utils.ResUtil;
import com.derby.football.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

public class AppContext extends Application {

    private static Context instatnce;

    @Override
    public void onCreate() {
        super.onCreate();
        instatnce = this;
        ToastUtil.register(this);
        ResUtil.init(this);
        RetrofitUtil.init(this);
        Logger.init();

        CrashReport.initCrashReport(getApplicationContext());

    }

    public static Context getInstatnce() {
        return instatnce;
    }
}
