package com.parknshop.service.serviceImpl.builder;

import com.parknshop.entity.RoleEntity;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.enumStatic.LoginTypeEnum;
import com.parknshop.service.enumStatic.RegisterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by weina on 2016/11/29.
 */
@Scope(value = "prototype")
@Service
public class UserBuilder implements IUserBuilder {

//    userEntity.setRoleByRoleId(roleEntity);
//        userEntity.setUserName("");
//        userEntity.setPhone("");
//        userEntity.setPassWord("");
//        userEntity.setBalance(0.0);
//        userEntity.setAddress("");
//        userEntity.setState(IUserService.STATE_REGISTER);//正在注册状态
//        userEntity.setEmail("");
    private int  roleId;
    private String userName;
    private String phone;
    private String passWord;
    private double balance;
    private int state;
    private String email;
    private String confirm;
    private String userImage;
    @Autowired
    private UserEntity userEntity ;
    @Autowired
    private RoleEntity roleEntity ;

    public UserBuilder(){
        this.roleId =RegisterType.USER.getRoleId();
        userName ="";
        phone ="";
        passWord="";
        balance=0.0;
        userImage="/user.jpg";
        state = IUserService.STATE_REGISTER;//注册状态
        email ="";
        confirm ="";
    }

    @Override
    public UserEntity builder() {

        roleEntity.setRoleId(this.roleId);
        userEntity.setRoleByRoleId(this.roleEntity);
        userEntity.setUsername(this.userName);
        userEntity.setPassword(this.passWord);
        userEntity.setBalance(this.balance);
        userEntity.setState(this.state);
        userEntity.setEmail(this.email);
        userEntity.setPhone(this.phone);
        userEntity.setConfirm(this.confirm);
        userEntity.setUserImage(this.userImage);
        return userEntity;
    }

    @Override
    public IUserBuilder setUserName(String userName) {
       this.userName = userName;
        return  this;
    }

    @Override
    public IUserBuilder setPhone(String phone) {
        this.phone = phone;
        return  this;
    }

    @Override
    public IUserBuilder setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    @Override
    public IUserBuilder setEmail(String email) {
        this.email =email;
        return  this;
    }

    @Override
    public void clear() {
        this.roleId =RegisterType.USER.getRoleId();
        userName ="";
        phone ="";
        passWord="";
        balance=0.0;
        state = IUserService.STATE_REGISTER;//注册状态
        email ="";
        confirm="";
        userImage="/user.jpg";

        roleEntity.setRoleId(this.roleId);
        userEntity.setRoleByRoleId(this.roleEntity);
        userEntity.setUsername(this.userName);
        userEntity.setPassword(this.passWord);
        userEntity.setBalance(this.balance);
        userEntity.setState(this.state);
        userEntity.setEmail(this.email);
        userEntity.setPhone(this.phone);
        userEntity.setConfirm(this.confirm);
        userEntity.setUserImage(this.userImage);
    }

    @Override
    public String getEmail() {
        return this.userEntity.getEmail();
    }

    @Override
    public String getName() {
        return  this.userEntity.getUsername();
    }

    @Override
    public String getConfirm() {
        return this.userEntity.getConfirm();
    }

    @Override
    public void setConfirm(String confirm) {this.confirm =confirm;}

}
