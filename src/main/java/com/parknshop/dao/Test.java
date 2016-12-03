package com.parknshop.dao;

import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.RoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by weina on 2016/11/25.
 */
@Repository
public class Test {

    public static void main(String[] args){

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setDescribes("商家");
        IBaseDao<RoleEntity> baseDao = new BaseDao<RoleEntity>();
        try {
            baseDao.save(roleEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("success");


    }
}
