package com.example.factorygeneral.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 13
 */
@Entity
public class PackageBean {
    @Id(autoincrement = true)
    private Long uId;

    private String packageName;
    private String packagePath;
    private String time;
    private String uuid;

    public PackageBean(String packageName, String packagePath) {
        this.packageName = packageName;
        this.packagePath = packagePath;
    }

    @Generated(hash = 1443274481)
    public PackageBean(Long uId, String packageName, String packagePath,
            String time, String uuid) {
        this.uId = uId;
        this.packageName = packageName;
        this.packagePath = packagePath;
        this.time = time;
        this.uuid = uuid;
    }
    @Generated(hash = 971720530)
    public PackageBean() {
    }
    public Long getUId() {
        return this.uId;
    }
    public void setUId(Long uId) {
        this.uId = uId;
    }
    public String getPackageName() {
        return this.packageName;
    }
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public String getPackagePath() {
        return this.packagePath;
    }
    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



}
