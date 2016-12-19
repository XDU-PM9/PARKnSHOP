package com.parknshop.controller;

import com.parknshop.dao.IBaseDao;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.RoleEntity;
import com.parknshop.service.IUserBuilder;
import com.parknshop.service.IUserService;
import com.parknshop.service.serviceImpl.OwnerService;
import com.parknshop.service.serviceImpl.builder.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by weina on 2016/11/28.
 */
@Controller
public class TestConroller {
    final
    IUserService service;
    final
    IUserBuilder userBuilder;
    final
    RoleEntity roleEntity;
    final
    IBaseDao<RoleEntity> mDao;

    @Autowired
    OwnerService services;
    @Autowired
    public TestConroller(IUserService service, UserBuilder userBuilder, RoleEntity roleEntity, IBaseDao<RoleEntity> mDao) {
        this.service = service;
        this.userBuilder = userBuilder;
        this.roleEntity = roleEntity;
        this.mDao = mDao;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index() {
        testRegister();
        return "index";
    }
    public void testLogin(){
        System.out.println(service.loginAsUser("admin","admin"));
    }
    public void testRegister(){
        userBuilder.clear();
        System.out.println(service.registerByUser(userBuilder.setPassWord("ww").setPhone("1234").setUserName("ww").setEmail("870603569@qq.com")));
    }
    public void testRole(){
        roleEntity.setDescribes("商家");

        try {
           mDao.save(roleEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("success");
    }
    public void testGet(){
        //测试dao get
//        AdminEntity c =(AdminEntity) service.getPeppleById(AdminEntity.class,2);
//        System.out.print(c.getPassword());
    }
    public void testShop(){
        OwnerEntity entity = new OwnerEntity();
        entity.setOwnerId(1);
        services.isHasShop(entity);
    }
}
