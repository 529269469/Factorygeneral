package com.example.factorygeneral.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.factorygeneral.greendao.db.DaoMaster;
import com.example.factorygeneral.greendao.db.DaoSession;


public class MyApplication extends Application {


    public static BaseActivity mContext;
    //静态单例
    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }

    public static MyApplication getInstances() {
        return instances;
    }


    private DaoMaster.DevOpenHelper packageHelper;
    private SQLiteDatabase packageDb;
    private DaoMaster packageMaster;
    private DaoSession packageDaoSession;

    private DaoMaster.DevOpenHelper menusHelper;
    private SQLiteDatabase menusDb;
    private DaoMaster menusMaster;
    private DaoSession menusDaoSession;

    private DaoMaster.DevOpenHelper moduleHelper;
    private SQLiteDatabase moduleDb;
    private DaoMaster moduleMaster;
    private DaoSession moduleDaoSession;

    private DaoMaster.DevOpenHelper unitHelper;
    private SQLiteDatabase unitDb;
    private DaoMaster unitMaster;
    private DaoSession unitDaoSession;

    private DaoMaster.DevOpenHelper projectModelHelper;
    private SQLiteDatabase projectModelDb;
    private DaoMaster projectModelMaster;
    private DaoSession projectModelDaoSession;

    private void setDatabase() {
        projectModelHelper = new DaoMaster.DevOpenHelper(this, "projectModel.db");
        projectModelDb = projectModelHelper.getWritableDatabase();
        projectModelMaster = new DaoMaster(projectModelDb);
        projectModelDaoSession = projectModelMaster.newSession();

        unitHelper = new DaoMaster.DevOpenHelper(this, "unitBean.db");
        unitDb = unitHelper.getWritableDatabase();
        unitMaster = new DaoMaster(unitDb);
        unitDaoSession = unitMaster.newSession();


        moduleHelper = new DaoMaster.DevOpenHelper(this, "moduleBean.db");
        moduleDb = moduleHelper.getWritableDatabase();
        moduleMaster = new DaoMaster(moduleDb);
        moduleDaoSession = moduleMaster.newSession();


        menusHelper = new DaoMaster.DevOpenHelper(this, "menusBean.db");
        menusDb = menusHelper.getWritableDatabase();
        menusMaster = new DaoMaster(menusDb);
        menusDaoSession = menusMaster.newSession();

        packageHelper = new DaoMaster.DevOpenHelper(this, "packageBean.db");
        packageDb = packageHelper.getWritableDatabase();
        packageMaster = new DaoMaster(packageDb);
        packageDaoSession = packageMaster.newSession();
    }

    public DaoSession getModuleDaoSession() {
        return moduleDaoSession;
    }

    public DaoSession getUnitDaoSession() {
        return unitDaoSession;
    }

    public DaoSession getProjectModelDaoSession() {
        return projectModelDaoSession;
    }

    public DaoSession getPackageDaoSession() {
        return packageDaoSession;
    }

    public DaoSession getMenusDaoSession() {
        return menusDaoSession;
    }
}
