package com.parknshop.bean;

import java.util.List;

/**
 * Created by cong on 2016/12/11.
 */
public class ApplyAllUserResponseBean {

    /**
     * error : boolean
     * total : 9
     * realSize : 5
     * data : [{"userId":"int","username":"String","phone":"String","email":"String","userImage":"String","state":"int"}]
     */

    private boolean error;
    private int total;
    private int realSize;
    private List<DataBean> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRealSize() {
        return realSize;
    }

    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : int
         * username : String
         * phone : String
         * email : String
         * userImage : String
         * state : int
         */

        private int userId;
        private String username;
        private String phone;
        private String email;
        private String userImage;
        private int state;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
