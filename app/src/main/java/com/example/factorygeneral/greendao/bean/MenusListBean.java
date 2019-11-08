package com.example.factorygeneral.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 13
 */
@Entity
public class MenusListBean {
    @Id(autoincrement = true)
    private Long uId;

    private boolean isCheck;

    private String id;
    private String keyId;
    private String name;
    private String userName;
    private String uuid;
    @Generated(hash = 596313359)
    public MenusListBean(Long uId, boolean isCheck, String id, String keyId,
            String name, String userName, String uuid) {
        this.uId = uId;
        this.isCheck = isCheck;
        this.id = id;
        this.keyId = keyId;
        this.name = name;
        this.userName = userName;
        this.uuid = uuid;
    }
    @Generated(hash = 1949017066)
    public MenusListBean() {
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
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
    public boolean getIsCheck() {
        return this.isCheck;
    }
    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }


}
