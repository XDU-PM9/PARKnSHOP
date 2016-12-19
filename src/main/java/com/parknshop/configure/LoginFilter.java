package com.parknshop.configure;

import com.parknshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wei on 16-12-19.
 */
@Component
public class LoginFilter extends HandlerInterceptorAdapter {
    @Autowired
    IUserService userService;

    //    final static String[] filterUrls=new String[]{
//            "listProduct"
//    };
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
//        for(String url:filterUrls){
//            if(request.getServletPath().contains(url)){
//                request.setAttribute("url",request.getServletPath());
//                request.getRequestDispatcher("customer/login").forward(request,response);
//                return false;
//            }
//        }
        if (!userService.isLogin()) {
            request.setAttribute("backPage", true);
//                request.setAttribute("url",request.getServletPath()+"?"+request.getQueryString());
            request.getRequestDispatcher("customer/login").forward(request, response);
            return false;
        }
        return true;
    }
}
