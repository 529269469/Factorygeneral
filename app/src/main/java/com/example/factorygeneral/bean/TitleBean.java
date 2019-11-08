package com.example.factorygeneral.bean;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/27 10
 */
public class TitleBean {
    private String title;
    private boolean isCheck;

    public TitleBean(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
