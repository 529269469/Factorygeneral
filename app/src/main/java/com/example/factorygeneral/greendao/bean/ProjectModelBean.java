package com.example.factorygeneral.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 13
 */
@Entity
public class ProjectModelBean {
    @Id(autoincrement = true)
    private Long uId;

    private String id;
    private String name;
    private String userName;
    private String uuid;
    @Generated(hash = 630133283)
    public ProjectModelBean(Long uId, String id, String name, String userName,
            String uuid) {
        this.uId = uId;
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.uuid = uuid;
    }
    @Generated(hash = 412805517)
    public ProjectModelBean() {
    }
    public Long getUId() {
        return this.uId;
    }
    public void setUId(Long uId) {
        this.uId = uId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
