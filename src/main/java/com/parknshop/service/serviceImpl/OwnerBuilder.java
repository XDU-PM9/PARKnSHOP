package com.parknshop.service.serviceImpl;

import com.parknshop.entity.OwnerEntity;
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
 * Created by weina on 2016/11/30.
 */
@Scope(value = "prototype")
@Service
public class OwnerBuilder implements IUserBuilder{
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
    private String address;
    private String realname;
    private String userImage;
    private int state;
    private String email;
    private String confirm;
    @Autowired
    private OwnerEntity ownerEntity ;
    @Autowired
    private RoleEntity roleEntity ;

    public OwnerBuilder(){
        this.roleId = RegisterType.SHOPOWNER.getRoleId();
        userName ="";
        phone ="";
        passWord="";
        balance=0.0;
        address ="";
        state = IUserService.STATE_REGISTER;//注册状态
        email ="";
        confirm="";
        realname="";
        userImage="";
    }

    @Override
    public OwnerEntity builder(){

        roleEntity.setRoleId(this.roleId);
        ownerEntity.setRoleByRoleId(this.roleEntity);
        ownerEntity.setUsername(this.userName);
        ownerEntity.setPassword(this.passWord);
        ownerEntity.setAddress("");
        ownerEntity.setBalance(this.balance);
        ownerEntity.setState(this.state);
        ownerEntity.setEmail(this.email);
        ownerEntity.setPhone(this.phone);
        /*
        额外的
         */
        ownerEntity.setPicture("");
        ownerEntity.setIdentityId("");
        ownerEntity.setConfirm(this.confirm);
        ownerEntity.setRealname(this.realname);
        ownerEntity.setUserImage(this.userImage);
        return ownerEntity;
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
        this.roleId = RegisterType.SHOPOWNER.getRoleId();
        userName ="";
        phone ="";
        passWord="";
        balance=0.0;
        address ="";
        state = IUserService.STATE_REGISTER;//注册状态
        email ="";
        realname="";
        userImage="";

        roleEntity.setRoleId(this.roleId);
        ownerEntity.setRoleByRoleId(this.roleEntity);
        ownerEntity.setUsername(this.userName);
        ownerEntity.setPassword(this.passWord);
        ownerEntity.setAddress("");
        ownerEntity.setBalance(this.balance);
        ownerEntity.setState(this.state);
        ownerEntity.setEmail(this.email);
        ownerEntity.setPhone(this.phone);
        /*
        额外的
         */
        ownerEntity.setPicture("");
        ownerEntity.setIdentityId("");
        ownerEntity.setConfirm("");
        ownerEntity.setRealname("");
        ownerEntity.setUserImage("");
    }

    @Override
    public String getEmail() {
        return this.ownerEntity.getEmail();
    }

    @Override
    public String getName() {
        return this.ownerEntity.getUsername();
    }

    @Override
    public String getConfirm() {
        return this.ownerEntity.getConfirm();
    }

    @Override
    public void setConfirm(String confirm) {
        this.confirm =confirm;
    }

}
