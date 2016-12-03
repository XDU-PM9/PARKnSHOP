package com.parknshop.service.serviceImpl;

import com.parknshop.entity.UserEntity;
import com.parknshop.service.baseImpl.ICheckUserObserver;
import com.parknshop.service.baseImpl.IDefineString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by weina on 2016/11/28.
 */
//单列
@Component
public class CheckUser implements ICheckUserObserver{
    @Override
    public Object userSatus(HttpSession session) {
        if(null == session){
            //如果 session 不存在创建一个
            session = getRequest().getSession(true);

        }
        Object userEntity =  session.getAttribute(IDefineString.SESSION_USER);
        if(null == userEntity){
            return null;
        }else{
            return userEntity;
        }
    }


   /*

   */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}
