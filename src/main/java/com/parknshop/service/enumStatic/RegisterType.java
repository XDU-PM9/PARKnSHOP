package com.parknshop.service.enumStatic;

import com.parknshop.service.IUserService;

/**
 * Created by weina on 2016/11/28.
 */

public enum RegisterType {
    USER(IUserService.ROLE_CUSTOMER),
    SHOPOWNER(IUserService.ROLE_SHOPOWNER);
    /*
    用于创建空的用户，和商家
     */

    private int roleId;

    RegisterType(int roleId){
        this.roleId = roleId;
    }
    public int getRoleId(){return roleId;}

}
