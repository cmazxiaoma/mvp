package com.cmazxiaoma.mvp.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.cmazxiaoma.mvp.Config;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-16:04
 * Author: xiaoma
 */

public class BaseApplication extends Application {
    public static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;
        CrashReport.initCrashReport(getApplicationContext(), Config.BUGLY_APPID, true);
    }

    public static Context getContext(){
        return BaseApplication.mcontext;
    }
}
