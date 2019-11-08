package com.example.factorygeneral.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 13
 */
@Entity
public class ModuleListBean {
    @Id(autoincrement = true)
    private Long uId;

    private String uuid;
    private String id;
    private String keyId;
    private String keyUuid;
    private String name;
    private String userName;

    @Generated(hash = 1901602276)
    public ModuleListBean(Long uId, String uuid, String id, String keyId,
            String keyUuid, String name, String userName) {
        this.uId = uId;
        this.uuid = uuid;
        this.id = id;
        this.keyId = keyId;
        this.keyUuid = keyUuid;
        this.name = name;
        this.userName = userName;
    }
    @Generated(hash = 199181449)
    public ModuleListBean() {
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
    public String getKeyId() {
        return this.keyId;
    }
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
    public String getKeyUuid() {
        return this.keyUuid;
    }
    public void setKeyUuid(String keyUuid) {
        this.keyUuid = keyUuid;
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
