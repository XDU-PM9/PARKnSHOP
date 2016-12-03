package com.parknshop.service.UnitTest;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.UserEntity;

/**
 * Created by weina on 2016/11/28.
 */
public class enumTest {
    public static  void main(String[] args){
        test2();

    }
    public  static  void test1(){
        IBaseDao<UserEntity> dao = new BaseDao<>();
//        dao.save(RegisterType.USER.getUserEntity());
    }
    public  static void test2(){
//        RegisterType.USER.getUserEntity().setAddress("hello");
//        System.out.print(RegisterType.USER.getUserEntity().getAddress());
    }
}
