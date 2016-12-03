package com.parknshop.bean;

import java.util.Date;

/**
 * Created by song on 16-12-2.
 */
public class AdminLoginResponseBean {

    /**
     * error : false
     * message : 登录返回消息
     * date : 服务器应答时间戳
     * data : {"userName":"管理员名字"}
     */

    private boolean error;
    private String message;
    private Date date;
    private DataBean data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userName : 管理员名字
         */

        private String userName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
