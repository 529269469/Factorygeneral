package com.example.factorygeneral.greendao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.factorygeneral.greendao.bean.MenusListBean;
import com.example.factorygeneral.greendao.bean.ModuleListBean;
import com.example.factorygeneral.greendao.bean.PackageBean;
import com.example.factorygeneral.greendao.bean.ProjectModelBean;
import com.example.factorygeneral.greendao.bean.UnitListBean;

import com.example.factorygeneral.greendao.db.MenusListBeanDao;
import com.example.factorygeneral.greendao.db.ModuleListBeanDao;
import com.example.factorygeneral.greendao.db.PackageBeanDao;
import com.example.factorygeneral.greendao.db.ProjectModelBeanDao;
import com.example.factorygeneral.greendao.db.UnitListBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig menusListBeanDaoConfig;
    private final DaoConfig moduleListBeanDaoConfig;
    private final DaoConfig packageBeanDaoConfig;
    private final DaoConfig projectModelBeanDaoConfig;
    private final DaoConfig unitListBeanDaoConfig;

    private final MenusListBeanDao menusListBeanDao;
    private final ModuleListBeanDao moduleListBeanDao;
    private final PackageBeanDao packageBeanDao;
    private final ProjectModelBeanDao projectModelBeanDao;
    private final UnitListBeanDao unitListBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        menusListBeanDaoConfig = daoConfigMap.get(MenusListBeanDao.class).clone();
        menusListBeanDaoConfig.initIdentityScope(type);

        moduleListBeanDaoConfig = daoConfigMap.get(ModuleListBeanDao.class).clone();
        moduleListBeanDaoConfig.initIdentityScope(type);

        packageBeanDaoConfig = daoConfigMap.get(PackageBeanDao.class).clone();
        packageBeanDaoConfig.initIdentityScope(type);

        projectModelBeanDaoConfig = daoConfigMap.get(ProjectModelBeanDao.class).clone();
        projectModelBeanDaoConfig.initIdentityScope(type);

        unitListBeanDaoConfig = daoConfigMap.get(UnitListBeanDao.class).clone();
        unitListBeanDaoConfig.initIdentityScope(type);

        menusListBeanDao = new MenusListBeanDao(menusListBeanDaoConfig, this);
        moduleListBeanDao = new ModuleListBeanDao(moduleListBeanDaoConfig, this);
        packageBeanDao = new PackageBeanDao(packageBeanDaoConfig, this);
        projectModelBeanDao = new ProjectModelBeanDao(projectModelBeanDaoConfig, this);
        unitListBeanDao = new UnitListBeanDao(unitListBeanDaoConfig, this);

        registerDao(MenusListBean.class, menusListBeanDao);
        registerDao(ModuleListBean.class, moduleListBeanDao);
        registerDao(PackageBean.class, packageBeanDao);
        registerDao(ProjectModelBean.class, projectModelBeanDao);
        registerDao(UnitListBean.class, unitListBeanDao);
    }
    
    public void clear() {
        menusListBeanDaoConfig.clearIdentityScope();
        moduleListBeanDaoConfig.clearIdentityScope();
        packageBeanDaoConfig.clearIdentityScope();
        projectModelBeanDaoConfig.clearIdentityScope();
        unitListBeanDaoConfig.clearIdentityScope();
    }

    public MenusListBeanDao getMenusListBeanDao() {
        return menusListBeanDao;
    }

    public ModuleListBeanDao getModuleListBeanDao() {
        return moduleListBeanDao;
    }

    public PackageBeanDao getPackageBeanDao() {
        return packageBeanDao;
    }

    public ProjectModelBeanDao getProjectModelBeanDao() {
        return projectModelBeanDao;
    }

    public UnitListBeanDao getUnitListBeanDao() {
        return unitListBeanDao;
    }

}
