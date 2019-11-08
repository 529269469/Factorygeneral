package com.example.factorygeneral.bean;

import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/11/8 09
 */
public class FindFilesBean {

    /**
     * state : 200
     * message : null
     * data : [{"id":"41","name":"测试数据包(201911071522).zip","userName":"ruohan"}]
     */

    private int state;
    private String message;
    private List<DataBean> data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 41
         * name : 测试数据包(201911071522).zip
         * userName : ruohan
         */

        private String id;
        private String name;
        private String userName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
