package com.example.greendaodemo;

import com.example.greendaodemo.gen.DaoMaster;
import com.example.greendaodemo.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

public class GreenDaoManager {
    private static final String DB_NAME = "greendao";
    private static GreenDaoManager mInstance;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    private GreenDaoManager() {
        if (mInstance == null) {
            MySQLiteOpenHelper helper = new MySQLiteOpenHelper(MyApplication.getContext(), DB_NAME, null);
            Database db = helper.getWritableDb();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }
}
