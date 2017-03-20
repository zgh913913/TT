package com.example.think.toutiao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 钟国会 on 2017/2/8.
 */
public class DbHelper {
    private static DbHelper mInstance;
    private DaoSession mDaoSession;

    private DbHelper() {
    }

    public static DbHelper getInstance() {
        if (mInstance == null) {
            synchronized (DbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DbHelper();
                }
            }
        }
        return mInstance;
    }

    public void setupDatabase(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "xixi.db");
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public ContentCacheDao getCacheDao() {
        return mDaoSession.getContentCacheDao();
    }
    public TitleCacheDao getTitleCacheDao() {
        return mDaoSession.getTitleCacheDao();
    }
}