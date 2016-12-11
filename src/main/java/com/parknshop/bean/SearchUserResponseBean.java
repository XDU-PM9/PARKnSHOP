package com.parknshop.bean;

/**
 * Created by cong on 2016/12/11.
 */
public class SearchUserResponseBean {

    /**
     * error : true/false
     * data : {"userId":123,"username":"String","userImage":"String","phone":"String","email":"String","address":"String","state":"int"}
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
         * userId : 123
         * username : String
         * userImage : String
         * phone : String
         * email : String
         * address : String
         * state : int
         */

        private int userId;
        private String username;
        private String userImage;
        private String phone;
        private String email;
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

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
