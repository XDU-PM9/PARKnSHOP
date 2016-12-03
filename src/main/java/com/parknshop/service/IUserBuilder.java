package com.parknshop.service;

import com.parknshop.entity.UserEntity;
import com.parknshop.service.enumStatic.RegisterType;

/**
 * Created by weina on 2016/11/29.
 */
public interface IUserBuilder {
    /*
    创建 用户bean 的构造器
     */
    Object builder();//不需要 调用

    IUserBuilder setUserName(String userName);//设置 名字
    IUserBuilder setPhone(String phone);//设置手机
    IUserBuilder setPassWord(String passWord);//设置密码
    IUserBuilder setEmail(String email);//设置邮箱
    void clear();//如果 只能创建单例子，每次清空
    /**额外的函数， service层需要
     */
    String getEmail();
    String getName();
    String getConfirm();
    void setConfirm(String confirm);
}
