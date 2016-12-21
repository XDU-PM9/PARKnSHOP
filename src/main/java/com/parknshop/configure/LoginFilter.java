package com.parknshop.configure;

import com.parknshop.service.baseImpl.IDefineString;
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        if (null==request.getSession().getAttribute(IDefineString.SESSION_USER)) {
            request.setAttribute("backPage", true);
            request.getRequestDispatcher("customer/login").forward(request, response);
            return false;
        }
        return true;
    }
}
