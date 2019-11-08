package com.example.factorygeneral.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/5 13
 */
@Entity
public class UnitListBean {
    @Id(autoincrement = true)
    private Long uId;
    private String uuid;
    private String answer;
    private String content;
    private String contentFile;
    private String id;
    private String keyUuid;
    private String label;
    private String relevantFile;
    private String sx;
    private String text;
    private String type;
    private String userName;

    @Generated(hash = 1402392465)
    public UnitListBean(Long uId, String uuid, String answer, String content,
            String contentFile, String id, String keyUuid, String label,
            String relevantFile, String sx, String text, String type,
            String userName) {
        this.uId = uId;
        this.uuid = uuid;
        this.answer = answer;
        this.content = content;
        this.contentFile = contentFile;
        this.id = id;
        this.keyUuid = keyUuid;
        this.label = label;
        this.relevantFile = relevantFile;
        this.sx = sx;
        this.text = text;
        this.type = type;
        this.userName = userName;
    }
    @Generated(hash = 1935753271)
    public UnitListBean() {
    }
 
    public Long getUId() {
        return this.uId;
    }
    public void setUId(Long uId) {
        this.uId = uId;
    }
    public String getAnswer() {
        return this.answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContentFile() {
        return this.contentFile;
    }
    public void setContentFile(String contentFile) {
        this.contentFile = contentFile;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getKeyUuid() {
        return this.keyUuid;
    }
    public void setKeyUuid(String keyUuid) {
        this.keyUuid = keyUuid;
    }
    public String getLabel() {
        return this.label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getRelevantFile() {
        return this.relevantFile;
    }
    public void setRelevantFile(String relevantFile) {
        this.relevantFile = relevantFile;
    }
    public String getSx() {
        return this.sx;
    }
    public void setSx(String sx) {
        this.sx = sx;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
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
