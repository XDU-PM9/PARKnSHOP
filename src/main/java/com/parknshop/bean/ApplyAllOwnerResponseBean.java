package com.parknshop.bean;

import java.util.List;

/**
 * Created by cong on 2016/12/11.
 */
public class ApplyAllOwnerResponseBean {

    /**
     * error : boolean
     * total : 9
     * realSize : 5
     * data : [{"ownerId":123,"username":"String","userImage":"String","realname":"String","phone":"String","email":"String","address":"String","state":"int"}]
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
