package com.sy.greendaotextdemo.manager;

import com.sy.greendaotextdemo.gen.DaoMaster;
import com.sy.greendaotextdemo.gen.DaoSession;
import com.sy.greendaotextdemo.gen.UserBeanDao;
import com.sy.greendaotextdemo.util.UIUtils;

/**
 * 创建时间： 2017/9/11 0011.
 * 编写人：xutailian
 * 功能描述：
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(UIUtils.getContext(), "zy_db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    public UserBeanDao getUserBeanDao(){
        return mDaoSession.getUserBeanDao();
    }


}
