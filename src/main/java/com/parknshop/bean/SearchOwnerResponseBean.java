package com.parknshop.bean;

/**
 * Created by cong on 2016/12/10.
 */
public class SearchOwnerResponseBean {

    /**
     * error : true/false
     * data : {"ownerId":123,"username":"String","userImage":"String","realname":"String","phone":"String","email":"String","address":"String","state":"int"}
     */

    private boolean error;
    private DataBean data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ownerId : 123
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
