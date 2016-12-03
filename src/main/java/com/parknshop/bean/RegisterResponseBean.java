package com.parknshop.bean;

import java.util.Date;

/**
 * Created by song on 16-12-1.
 */
public class RegisterResponseBean {

    /**
     * error : true
     * message : “message”
     * date : ”date“
     * data : {"userName":"name","imge":"userimage"}
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
         * userName : name
         * imge : userimage
         */

        private String userName;
        private String imge;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getImge() {
            return imge;
        }

        public void setImge(String imge) {
            this.imge = imge;
        }
    }
}
