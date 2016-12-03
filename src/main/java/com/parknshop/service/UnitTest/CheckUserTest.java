package com.parknshop.service.UnitTest;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.UserEntity;
import com.parknshop.service.IUserService;
import com.parknshop.service.baseImpl.ICheckUserObserver;
import com.parknshop.service.serviceImpl.CheckUser;
import com.parknshop.service.serviceImpl.CheckUserListen;
import com.parknshop.service.serviceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by weina on 2016/11/28.
 */
public class CheckUserTest {
    /*
    测试 checkUser 一系列
     */
    @Autowired

    public static void main(String[] args){
//        testCheckUser();
        testLogin();
    }
    public static void testMap(){
        ICheckUserObserver observer = new CheckUser();
        System.out.println(observer.getClass().getName());
        System.out.println(CheckUser.class.getName());
    }
    public static void testCheckUser(){
        //测试 用户是否存在函数
//        IUserService userService = new UserService(new CheckUserListen(),new BaseDao<>());
//        System.out.println(userService.checkUserExist("email"));
    }
    public static  void testLogin(){
        //测试登录
//        IUserService userService = new UserService(new CheckUserListen(),new BaseDao<>());
//        UserEntity userEntity = userService.setIsAdmin(true).login("email","1");
//        if(null == userEntity){
//            System.out.println("登录失败");
//        }else {
//            System.out.println("登录成功");
//        }
    }
    public static  void testRegister() {
//        IUserService userService = new UserService(new CheckUserListen(),new BaseDao<>());
////        System.out.print(userService.register(new UserBuilder(RegisterType.USER)));
    }

}
