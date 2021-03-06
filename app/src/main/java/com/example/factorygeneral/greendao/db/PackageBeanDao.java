package com.example.factorygeneral.greendao.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.factorygeneral.greendao.bean.PackageBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PACKAGE_BEAN".
*/
public class PackageBeanDao extends AbstractDao<PackageBean, Long> {

    public static final String TABLENAME = "PACKAGE_BEAN";

    /**
     * Properties of entity PackageBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UId = new Property(0, Long.class, "uId", true, "_id");
        public final static Property PackageName = new Property(1, String.class, "packageName", false, "PACKAGE_NAME");
        public final static Property PackagePath = new Property(2, String.class, "packagePath", false, "PACKAGE_PATH");
        public final static Property Time = new Property(3, String.class, "time", false, "TIME");
        public final static Property Uuid = new Property(4, String.class, "uuid", false, "UUID");
    }


    public PackageBeanDao(DaoConfig config) {
        super(config);
    }
    
    public PackageBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PACKAGE_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: uId
                "\"PACKAGE_NAME\" TEXT," + // 1: packageName
                "\"PACKAGE_PATH\" TEXT," + // 2: packagePath
                "\"TIME\" TEXT," + // 3: time
                "\"UUID\" TEXT);"); // 4: uuid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PACKAGE_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PackageBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(2, packageName);
        }
 
        String packagePath = entity.getPackagePath();
        if (packagePath != null) {
            stmt.bindString(3, packagePath);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        String uuid = entity.getUuid();
        if (uuid != null) {
            stmt.bindString(5, uuid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PackageBean entity) {
        stmt.clearBindings();
 
        Long uId = entity.getUId();
        if (uId != null) {
            stmt.bindLong(1, uId);
        }
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(2, packageName);
        }
 
        String packagePath = entity.getPackagePath();
        if (packagePath != null) {
            stmt.bindString(3, packagePath);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(4, time);
        }
 
        String uuid = entity.getUuid();
        if (uuid != null) {
            stmt.bindString(5, uuid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PackageBean readEntity(Cursor cursor, int offset) {
        PackageBean entity = new PackageBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // uId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // packageName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // packagePath
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // time
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // uuid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PackageBean entity, int offset) {
        entity.setUId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPackageName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPackagePath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUuid(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PackageBean entity, long rowId) {
        entity.setUId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PackageBean entity) {
        if(entity != null) {
            return entity.getUId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PackageBean entity) {
        return entity.getUId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
