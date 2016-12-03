package com.parknshop.controller;

import com.parknshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 邮件激活专用
 * Created by song on 16-12-1.
 */
@Controller
@RequestMapping(value = "/activate")
public class ActivateController {

    private static final String PARAM_CODE = "code";
    private static final String PARAM_TYPE = "type";

    @Autowired
    IUserService mService;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public String activate(HttpServletRequest request){
        String code = request.getParameter(PARAM_CODE);
        String type = request.getParameter(PARAM_TYPE);
        System.out.println("code = "+code+"     type="+type);
        boolean result;
        switch (type){
            case "2":   //customer
                result = mService.activateUser(code);
                if (result){
                    return "CustomerActivateSucced.html";
                }
                break;
            case "3":   //customer
                result = mService.activateOwner(code);
                if (result){
                    return "OwnerActivateSucced.html";
                }
                break;
            default:
                break;
        }
        return "ActivateFailed.html";

    }

}
