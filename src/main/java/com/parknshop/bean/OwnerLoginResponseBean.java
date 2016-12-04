package com.parknshop.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by song on 16-12-1.
 */
public class OwnerLoginResponseBean {

    /**
     * error : false
     * message : message
     * date : date
     * data : {"userName":"userName","image":"user image"}
     */

    private boolean error;
    private String message;
    private Date date;
    private DataBean data;
    private ShopBean shop;

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
         * userName : userName
         * image : user image
         */

        private String userName;
        private String image;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class ShopBean{
        /**
         * "status":-3|-2|-1|0|1|2|3,//代表店的状态，且msg中带有状态的文字描述
         *  “msg”："描述该店当前状态，如审核中；已被拒绝；已被拉黑等",
         *  "name":"店名"，
         *  "ico":"店的ico"，
         *  “desc”："店的描述"
         */
        private int status;
        private String msg;
        private String name;
        private String ico;
        private String desc;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
