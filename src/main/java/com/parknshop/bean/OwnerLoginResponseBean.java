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
}
