package com.parknshop.bean;

/**
 * Created by song on 16-12-1.
 * @author 宋正腾
 * 用来处理登录json对象的bean
 */
public class LoginRequestBean {

    /**
     * userName : [user name]
     * password : [password]
     */

    private String userName;
    private String password;

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

    @Override
    public String toString() {
        return "userName:"+userName+"   password:"+password;
    }
}
