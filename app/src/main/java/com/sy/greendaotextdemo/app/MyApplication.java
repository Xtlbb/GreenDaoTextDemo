package com.sy.greendaotextdemo.app;

import android.app.Application;
import android.content.Context;

import com.sy.greendaotextdemo.manager.GreenDaoManager;

/**
 * 创建时间： 2017/9/11 0011.
 * 编写人：xutailian
 * 功能描述：
 */

public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.getInstance();
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        MyApplication.mContext = mContext;
    }
}
