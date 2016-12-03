package com.parknshop.service.enumStatic;

/**
 * Created by weina on 2016/11/30.
 */
public enum LoginTypeEnum {
    USER("UserEntity"),
    OWNER("OwnerEntity"),
    ADMIN("AdminEntity");
    private String type;
    LoginTypeEnum(String type){
        this.type = type;
    }
    @Override
    public String toString(){return  this.type;}
}
