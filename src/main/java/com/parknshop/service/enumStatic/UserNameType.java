package com.parknshop.service.enumStatic;

import org.springframework.stereotype.Component;

/**
 * Created by weina on 2016/11/29.
 */

public enum UserNameType {
    PHONE("phone"),
    EAMIL("email"),
    NAME("userName");
    private String type;
    UserNameType(String type){
        this.type  = type;
    }
    @Override
    public String toString(){return  type;}
}
