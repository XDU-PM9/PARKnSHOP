package com.parknshop.bean;

/**
 * Created by song on 16-12-3.
 */
public class ShopCheckBean {

    /**
     * error : false
     * status : 1
     * message : 审核中/审核完成
     */

    private boolean error;
    private int status;
    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
