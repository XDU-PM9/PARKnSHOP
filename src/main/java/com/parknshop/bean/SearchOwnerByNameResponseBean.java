package com.parknshop.bean;

import java.util.List;

/**
 * Created by Cong on 2017/1/2.
 */
public class SearchOwnerByNameResponseBean {

    /**
     * error : true/false
     * data : [{"id":123,"username":"String","userImage":"String","realname":"String","phone":"String","email":"String","address":"String","state":"int"}]
     */

    private boolean error;
    private List<DataBean> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 123
         * username : String
         * userImage : String
         * realname : String
         * phone : String
         * email : String
         * address : String
         * state : int
         */

        private int ownerId;
        private String username;
        private String userImage;
        private String realname;
        private String phone;
        private String email;
        private String address;
        private int state;

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
