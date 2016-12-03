package com.parknshop.bean;

/**
 * Created by song on 16-12-1.
 */
public class RegisterRequestBean {


    /**
     * userName : userName
     * password : password
     * email : email
     */

    private String userName;
    private String password;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
