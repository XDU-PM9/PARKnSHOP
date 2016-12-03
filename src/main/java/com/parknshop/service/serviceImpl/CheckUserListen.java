package com.parknshop.service.serviceImpl;

import com.parknshop.service.serviceAbstract.AbstractCheckUser;
import org.springframework.stereotype.Component;

/**
 * Created by weina on 2016/11/28.
 */
@Component
public class CheckUserListen extends AbstractCheckUser {
    //用于 启用监听用户是否在线,返回用户
    @Override
    public Object checkLoginSatus() {

        return notifyObserverItem(CheckUser.class.getName());
    }
}
